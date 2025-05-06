package es.santander.ascender.final_grupo04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.final_grupo04.DTO.TipoDTO;
import es.santander.ascender.final_grupo04.DTO.TipoFormatoDTO;
import es.santander.ascender.final_grupo04.model.Tipo;
import es.santander.ascender.final_grupo04.service.TipoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipo")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @PostMapping
    public ResponseEntity<TipoDTO> crearTipo(@RequestBody TipoDTO tipoDTO) {
        try {
            TipoDTO createdTipo = tipoService.crearTipo(tipoDTO);
            return new ResponseEntity<>(createdTipo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<TipoFormatoDTO>> listarTipos() {
        List<TipoFormatoDTO> tipos = tipoService.listarTipos();
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> actualizarTipo(@PathVariable Long id, @Valid @RequestBody String nombre) {
        Tipo updatedTipo = tipoService.actualizarTipo(id, nombre);
        if (updatedTipo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTipo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipo(@PathVariable Long id) {
        tipoService.eliminarTipo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/formatos/{tipoId}")
    public ResponseEntity<List<String>> obtenerFormatosPorTipo(@PathVariable Long tipoId) {
        List<String> formatos = tipoService.obtenerFormatosPorTipo(tipoId);
        if (formatos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(formatos, HttpStatus.OK);
    }
}
