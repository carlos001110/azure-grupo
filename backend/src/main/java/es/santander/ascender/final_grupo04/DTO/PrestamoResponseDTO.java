package es.santander.ascender.final_grupo04.DTO;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PrestamoResponseDTO {

    private Long id;
    private String persona;
         @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPrestamo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPrevistaDevolucion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDevolucion;
    private boolean activo;
    private List<ItemPrestamoDTO> items;

    public PrestamoResponseDTO(Long id, String persona, LocalDate fechaPrestamo,
            LocalDate fechaPrevistaDevolucion, LocalDate fechaDevolucion,
            boolean activo, List<ItemPrestamoDTO> items) {
        this.id = id;
        this.persona = persona;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.activo = activo;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaPrevistaDevolucion() {
        return fechaPrevistaDevolucion;
    }

    public void setFechaPrevistaDevolucion(LocalDate fechaPrevistaDevolucion) {
        this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<ItemPrestamoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPrestamoDTO> items) {
        this.items = items;
    }

}
