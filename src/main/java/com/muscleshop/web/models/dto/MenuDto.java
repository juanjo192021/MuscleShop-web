package com.muscleshop.web.models.dto;

public class MenuDto {

    private Integer id;
    private String nombre;
    private String url;
    private String imagen;
    private String nombreMenuTipo;
    private String estado;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreMenuTipo() {
        return nombreMenuTipo;
    }

    public void setNombreMenuTipo(String nombreMenuTipo) {
        this.nombreMenuTipo = nombreMenuTipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
