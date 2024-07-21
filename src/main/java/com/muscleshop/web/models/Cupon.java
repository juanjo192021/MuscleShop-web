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
@Table(name = "cupon")
public class Cupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String valor;

	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "cupon_categoria_id")
	private CuponCategoria cuponCate;

	public Cupon() {
	}

	public Cupon(String nombre, String valor, Estado estado, CuponCategoria cuponCate) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.estado = estado;
		this.cuponCate = cuponCate;
	}

	public Cupon(String nombre, String valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
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

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public CuponCategoria getCuponCate() {
		return cuponCate;
	}

	public void setCuponCate(CuponCategoria cuponCate) {
		this.cuponCate = cuponCate;
	}

}
