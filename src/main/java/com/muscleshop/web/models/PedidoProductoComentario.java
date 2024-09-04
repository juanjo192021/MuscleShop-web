package com.muscleshop.web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_producto_comentario")
public class PedidoProductoComentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String comentario;
	
	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;
	
	@OneToOne
	@JoinColumn(name = "pedidos_productos_id")
	private PedidoProducto pedidoProducto;

	public PedidoProductoComentario() {
	}

	public PedidoProductoComentario(String comentario, Estado estado, PedidoProducto pedidoProducto) {
		super();
		this.comentario = comentario;
		this.estado = estado;
		this.pedidoProducto = pedidoProducto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public PedidoProducto getPedidoProducto() {
		return pedidoProducto;
	}

	public void setPedidoProducto(PedidoProducto pedidoProducto) {
		this.pedidoProducto = pedidoProducto;
	}
}
