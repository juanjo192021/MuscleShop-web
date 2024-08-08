package com.muscleshop.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "banner_menu_sub")
public class BannerMenuSub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imagen;

    @ManyToOne()
    @JoinColumn(name="menu_sub_id")
    @JsonIgnore
    private MenuSub menuSub;

    @Column(name="tipo_dispositivo", nullable = false)
    private String tipoDispositivo;

    public BannerMenuSub() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public MenuSub getMenuSub() {
        return menuSub;
    }

    public void setMenuSub(MenuSub menuSub) {
        this.menuSub = menuSub;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

}
