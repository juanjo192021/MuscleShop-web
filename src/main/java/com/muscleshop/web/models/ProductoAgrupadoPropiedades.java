package com.muscleshop.web.models;

import jakarta.persistence.*;

@Entity
@Table(name="producto_agrupado_propiedades")
public class ProductoAgrupadoPropiedades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "producto_agrupado_id")
    private ProductoAgrupado productoAgrupado;

    @ManyToOne
    @JoinColumn(name = "producto_propiedades_detalles_id")
    private ProductoPropiedadesDetalles productoPropiedadesDetalles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductoAgrupado getProductoAgrupado() {
        return productoAgrupado;
    }

    public void setProductoAgrupado(ProductoAgrupado productoAgrupado) {
        this.productoAgrupado = productoAgrupado;
    }

    public ProductoPropiedadesDetalles getProductoPropiedadesDetalles() {
        return productoPropiedadesDetalles;
    }

    public void setProductoPropiedadesDetalles(ProductoPropiedadesDetalles productoPropiedadesDetalles) {
        this.productoPropiedadesDetalles = productoPropiedadesDetalles;
    }
}
