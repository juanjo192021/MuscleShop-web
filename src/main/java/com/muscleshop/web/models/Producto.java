package com.muscleshop.web.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String imagen;
	private String descripcion;
	private LocalDate fecha;
/*	private Integer stock;*/

	@OneToOne
	@JoinColumn(name = "categoria_id")
	private ProductoCategoria categoria;

	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@OneToMany(mappedBy = "producto")
	private List<ProductoImagen> productoImg;
	
	@OneToMany(mappedBy = "producto")
	private List<ProductoPrecio> productoPre;


	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductoMenuSub> productoMenuSub;

	@OneToMany(mappedBy = "producto")
	private List<ProductoPropiedadDetalle> proPropiDetal ;


	@OneToOne(mappedBy  = "producto")
	private ProductoInformacion productoInfo;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "producto_forma_producto", joinColumns = { @JoinColumn(name = "producto_id") }, inverseJoinColumns = {
			@JoinColumn(name = "producto_forma_id") })
	private Set<ProductoForma> productoForma;

	@OneToOne()
	@JoinColumn(name="agrupacion_id")
	private Agrupacion agrupacion;
	
	public Producto() {
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/*public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}*/

	public ProductoCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(ProductoCategoria categoria) {
		this.categoria = categoria;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<ProductoImagen> getProductoImg() {
		return productoImg;
	}

	public void setProductoImg(List<ProductoImagen> productoImg) {
		this.productoImg = productoImg;
	}

	public List<ProductoPrecio> getProductoPre() {
		return productoPre;
	}

	public Set<ProductoForma> getProductoForma() {
		return productoForma;
	}

	public void setProductoForma(Set<ProductoForma> productoForma) {
		this.productoForma = productoForma;
	}

	public void setProductoPre(List<ProductoPrecio> productoPre) {
		this.productoPre = productoPre;
	}


	public List<ProductoPropiedadDetalle> getProPropiDetal() {
		return proPropiDetal;
	}

	public void setProPropiDetal(List<ProductoPropiedadDetalle> proPropiDetal) {
		this.proPropiDetal = proPropiDetal;
	}

	public ProductoInformacion getProductoInfo() {
		return productoInfo;
	}

	public void setProductoInfo(ProductoInformacion productoInfo) {
		this.productoInfo = productoInfo;
	}

	public Agrupacion getAgrupacion() {
		return agrupacion;
	}

	public void setAgrupacion(Agrupacion agrupacion) {
		this.agrupacion = agrupacion;
	}
}
