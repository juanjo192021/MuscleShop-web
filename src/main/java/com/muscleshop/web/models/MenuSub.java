package com.muscleshop.web.models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "menu_sub")
public class MenuSub {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String url;
	private String imagen;
	private String banner;


	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;

	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;


	@OneToMany(mappedBy = "menuSub")
	@JsonIgnore
	private List<ProductoCategoria> productoCate;

	@OneToMany(mappedBy = "menuSub", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductoMenuSub> productoMenuSub;

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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<ProductoCategoria> getProductoCate() {
		return productoCate;
	}

	public void setProductoCate(List<ProductoCategoria> productoCate) {
		this.productoCate = productoCate;
	}

	public List<ProductoMenuSub> getProductoMenuSub() {
		return productoMenuSub;
	}

	public void setProductoMenuSub(List<ProductoMenuSub> productoMenuSub) {
		this.productoMenuSub = productoMenuSub;
	}

	public MenuSub(){}

	public MenuSub(String nombre, String url, String imagen, Menu menu, String banner, Estado estado, List<ProductoMenuSub> productoMenuSub, List<ProductoCategoria> productoCate) {
		this.nombre = nombre;
		this.url = url;
		this.imagen = imagen;
		this.menu = menu;
		this.banner = banner;
		this.estado = estado;
		this.productoMenuSub = productoMenuSub;
		this.productoCate = productoCate;
	}
}
