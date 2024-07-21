package com.muscleshop.web.models;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
	private List<ProductoCategoria> productoCate;
	
	@ManyToMany(mappedBy = "menuSub")
	private Set<Producto> producto;
	
	public MenuSub() {
	}

	public MenuSub(String nombre, String url, String imagen, Menu menu, Estado estado,
			List<ProductoCategoria> productoCate, Set<Producto> producto) {
		super();
		this.nombre = nombre;
		this.url = url;
		this.imagen = imagen;
		this.menu = menu;
		this.estado = estado;
		this.productoCate = productoCate;
		this.producto = producto;
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

	public Set<Producto> getProducto() {
		return producto;
	}

	public void setProducto(Set<Producto> producto) {
		this.producto = producto;
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
	

}
