package com.muscleshop.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "propiedades_detalles")
public class PropiedadesDetalles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "detalles_id")
	private Detalles detalles;

	@ManyToOne
	@JoinColumn(name = "propiedades_id")
	private Propiedades propiedades;

	@OneToMany(mappedBy = "propiedadesDetalles")
	@JsonIgnore
	private List<ProductoPropiedadDetalle> productoPropiedadesDetalles ;

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

	public List<ProductoPropiedadDetalle> getProductoPropiedadesDetalles() {
		return productoPropiedadesDetalles;
	}

	public void setProductoPropiedadesDetalles(List<ProductoPropiedadDetalle> productoPropiedadesDetalles) {
		this.productoPropiedadesDetalles = productoPropiedadesDetalles;
	}
}
