package es.santander.ascender.final_grupo04.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ItemResponseDTO {

    private Long id;
    private String titulo;
    private String ubicacion;
    private boolean estado;
    private String tipo; // Nombre del tipo
    private String formato; // Nombre del formato
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAdquisicion;
    private String urlImagen;

    public ItemResponseDTO(Long id, String titulo, String ubicacion, boolean estado,
            String tipo, String formato, LocalDate fechaAdquisicion, String urlImagen) {
        this.id = id;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.tipo = tipo;
        this.formato = formato;
        this.fechaAdquisicion = fechaAdquisicion;
        this.urlImagen = urlImagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
