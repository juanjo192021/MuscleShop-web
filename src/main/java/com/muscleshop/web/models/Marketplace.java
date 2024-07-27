package com.muscleshop.web.models;

import jakarta.persistence.*;

@Entity
@Table(name = "marketplace")
public class Marketplace{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String imagen;

    private String url;

    @OneToOne()
    @JoinColumn(name="estado_id")
    private Estado estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Marketplace() {
    }

    public Marketplace(String nombre,String imagen, String url, Estado estado) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.url = url;
        this.estado = estado;
    }
}