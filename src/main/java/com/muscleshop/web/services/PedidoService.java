package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IPedidoDao;
import com.muscleshop.web.models.Pedidos;
import com.muscleshop.web.models.Usuario;

@Service
public class PedidoService {

	@Autowired
	private IPedidoDao pedidoDao;

	public Pedidos guardarPedido(Pedidos pedido) {
		return pedidoDao.save(pedido);
	}

	public List<Pedidos> listarPedido() {
		return pedidoDao.findAll();
	}

	public Pedidos listarPorId(Integer id) {
		return pedidoDao.findById(id).orElse(null);
	}

	public List<Pedidos> obtenerUsuario(Usuario usuario) {
		return pedidoDao.findByUsuario(usuario);
	}
}
