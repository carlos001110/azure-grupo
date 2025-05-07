PARA EJECUTAR CON TEST
mvn clean package

// Si quieres desactivar
set AZURE_CLI_DISABLE_CONNECTION_VERIFICATION=1 

// SUBIR EL BACKEND
 az webapp deploy --resource-group grupal --name backend-grupal --src-path target/final-grupo04-0.0.1-SNAPSHOT.jar --type jar

// EJECUTAR FRONTEND ANGULAR 
npm run build --prod

y Ir a la carpeta dist que se tiene los archivos que se subirán al contenedor de azure


Resumen de la implementación de Azure Computer Vision
1. Creación del recurso en Azure
Fuiste al portal de Azure.

Creaste un recurso de Computer Vision (ahora parte de Azure Cognitive Services).

Obtuviste:

Una clave de suscripción (API key).

Una URL de endpoint para hacer las solicitudes.

2. Configuración del backend en Spring Boot
Usaste Spring Boot para recibir la imagen (tipo MultipartFile).

Configuraste un método en el controlador para:

Recibir el archivo.

Enviarlo a Azure Computer Vision por medio de una llamada HTTP (RestTemplate o HttpClient).

Parsear la respuesta JSON de Azure para obtener, por ejemplo, el título detectado en la imagen.

Devolver ese texto al frontend junto con la URL de la imagen en blob storage (si aplica).

3. Subida de la imagen a Azure Blob Storage
Configuraste una cuenta de almacenamiento con un contenedor (por ejemplo, imagenes).

Desde el backend:

Usaste la librería azure-storage-blob para subir la imagen al contenedor.

Generaste la URL pública del archivo y la guardaste en tu entidad Item.

4. Integración con el frontend Angular
En el componente FormularioItemComponent, usaste HttpClient para:

Enviar la imagen al backend vía POST.

Mostrar una vista previa (imagenPreview).

Obtener automáticamente el título detectado por Azure y asignarlo al modelo item.titulo.

5. Manejo visual
Mostrás la imagen procesada en el modal de confirmación (itemCreado.urlImagen).

También reutilizás esa URL para visualizarla en ListaItemsComponent.

✅ Resultado Final
El usuario sube una imagen desde el frontend.

Azure extrae el texto con Computer Vision.

La imagen se guarda en blob storage.

El título sugerido se completa automáticamente.

Se puede ver la imagen luego al consultar la lista o detalles del ítem.

