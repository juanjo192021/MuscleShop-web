package com.muscleshop.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "propiedad_detalle_imagen")
public class PropiedadDetalleImagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name="producto_propiedad_detalle_id")
    @JsonIgnore
    private ProductoPropiedadDetalle productoPropiedadDetalle;

    @ManyToOne
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

    public ProductoPropiedadDetalle getProductoPropiedadDetalle() {
        return productoPropiedadDetalle;
    }

    public void setProductoPropiedadDetalle(ProductoPropiedadDetalle productoPropiedadDetalle) {
        this.productoPropiedadDetalle = productoPropiedadDetalle;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
