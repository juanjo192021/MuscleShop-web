package com.muscleshop.web.models.dto;

public class PropiedadDetalleDto {
    private String detalle;
    private Double precio;
    private Integer stock;

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public PropiedadDetalleDto(String detalle, Double precio, Integer stock) {
        this.detalle = detalle;
        this.precio = precio;
        this.stock = stock;
    }
}
