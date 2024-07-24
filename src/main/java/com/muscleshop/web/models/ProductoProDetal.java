package com.muscleshop.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto_propiedades_detalles")
public class ProductoProDetal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double precio;

	@OneToOne
	@JoinColumn(name = "producto_id")
	@JsonIgnore
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "propiedades_detalles_id")
	private PropiedadesDetalles propiedadesDetalles;

	public ProductoProDetal() {
	}

	public ProductoProDetal(Double precio, Producto producto, PropiedadesDetalles propiedadesDetalles) {
		super();
		this.precio = precio;
		this.producto = producto;
		this.propiedadesDetalles = propiedadesDetalles;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public PropiedadesDetalles getPropiedadesDetalles() {
		return propiedadesDetalles;
	}

	public void setPropiedadesDetalles(PropiedadesDetalles propiedadesDetalles) {
		this.propiedadesDetalles = propiedadesDetalles;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	
}
