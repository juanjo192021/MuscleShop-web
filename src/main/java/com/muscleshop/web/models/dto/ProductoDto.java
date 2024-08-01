package com.muscleshop.web.models.dto;

import java.util.List;
import java.util.Map;

public class ProductoDto {
    private Integer id;
    private String nombre;
    private String imagen;
    private String nombreCategoria;
    private String urlCategoria;
    private String nombreMenuSub;
    private Map<String, List<String>> propiedadesDetallesMap;

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

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getUrlCategoria() {
        return urlCategoria;
    }

    public void setUrlCategoria(String urlCategoria) {
        this.urlCategoria = urlCategoria;
    }

    public String getNombreMenuSub() {
        return nombreMenuSub;
    }

    public void setNombreMenuSub(String nombreMenuSub) {
        this.nombreMenuSub = nombreMenuSub;
    }

    public Map<String, List<String>> getPropiedadesDetallesMap() {
        return propiedadesDetallesMap;
    }

    public void setPropiedadesDetallesMap(Map<String, List<String>> propiedadesDetallesMap) {
        this.propiedadesDetallesMap = propiedadesDetallesMap;
    }
}
