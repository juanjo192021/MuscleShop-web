package com.muscleshop.web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto_informacion")
public class ProductoInformacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String beneficios;
	private String uso_sugerido;
	private String info_nutricional;
	private String ingredientes;
	private String precauciones;
	private String descripcion;
	private String url;
	
	@OneToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	public ProductoInformacion() {
	}

	public ProductoInformacion(String beneficios, String uso_sugerido, String info_nutricional, String ingredientes,
			String precauciones, String descripcion, String url, Producto producto) {
		super();
		this.beneficios = beneficios;
		this.uso_sugerido = uso_sugerido;
		this.info_nutricional = info_nutricional;
		this.ingredientes = ingredientes;
		this.precauciones = precauciones;
		this.descripcion = descripcion;
		this.url = url;
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public String getUso_sugerido() {
		return uso_sugerido;
	}

	public void setUso_sugerido(String uso_sugerido) {
		this.uso_sugerido = uso_sugerido;
	}

	public String getInfo_nutricional() {
		return info_nutricional;
	}

	public void setInfo_nutricional(String info_nutricional) {
		this.info_nutricional = info_nutricional;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getPrecauciones() {
		return precauciones;
	}

	public void setPrecauciones(String precauciones) {
		this.precauciones = precauciones;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	


	
	
}
