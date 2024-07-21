package com.muscleshop.web.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "propiedades")
public class Propiedades {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	
	@OneToMany(mappedBy = "propiedades")
	private List<PropiedadesDetalles> propiedadesDetalles;
	
	public Propiedades() {

	}

	public Propiedades(String nombre, List<PropiedadesDetalles> propiedadesDetalles) {
		super();
		this.nombre = nombre;
		this.propiedadesDetalles = propiedadesDetalles;
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

	public List<PropiedadesDetalles> getPropiedadesDetalles() {
		return propiedadesDetalles;
	}

	public void setPropiedadesDetalles(List<PropiedadesDetalles> propiedadesDetalles) {
		this.propiedadesDetalles = propiedadesDetalles;
	}
	
	
	
	
	
	

	
	

}
