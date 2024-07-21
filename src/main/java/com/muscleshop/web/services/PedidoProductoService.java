package com.muscleshop.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IPedidoProductoDao;
import com.muscleshop.web.models.PedidoProducto;

@Service
public class PedidoProductoService {

	@Autowired
	private IPedidoProductoDao pedidoProductoDao;

	public PedidoProducto guardarPedidoPro(PedidoProducto pedidoProducto) {
		return pedidoProductoDao.save(pedidoProducto);
	}

	public PedidoProducto listarID(Integer id) {
		return pedidoProductoDao.findById(id).orElse(null);
	}

	
}
