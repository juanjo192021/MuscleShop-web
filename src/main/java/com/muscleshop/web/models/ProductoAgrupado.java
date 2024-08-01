package com.muscleshop.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "producto_agrupado")
public class ProductoAgrupado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

/*    @ManyToOne
    @JoinColumn(name = "producto_padre_id", nullable = false)
    @JsonIgnore
    private Producto productoPadre;

    *//*Tener mapeado esto*//*
    @ManyToOne
    @JoinColumn(name = "producto_hijo_id", nullable = false)
    @JsonIgnore
    private Producto productoHijo;*/
    @ManyToOne
    @JoinColumn(name = "producto_padre_id")
    private Producto productoPadre;

    @ManyToOne
    @JoinColumn(name = "producto_hijo_id")
    private Producto productoHijo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProductoPadre() {
        return productoPadre;
    }

    public void setProductoPadre(Producto productoPadre) {
        this.productoPadre = productoPadre;
    }

    public Producto getProductoHijo() {
        return productoHijo;
    }

    public void setProductoHijo(Producto productoHijo) {
        this.productoHijo = productoHijo;
    }
}
