package es.santander.ascender.final_grupo04.service;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobHttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class AzureBlobService {

    @Value("${azure.storage.account-name}")
    private String accountName;

    @Value("${azure.storage.account-key}")
    private String accountKey;

    @Value("${azure.storage.container-name}")
    private String containerName;

    @Value("${azure.storage.endpoint}")
    private String endpoint;

    public String uploadFile(MultipartFile file) throws Exception {
        System.out.println("⚠️ Entrando al método uploadFile");
        String fileName = "items/portadas/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        String connectionString = String.format(
                "DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net",
                accountName, accountKey
        );

        System.out.println("🚀 Conectando a Azure Blob con: " + connectionString);
        System.out.println("📄 Subiendo archivo: " + fileName + " (" + file.getContentType() + ")");

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        if (!containerClient.exists()) {
            System.out.println("ℹ️ Contenedor no existe, creando: " + containerName);
            containerClient.create();
        }

        BlobClient blobClient = containerClient.getBlobClient(fileName);

        try (InputStream inputStream = file.getInputStream()) {
            blobClient.upload(inputStream, file.getSize(), true);
            BlobHttpHeaders headers = new BlobHttpHeaders()
                    .setContentType(file.getContentType());
            blobClient.setHttpHeaders(headers);
            System.out.println("✅ Imagen subida a: " + endpoint + containerName + "/" + fileName);
        } catch (Exception e) {
            System.out.println("❌ Error al subir a Azure Blob: " + e.getMessage());
            throw new RuntimeException("Error al subir archivo a Azure Blob", e);
        }

        return endpoint + containerName + "/" + fileName;
    }

}
