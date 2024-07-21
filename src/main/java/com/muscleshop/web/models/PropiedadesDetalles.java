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
@Table(name = "propiedades_detalles")
public class PropiedadesDetalles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "detalles_id")
	private Detalles detalles;

	@ManyToOne
	@JoinColumn(name = "propiedades_id")
	private Propiedades propiedades;
	

	public PropiedadesDetalles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PropiedadesDetalles(Detalles detalles, Propiedades propiedades) {
		super();
		this.detalles = detalles;
		this.propiedades = propiedades;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Detalles getDetalles() {
		return detalles;
	}

	public void setDetalles(Detalles detalles) {
		this.detalles = detalles;
	}

	public Propiedades getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Propiedades propiedades) {
		this.propiedades = propiedades;
	}

	


}
