package com.muscleshop.web.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto_forma")
public class ProductoForma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	
	@ManyToMany(mappedBy = "productoForma")
	private Set<Producto> producto;

	public ProductoForma() {
	}

	public ProductoForma(String nombre, Set<Producto> producto) {
		super();
		this.nombre = nombre;
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

	public Set<Producto> getProducto() {
		return producto;
	}

	public void setProducto(Set<Producto> producto) {
		this.producto = producto;
	}
	
	

}
