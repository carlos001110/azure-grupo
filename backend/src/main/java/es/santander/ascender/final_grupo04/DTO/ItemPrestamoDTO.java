package es.santander.ascender.final_grupo04.DTO;

public class ItemPrestamoDTO {

    private Long id;
    private String titulo;
    private String ubicacion;

    public ItemPrestamoDTO(Long id, String titulo, String ubicacion) {
        this.id = id;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
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

}
