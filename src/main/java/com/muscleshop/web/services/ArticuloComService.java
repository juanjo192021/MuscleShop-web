package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IArticuloComentarioDao;
import com.muscleshop.web.models.ArticuloComentario;

@Service
public class ArticuloComService {

	@Autowired
	private IArticuloComentarioDao articuloComDao;

	public List<ArticuloComentario> listarArticulo() {
		return articuloComDao.findAll();
	}

	public void saveArticuloCom(ArticuloComentario artiCom) {
		articuloComDao.save(artiCom);
	}
}
