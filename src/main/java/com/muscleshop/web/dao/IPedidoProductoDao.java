package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.PedidoProducto;
import com.muscleshop.web.models.Pedidos;


public interface IPedidoProductoDao extends JpaRepository<PedidoProducto, Integer> {

	List<PedidoProducto> findByPedido(Pedidos pedido);
}
