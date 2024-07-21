package com.muscleshop.web.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cupon_categoria")
public class CuponCategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	
	@OneToMany(mappedBy = "cuponCate")
	private List<Cupon> cupon;

	public CuponCategoria() {
	}

	public CuponCategoria(String nombre, List<Cupon> cupon) {
		super();
		this.nombre = nombre;
		this.cupon = cupon;
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

	public List<Cupon> getCupon() {
		return cupon;
	}

	public void setCupon(List<Cupon> cupon) {
		this.cupon = cupon;
	}

}
