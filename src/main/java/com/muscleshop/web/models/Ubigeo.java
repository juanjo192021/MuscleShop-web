package com.muscleshop.web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ubigeo")
public class Ubigeo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String region;
	private String provincia;
	private String distrito;
	private Integer costo;
	
	
	public Ubigeo() {
	}

	public Ubigeo(String region, String provincia, String distrito, Integer costo) {
		super();
		this.region = region;
		this.provincia = provincia;
		this.distrito = distrito;
		this.costo = costo;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}



	public Integer getCosto() {
		return costo;
	}



	public void setCosto(Integer costo) {
		this.costo = costo;
	}


	
	

}
