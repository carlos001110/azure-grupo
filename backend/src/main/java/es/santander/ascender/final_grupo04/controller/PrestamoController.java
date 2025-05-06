package es.santander.ascender.final_grupo04.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.final_grupo04.DTO.PrestamoResponseDTO;
import es.santander.ascender.final_grupo04.service.PrestamoService;

@RestController
@RequestMapping("/api/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<PrestamoResponseDTO> crearPrestamo(@RequestParam Long itemId,
            @RequestParam String persona,
            @RequestParam LocalDate fechaPrevistaDevolucion) {
        PrestamoResponseDTO prestamo = prestamoService.crearPrestamo(itemId, persona, fechaPrevistaDevolucion);
        return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<PrestamoResponseDTO> devolverItem(@PathVariable Long id) {
        PrestamoResponseDTO devolucionPrestamo = prestamoService.devolverItem(id);
        if (devolucionPrestamo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(devolucionPrestamo, HttpStatus.OK);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PrestamoResponseDTO>> listarPrestamosActivos(
            @RequestParam(required = false) String persona,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {
        List<PrestamoResponseDTO> prestamos = prestamoService.listarPrestamosActivos(persona, fechaDesde, fechaHasta);
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    @GetMapping("/historial")
    public ResponseEntity<List<PrestamoResponseDTO>> listarHistorialCompleto() {
        List<PrestamoResponseDTO> historial = prestamoService.listarTodos();
        return new ResponseEntity<>(historial, HttpStatus.OK);
    }

}
