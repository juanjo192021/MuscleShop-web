package com.muscleshop.web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "logos")
public class Logos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String header;
	private String footer1;
	private String footer2;
	private String login;
	private String registro;
	private String boleta;
	
	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	public Logos() {
	}

	public Logos(String header, String footer1, String footer2, String login, String registro, String boleta,
			Estado estado) {
		super();
		this.header = header;
		this.footer1 = footer1;
		this.footer2 = footer2;
		this.login = login;
		this.registro = registro;
		this.boleta = boleta;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter1() {
		return footer1;
	}

	public void setFooter1(String footer1) {
		this.footer1 = footer1;
	}

	public String getFooter2() {
		return footer2;
	}

	public void setFooter2(String footer2) {
		this.footer2 = footer2;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getBoleta() {
		return boleta;
	}

	public void setBoleta(String boleta) {
		this.boleta = boleta;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
