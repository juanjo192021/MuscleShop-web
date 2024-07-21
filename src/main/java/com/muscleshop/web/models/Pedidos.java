package com.muscleshop.web.models;

import java.sql.Timestamp;
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
@Table(name = "pedidos")
public class Pedidos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Timestamp fecha;
	private double total_costo;
	private String num_pedido;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "metodo_pago_id")
	private MetodoPago metodo;

	@OneToOne
	@JoinColumn(name = "comprobante_id")
	private ComprobantePago comprobante;
	
	@OneToOne
	@JoinColumn(name = "ubigeo_id")
	private Ubigeo ubigeo;
	
	@OneToMany(mappedBy = "pedido")
	private List<PedidoProducto> pedidosProductos;
	
	@OneToOne
	@JoinColumn(name = "usuario_direccion_id")
	private UsuarioDireccion direccion;

	public Pedidos() {
	}

	public Pedidos(Timestamp fecha, double total_costo, String num_pedido, Usuario usuario, MetodoPago metodo,
			ComprobantePago comprobante, Ubigeo ubigeo, List<PedidoProducto> pedidosProductos,
			UsuarioDireccion direccion) {
		super();
		this.fecha = fecha;
		this.total_costo = total_costo;
		this.num_pedido = num_pedido;
		this.usuario = usuario;
		this.metodo = metodo;
		this.comprobante = comprobante;
		this.ubigeo = ubigeo;
		this.pedidosProductos = pedidosProductos;
		this.direccion = direccion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public double getTotal_costo() {
		return total_costo;
	}

	public void setTotal_costo(double total_costo) {
		this.total_costo = total_costo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public MetodoPago getMetodo() {
		return metodo;
	}

	public void setMetodo(MetodoPago metodo) {
		this.metodo = metodo;
	}

	public ComprobantePago getComprobante() {
		return comprobante;
	}

	public void setComprobante(ComprobantePago estado) {
		this.comprobante = estado;
	}

	public List<PedidoProducto> getPedidosProductos() {
		return pedidosProductos;
	}

	public void setPedidosProductos(List<PedidoProducto> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}

	public Ubigeo getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}

	public String getNum_pedido() {
		return num_pedido;
	}

	public void setNum_pedido(String num_pedido) {
		this.num_pedido = num_pedido;
	}

	public UsuarioDireccion getDireccion() {
		return direccion;
	}

	public void setDireccion(UsuarioDireccion direccion) {
		this.direccion = direccion;
	}

}
