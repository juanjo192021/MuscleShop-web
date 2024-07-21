package com.muscleshop.web.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String url;
	private String imagen;
	private String descripcion;

	@ManyToOne
	@JoinColumn(name="menu_tipo_id")
	private MenuTipo menuTipo;
	
	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@OneToMany(mappedBy = "menu")
	private List<MenuSub> menuSub;

	public Menu() {
	}

	public Menu(String nombre, String url, String imagen, String descripcion, MenuTipo menuTipo, Estado estado,
			List<MenuSub> menuSub) {
		super();
		this.nombre = nombre;
		this.url = url;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.menuTipo = menuTipo;
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

	public List<MenuSub> getMenuSub() {
		return menuSub;
	}

	public void setMenuSub(List<MenuSub> menuSub) {
		this.menuSub = menuSub;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public MenuTipo getMenuTipo() {
		return menuTipo;
	}

	public void setMenuTipo(MenuTipo menuTipo) {
		this.menuTipo = menuTipo;
	}

}
