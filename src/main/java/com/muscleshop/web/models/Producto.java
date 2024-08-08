package com.muscleshop.web.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private ProductoCategoria productoCategoria;

	@ManyToOne
	@JoinColumn(name="agrupacion_id", nullable = false)
	private Agrupacion agrupacion;

	@OneToOne(mappedBy  = "producto")
	private ProductoInformacion productoInformacion;

	@OneToMany(mappedBy = "producto")
	private List<ProductoImagen> productoImagenes;
	
	@OneToMany(mappedBy = "producto")
	@JsonIgnore
	private List<ProductoPrecio> productoPrecios;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<ProductoMenuSub> productoMenusSub;

	@OneToMany(mappedBy = "producto")
	//@JsonIgnore
	private List<ProductoPropiedadDetalle> productoPropiedadesDetalles ;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<ProductoFormaProducto> productoFormasProducto;

	@OneToMany(mappedBy = "productoPadre", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductoAgrupado> productosHijos;

	@OneToMany(mappedBy = "productoHijo")
	@JsonIgnore
	private List<ProductoAgrupado> productosPadre;

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public ProductoCategoria getProductoCategoria() {
		return productoCategoria;
	}

	public void setProductoCategoria(ProductoCategoria productoCategoria) {
		this.productoCategoria = productoCategoria;
	}

	public Agrupacion getAgrupacion() {
		return agrupacion;
	}

	public void setAgrupacion(Agrupacion agrupacion) {
		this.agrupacion = agrupacion;
	}

	public ProductoInformacion getProductoInformacion() {
		return productoInformacion;
	}

	public void setProductoInformacion(ProductoInformacion productoInformacion) {
		this.productoInformacion = productoInformacion;
	}

	public List<ProductoImagen> getProductoImagenes() {
		return productoImagenes;
	}

	public void setProductoImagenes(List<ProductoImagen> productoImagenes) {
		this.productoImagenes = productoImagenes;
	}

	public List<ProductoPrecio> getProductoPrecios() {
		return productoPrecios;
	}

	public void setProductoPrecios(List<ProductoPrecio> productoPrecios) {
		this.productoPrecios = productoPrecios;
	}

	public List<ProductoMenuSub> getProductoMenusSub() {
		return productoMenusSub;
	}

	public void setProductoMenusSub(List<ProductoMenuSub> productoMenusSub) {
		this.productoMenusSub = productoMenusSub;
	}

	public List<ProductoPropiedadDetalle> getProductoPropiedadesDetalles() {
		return productoPropiedadesDetalles;
	}

	public void setProductoPropiedadesDetalles(List<ProductoPropiedadDetalle> productoPropiedadesDetalles) {
		this.productoPropiedadesDetalles = productoPropiedadesDetalles;
	}

	public List<ProductoFormaProducto> getProductoFormasProducto() {
		return productoFormasProducto;
	}

	public void setProductoFormasProducto(List<ProductoFormaProducto> productoFormasProducto) {
		this.productoFormasProducto = productoFormasProducto;
	}

	public List<ProductoAgrupado> getProductosHijos() {
		return productosHijos;
	}

	public void setProductosHijos(List<ProductoAgrupado> productosHijos) {
		this.productosHijos = productosHijos;
	}

	public List<ProductoAgrupado> getProductosPadre() {
		return productosPadre;
	}

	public void setProductosPadre(List<ProductoAgrupado> productosPadre) {
		this.productosPadre = productosPadre;
	}
}
