package es.santander.ascender.final_grupo04.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 100)
    private String titulo;

    private String ubicacion;

    @Column(name = "url_imagen")
    private String urlImagen;

    @Column(name = "fecha_adquisicion")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate FechaAdquisicion;

    private boolean estado = true; // Por defecto, los ítems son "Disponibles"

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "formato_id", nullable = false)  // Nueva relación con Formato
    private Formato formato;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;

    public Item() {
    }

    public Item(Long id, String titulo, String ubicacion, LocalDate fechaAdquisicion,
            boolean estado, Tipo tipo, Formato formato, Prestamo prestamo, String urlImagen) {
        this.id = id;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.FechaAdquisicion = fechaAdquisicion;
        this.estado = estado;
        this.tipo = tipo;
        this.formato = formato;
        this.prestamo = prestamo;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public LocalDate getFechaAdquisicion() {
        return FechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        FechaAdquisicion = fechaAdquisicion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

}
