package com.muscleshop.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "producto_propiedades_detalles")
public class ProductoPropiedadDetalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double precio;
	private Integer stock;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	@JsonIgnore
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "propiedades_detalles_id")
	private PropiedadesDetalles propiedadesDetalles;

	@OneToMany(mappedBy = "productoPropiedadDetalle")
	private List<PropiedadDetalleImagen> propiedadesDetallesImagenes;

	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado;

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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<PropiedadDetalleImagen> getPropiedadesDetallesImagenes() {
		return propiedadesDetallesImagenes;
	}

	public void setPropiedadesDetallesImagenes(List<PropiedadDetalleImagen> propiedadesDetallesImagenes) {
		this.propiedadesDetallesImagenes = propiedadesDetallesImagenes;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
