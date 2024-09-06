package com.muscleshop.web.models;

import jakarta.persistence.*;

@Entity
@Table(name = "agrupacion_imagen")
public class AgrupacionImagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToOne()
    @JoinColumn(name="agrupacion_id")
    private Agrupacion agrupacion;

    @OneToOne()
    @JoinColumn(name="estado_id")
    private Estado estado;
}
