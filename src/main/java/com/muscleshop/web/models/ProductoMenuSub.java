package com.muscleshop.web.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="producto_menu_sub")
public class ProductoMenuSub implements Serializable {

/*
    private static final long serialVersionUID = 1L;
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name="producto_id")
    private Producto producto;

    @ManyToOne()
    @JoinColumn(name="menu_sub_id")
    private MenuSub menuSub;

    @OneToOne()
    @JoinColumn(name="recomendado_producto_menu_sub_id")
    private RecomendadoProductoMenuSub recomendadoProductoMenuSub;

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

    public MenuSub getMenuSub() {
        return menuSub;
    }

    public void setMenuSub(MenuSub menuSub) {
        this.menuSub = menuSub;
    }

    public RecomendadoProductoMenuSub getRecomendadoProductoMenuSub() {
        return recomendadoProductoMenuSub;
    }

    public void setRecomendadoProductoMenuSub(RecomendadoProductoMenuSub recomendadoProductoMenuSub) {
        this.recomendadoProductoMenuSub = recomendadoProductoMenuSub;
    }

    public ProductoMenuSub(){}

    public ProductoMenuSub(Integer id, Producto producto, MenuSub menuSub, RecomendadoProductoMenuSub recomendadoProductoMenuSub) {
        this.id = id;
        this.producto = producto;
        this.menuSub = menuSub;
        this.recomendadoProductoMenuSub = recomendadoProductoMenuSub;
    }
}