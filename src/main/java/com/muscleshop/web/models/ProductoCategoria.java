package com.muscleshop.web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto_categoria")
public class ProductoCategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String url;

	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;


	@ManyToOne
	@JoinColumn(name = "menu_sub_id")
	private MenuSub menuSub;

	public ProductoCategoria() {
	}

	public ProductoCategoria(String nombre, String url, Estado estado, MenuSub menuSub) {
		super();
		this.nombre = nombre;
		this.url = url;
		this.estado = estado;
		this.menuSub = menuSub;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public MenuSub getMenuSub() {
		return menuSub;
	}

	public void setMenuSub(MenuSub menuSub) {
		this.menuSub = menuSub;
	}
	
	
	

}
