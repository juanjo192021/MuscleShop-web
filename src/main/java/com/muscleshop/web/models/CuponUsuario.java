package com.muscleshop.web.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cupon_usuario")
public class CuponUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate fecha;

	@OneToOne
	@JoinColumn(name = "cupon_id")
	private Cupon cupon;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public CuponUsuario() {
	}

	public CuponUsuario(LocalDate fecha, Cupon cupon, Usuario usuario) {
		super();
		this.fecha = fecha;
		this.cupon = cupon;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cupon getCupon() {
		return cupon;
	}

	public void setCupon(Cupon cupon) {
		this.cupon = cupon;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
