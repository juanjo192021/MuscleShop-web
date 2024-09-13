package com.muscleshop.web.models;

import java.time.LocalDate;
import java.util.List;

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
	private String url;
	private String descripcion;
	private LocalDate fecha;
	private String mostrar;
	private Integer prioridad;

	@ManyToOne
	@JoinColumn(name = "estado_id")
	private EstadoArticuloProducto estado;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private ProductoCategoria productoCategoria;

	@OneToMany
	@JoinColumn(name = "producto_id")
	private List<ProductoVariacion> productoVariaciones;

/*	@ManyToOne
	@JoinColumn(name="agrupacion_id", nullable = false)
	private Agrupacion agrupacion;*/

	@OneToOne(mappedBy  = "producto")
	private ProductoInformacion productoInformacion;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<ProductoMenuSub> productoMenusSub;

	@OneToMany(mappedBy = "producto")
	@JsonIgnore
	private List<ProductoPropiedadesDetalles> productoPropiedadesDetalles ;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public EstadoArticuloProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoArticuloProducto estado) {
		this.estado = estado;
	}

	public String getMostrar() {
		return mostrar;
	}

	public void setMostrar(String mostrar) {
		this.mostrar = mostrar;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public ProductoCategoria getProductoCategoria() {
		return productoCategoria;
	}

	public void setProductoCategoria(ProductoCategoria productoCategoria) {
		this.productoCategoria = productoCategoria;
	}

	public ProductoInformacion getProductoInformacion() {
		return productoInformacion;
	}

	public void setProductoInformacion(ProductoInformacion productoInformacion) {
		this.productoInformacion = productoInformacion;
	}

	public List<ProductoMenuSub> getProductoMenusSub() {
		return productoMenusSub;
	}

	public void setProductoMenusSub(List<ProductoMenuSub> productoMenusSub) {
		this.productoMenusSub = productoMenusSub;
	}

	public List<ProductoPropiedadesDetalles> getProductoPropiedadesDetalles() {
		return productoPropiedadesDetalles;
	}

	public void setProductoPropiedadesDetalles(List<ProductoPropiedadesDetalles> productoPropiedadesDetalles) {
		this.productoPropiedadesDetalles = productoPropiedadesDetalles;
	}

	public List<ProductoVariacion> getProductoVariaciones() {
		return productoVariaciones;
	}

	public void setProductoVariaciones(List<ProductoVariacion> productoVariaciones) {
		this.productoVariaciones = productoVariaciones;
	}
}
