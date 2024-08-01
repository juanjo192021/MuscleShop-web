package com.muscleshop.web.models;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_forma_producto")
public class ProductoFormaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="producto_id")
    private Producto producto;

    @ManyToOne()
    @JoinColumn(name="producto_forma_id")
    private ProductoForma productoForma;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoForma getProductoForma() {
        return productoForma;
    }

    public void setProductoForma(ProductoForma productoForma) {
        this.productoForma = productoForma;
    }
}
