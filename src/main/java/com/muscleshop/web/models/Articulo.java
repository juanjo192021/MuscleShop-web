package com.muscleshop.web.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String imagen;
	private String titulo;
	private String descripcion;

	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@OneToMany(mappedBy = "articulo")
	private List<ArticuloComentario> articuloCom;

	public Articulo() {
	}

	public Articulo(String imagen, String titulo, String descripcion, Estado estado,
			List<ArticuloComentario> articuloCom) {
		super();
		this.imagen = imagen;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.articuloCom = articuloCom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<ArticuloComentario> getArticuloCom() {
		return articuloCom;
	}

	public void setArticuloCom(List<ArticuloComentario> articuloCom) {
		this.articuloCom = articuloCom;
	}

}
