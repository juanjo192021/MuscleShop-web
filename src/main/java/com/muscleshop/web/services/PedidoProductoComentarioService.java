package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IPedidoProductoComentarioDao;
import com.muscleshop.web.models.PedidoProductoComentario;


@Service
public class PedidoProductoComentarioService {
	
	@Autowired
	private IPedidoProductoComentarioDao pedProComDao;
	
	public List<PedidoProductoComentario> comentariosMostrables() {
        return pedProComDao.findByComentarioMostrarId(1);
    }

}
