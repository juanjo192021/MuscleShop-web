package com.muscleshop.web.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="agrupacion")
public class Agrupacion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@OneToOne()
	@JoinColumn(name="categoria_id")
	private ProductoCategoria categoriaProductos;

	private String imagen;

	private Double precio;

	@Column(name = "precio_reducido", nullable = false)
	private Double precioReducido;

	@Column(name = "precio_team", nullable = false)
	private Double precioTeam;

	@Column(name = "precio_team_vip", nullable = false)
	private Double precioTeamVip;

	@Column(name = "precio_familiar", nullable = false)
	private Double precioFamiliar;

	private Integer prioridad;

	@OneToOne()
	@JoinColumn(name="estado_id")
	private Estado estado;

	@OneToMany(mappedBy = "agrupacion",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductoAgrupado> productoAgrupado;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ProductoCategoria getCategoriaProductos() {
		return categoriaProductos;
	}

	public void setCategoriaProductos(ProductoCategoria categoriaProductos) {
		this.categoriaProductos = categoriaProductos;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getPrecioReducido() {
		return precioReducido;
	}

	public void setPrecioReducido(Double precioReducido) {
		this.precioReducido = precioReducido;
	}

	public Double getPrecioTeam() {
		return precioTeam;
	}

	public void setPrecioTeam(Double precioTeam) {
		this.precioTeam = precioTeam;
	}

	public Double getPrecioTeamVip() {
		return precioTeamVip;
	}

	public void setPrecioTeamVip(Double precioTeamVip) {
		this.precioTeamVip = precioTeamVip;
	}

	public Double getPrecioFamiliar() {
		return precioFamiliar;
	}

	public void setPrecioFamiliar(Double precioFamiliar) {
		this.precioFamiliar = precioFamiliar;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<ProductoAgrupado> getProductoAgrupado() {
		return productoAgrupado;
	}

	public void setProductoAgrupado(List<ProductoAgrupado> productoAgrupado) {
		this.productoAgrupado = productoAgrupado;
	}
}
