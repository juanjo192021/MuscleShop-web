package com.muscleshop.web.models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "producto_forma")
public class ProductoForma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;

	@OneToMany(mappedBy = "productoForma", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductoFormaProducto> productoFormaProducto;

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

	public List<ProductoFormaProducto> getProductoFormaProducto() {
		return productoFormaProducto;
	}

	public void setProductoFormaProducto(List<ProductoFormaProducto> productoFormaProducto) {
		this.productoFormaProducto = productoFormaProducto;
	}
}
