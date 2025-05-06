package es.santander.ascender.final_grupo04.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ItemDTO {

    private String titulo;
    private String ubicacion;
    private Long tipoId; // ID del tipo asociado
    private String formato;
    private String urlImagen;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAdquisicion; // Formato seleccionado (por ejemplo, "CD" o "Vinilo")

    public ItemDTO(String titulo, String ubicacion, Long tipoId, String formato, String urlImagen,
            LocalDate fechaAdquisicion) {
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.tipoId = tipoId;
        this.formato = formato;
        this.urlImagen = urlImagen;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
