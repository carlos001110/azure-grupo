package es.santander.ascender.final_grupo04.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.final_grupo04.DTO.TipoDTO;
import es.santander.ascender.final_grupo04.DTO.TipoFormatoDTO;
import es.santander.ascender.final_grupo04.model.Formato;
import es.santander.ascender.final_grupo04.model.Tipo;
import es.santander.ascender.final_grupo04.repository.FormatoRepository;
import es.santander.ascender.final_grupo04.repository.TipoRepository;

@Transactional
@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private FormatoRepository formatoRepository;

    @Transactional
    public TipoDTO crearTipo(TipoDTO tipoDTO) {
        // Verificar si ya existe un tipo con el mismo nombre
        if (tipoRepository.findByNombre(tipoDTO.getNombre()).isPresent()) {
            throw new RuntimeException("El tipo con el nombre '" + tipoDTO.getNombre() + "' ya existe.");
        }
        // Crear el nuevo tipo
        Tipo tipo = new Tipo();
        tipo.setNombre(tipoDTO.getNombre());

        // Recuperar los formatos por sus IDs
        List<Formato> formatos = tipoDTO.getFormatoIds().stream()
                .map(id -> formatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formato con ID " + id + " no encontrado")))
                .collect(Collectors.toList());

        // Asociar los formatos al tipo
        tipo.setFormato(formatos);

        // Guardar el tipo
        Tipo savedTipo = tipoRepository.save(tipo);

        // Construir el DTO de respuesta
        TipoDTO responseDTO = new TipoDTO();
        responseDTO.setNombre(savedTipo.getNombre());
        responseDTO.setFormatoIds(
                savedTipo.getFormato().stream().map(Formato::getId).collect(Collectors.toList())
        );

        return responseDTO;
    }

    @Transactional(readOnly = true)
    public List<TipoFormatoDTO> listarTipos() {
        // Asegúrate de cargar todos los tipos y sus formatos asociados
        return tipoRepository.findAll().stream()
                .map(tipo -> new TipoFormatoDTO(
                tipo.getId(),
                tipo.getNombre(),
                tipo.getFormato().stream()
                        .map(Formato::getNombre) // Extrae solo los nombres de los formatos
                        .collect(Collectors.toList())
        ))
                .collect(Collectors.toList());
    }

    @Transactional
    public Tipo actualizarTipo(Long id, String nombre) {
        Optional<Tipo> tipoOptional = tipoRepository.findById(id);
        if (tipoOptional.isPresent()) {
            Tipo tipo = tipoOptional.get();

            // Asegurarse de que la colección de formatos sea mutable (en caso de que sea inmutable)
            if (!(tipo.getFormato() instanceof java.util.ArrayList)) {
                tipo.setFormato(new ArrayList<>(tipo.getFormato()));  // Convertir a una lista mutable si no lo es
            }

            tipo.setNombre(nombre); // Actualizar el nombre
            return tipoRepository.save(tipo); // Guardar el tipo con el nuevo nombre
        }
        throw new RuntimeException("Tipo no encontrado");
    }

    public List<String> obtenerFormatosPorTipo(Long tipoId) {
        Tipo tipo = tipoRepository.findById(tipoId)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));

        // Obtener los nombres de los formatos asociados al tipo
        return tipo.getFormato().stream()
                .map(Formato::getNombre)
                .collect(Collectors.toList());
    }

    @Transactional
    public void eliminarTipo(Long id) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));

        if (tipo.getItems() != null && !tipo.getItems().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el tipo porque tiene ítems asociados.");
        }

        tipoRepository.delete(tipo);
    }

}
