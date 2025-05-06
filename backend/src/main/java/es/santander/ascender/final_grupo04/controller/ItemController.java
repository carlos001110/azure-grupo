package es.santander.ascender.final_grupo04.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.santander.ascender.final_grupo04.DTO.ItemDTO;
import es.santander.ascender.final_grupo04.DTO.ItemResponseDTO;
import es.santander.ascender.final_grupo04.service.AzureBlobService;
import es.santander.ascender.final_grupo04.service.AzureVisionService;
import es.santander.ascender.final_grupo04.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AzureBlobService azureBlobService;

    @Autowired
    private AzureVisionService azureVisionService;

    /**
     * Crea un nuevo ítem con formato y tipo validados.
     */
    @PostMapping
    public ResponseEntity<ItemResponseDTO> crearItem(@Valid @RequestBody ItemDTO itemDTO) {
        ItemResponseDTO createdItem = itemService.crearItemConFormato(itemDTO);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    /**
     * Lista todos los ítems disponibles.
     */
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> listarItemsDisponibles() {
        List<ItemResponseDTO> items = itemService.listarItemDisponibles();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    /**
     * Busca ítems con filtros y paginación.
     */
    @GetMapping("/buscar")
    public ResponseEntity<Page<ItemResponseDTO>> buscarItemsPaginado(
            @RequestParam(defaultValue = "") String titulo,
            @RequestParam(defaultValue = "") String tipo,
            @RequestParam(defaultValue = "") String ubicacion,
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable
    ) {
        Page<ItemResponseDTO> result = itemService.buscarItemsPaginado(titulo, tipo, ubicacion, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Actualiza un ítem existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> actualizarItem(@PathVariable Long id, @Valid @RequestBody ItemDTO itemDTO) {
        ItemResponseDTO updatedItem = itemService.actualizarItem(id, itemDTO);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    /**
     * Elimina un ítem por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long id) {
        itemService.eliminarItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/procesar-imagen", consumes = "multipart/form-data")
    @Operation(summary = "Sube imagen, extrae título con OCR y devuelve URL y texto")
    public ResponseEntity<?> procesarImagenCompleta(@RequestPart("file") MultipartFile file) {
        try {
            System.out.println("📥 Llamando a uploadFile en AzureBlobService...");
            String urlImagen = azureBlobService.uploadFile(file);

            System.out.println("🧠 Llamando a Vision API con imagen binaria...");
            String tituloExtraido = azureVisionService.extraerTextoDesdeArchivo(file);

            return ResponseEntity.ok(Map.of(
                    "titulo", tituloExtraido,
                    "urlImagen", urlImagen
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al procesar imagen: " + e.getMessage()));
        }
    }

}
