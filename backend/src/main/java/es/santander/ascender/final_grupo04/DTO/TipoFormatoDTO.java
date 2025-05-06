package es.santander.ascender.final_grupo04.DTO;

import java.util.List;

public class TipoFormatoDTO {

    private Long id;
    private String nombre;
    private List<String> formatos; // Nombres de los formatos asociados

    public TipoFormatoDTO(Long id, String nombre, List<String> formatos) {
        this.id = id;
        this.nombre = nombre;
        this.formatos = formatos;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getFormatos() {
        return formatos;
    }

    public void setFormatos(List<String> formatos) {
        this.formatos = formatos;
    }
}
