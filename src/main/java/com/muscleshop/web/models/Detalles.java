package com.muscleshop.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "detalles")
public class Detalles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;

	@OneToMany(mappedBy = "detalles")
	@JsonIgnore
	private List<PropiedadesDetalles> propiedadesDetalles ;

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

	public List<PropiedadesDetalles> getPropiedadesDetalles() {
		return propiedadesDetalles;
	}

	public void setPropiedadesDetalles(List<PropiedadesDetalles> propiedadesDetalles) {
		this.propiedadesDetalles = propiedadesDetalles;
	}
}
