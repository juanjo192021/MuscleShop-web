package com.muscleshop.web.models.dto;

public class ProductoCarritoDto {
    private int productoId;
    private int productoPropiedadDetalleId;
    private String nombreProducto;
    private String imagenProducto;
    private String nombrePropiedadDetalle;
    private String nombrePropiedadDetalle2;
    private Integer cantidadProducto;
    private Double precio;
    private Double precioReducido;

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getProductoPropiedadDetalleId() {
        return productoPropiedadDetalleId;
    }

    public void setProductoPropiedadDetalleId(int productoPropiedadDetalleId) {
        this.productoPropiedadDetalleId = productoPropiedadDetalleId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public String getNombrePropiedadDetalle() {
        return nombrePropiedadDetalle;
    }

    public void setNombrePropiedadDetalle(String nombrePropiedadDetalle) {
        this.nombrePropiedadDetalle = nombrePropiedadDetalle;
    }

    public String getNombrePropiedadDetalle2() {
        return nombrePropiedadDetalle2;
    }

    public void setNombrePropiedadDetalle2(String nombrePropiedadDetalle2) {
        this.nombrePropiedadDetalle2 = nombrePropiedadDetalle2;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecioReducido() {
        return precioReducido;
    }

    public void setPrecioReducido(Double precioReducido) {
        this.precioReducido = precioReducido;
    }
}
