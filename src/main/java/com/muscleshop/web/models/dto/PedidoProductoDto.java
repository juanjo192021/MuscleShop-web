package com.muscleshop.web.models.dto;

public class PedidoProductoDto {
    private Integer id;
    private Integer productoId;
    private Integer productoPropiedadesDetallesId;
    private Integer cantidad;
    private double sub_total;
    private ProductoCarritoDto productoCarrito;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getProductoPropiedadesDetallesId() {
        return productoPropiedadesDetallesId;
    }

    public void setProductoPropiedadesDetallesId(Integer productoPropiedadesDetallesId) {
        this.productoPropiedadesDetallesId = productoPropiedadesDetallesId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public ProductoCarritoDto getProductoCarrito() {
        return productoCarrito;
    }

    public void setProductoCarrito(ProductoCarritoDto productoCarrito) {
        this.productoCarrito = productoCarrito;
    }
}
