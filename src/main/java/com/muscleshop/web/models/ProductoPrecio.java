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
@Table(name = "producto_precio")
public class ProductoPrecio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private double precio;

	private double precio_tachado;

	private Integer porcentaje;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	@JsonIgnore
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "rol_perfil_id")
	private RolPerfil rolPerfil;

	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public RolPerfil getRolPerfil() {
		return rolPerfil;
	}

	public void setRolPerfil(RolPerfil rolPerfil) {
		this.rolPerfil = rolPerfil;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public double getPrecio_tachado() {
		return precio_tachado;
	}

	public void setPrecio_tachado(double precio_tachado) {
		this.precio_tachado = precio_tachado;
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}
	

}
