package es.santander.ascender.final_grupo04.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@Service
public class AzureVisionService {

    @Value("${azure.vision.endpoint}")
    private String endpoint;

    @Value("${azure.vision.key}")
    private String subscriptionKey;

    private final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    public String extraerTextoDesdeArchivo(MultipartFile file) throws Exception {
        String visionUrl = endpoint + "/vision/v3.2/read/analyze";

        System.out.println("🔁 Enviando imagen binaria directamente a Azure Vision (OCR)");

        RequestBody body = RequestBody.create(file.getBytes(), MediaType.parse("application/octet-stream"));

        Request request = new Request.Builder()
                .url(visionUrl)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Content-Type", "application/octet-stream")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            String error = response.body() != null ? response.body().string() : "Sin detalle";
            throw new RuntimeException("❌ Error al enviar imagen binaria: " + error);
        }

        String operationLocation = response.header("Operation-Location");
        if (operationLocation == null) {
            throw new RuntimeException("❌ No se recibió Operation-Location");
        }

        // Esperar OCR
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);

            Request resultRequest = new Request.Builder()
                    .url(operationLocation)
                    .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                    .build();

            Response resultResponse = client.newCall(resultRequest).execute();
            String resultJson = resultResponse.body().string();
            JsonNode json = new ObjectMapper().readTree(resultJson);

            if ("succeeded".equals(json.path("status").asText())) {
                StringBuilder texto = new StringBuilder();
                for (JsonNode page : json.path("analyzeResult").path("readResults")) {
                    for (JsonNode line : page.path("lines")) {
                        texto.append(line.path("text").asText()).append(" ");
                    }
                }
                return texto.toString().trim();
            }
        }

        throw new RuntimeException("⏱ El análisis tomó demasiado tiempo.");
    }

}
