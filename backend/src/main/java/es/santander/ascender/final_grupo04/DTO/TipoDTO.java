package es.santander.ascender.final_grupo04.DTO;

import java.util.List;

public class TipoDTO {

    private String nombre;
    private List<Long> formatoIds;  // Lista de IDs de los formatos asociados al tipo

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getFormatoIds() {
        return formatoIds;
    }

    public void setFormatoIds(List<Long> formatoIds) {
        this.formatoIds = formatoIds;
    }
}

