package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IPedProComentarioDao;
import com.muscleshop.web.models.PedidoProductoComentario;


@Service
public class PedProComentarioService {
	
	@Autowired
	private IPedProComentarioDao pedProComDao;
	
	public List<PedidoProductoComentario> comentariosMostrables() {
        return pedProComDao.findByComentarioMostrarId(1);
    }

}
