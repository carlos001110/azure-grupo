package es.santander.ascender.final_grupo04.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tipo_formato",
            joinColumns = @JoinColumn(name = "tipo_id"),
            inverseJoinColumns = @JoinColumn(name = "formato_id")
    )
    private List<Formato> formatos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private List<Item> items;

    public Tipo() {
    }

    public Tipo(Long id, String nombre, List<Formato> formatos, List<Item> items) {
        this.id = id;
        this.nombre = nombre;
        this.formatos = formatos;
        this.items = items;
    }

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

    public List<Formato> getFormato() {
        return formatos;
    }

    public void setFormato(List<Formato> formatos) {
        this.formatos = formatos;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
