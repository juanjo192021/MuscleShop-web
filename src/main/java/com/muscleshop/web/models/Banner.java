package com.muscleshop.web.models;

import jakarta.persistence.*;

@Entity
@Table(name = "banner")
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="nombre", nullable = false)
	private String nombre;

	@Column(name="tipo_dispositivo", nullable = false)
	private String tipoDispositivo;

	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	public Banner() {
	}

	public Banner(String nombre,String tipoDispositivo , Estado estado) {
		super();
		this.nombre = nombre;
		this.tipoDispositivo = tipoDispositivo;
		this.estado = estado;
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

	public String getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
