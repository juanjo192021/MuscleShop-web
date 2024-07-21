package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IArticuloDao;
import com.muscleshop.web.models.Articulo;

@Service
public class ArticuloService {

	@Autowired
	private IArticuloDao articuloDao;

	public List<Articulo> listarArticulo() {
		return articuloDao.findAll();
	}

	public Page<Articulo> listarArticuloPorPage(Integer items) { return  articuloDao.findAll(PageRequest.of(0,items));}

	public void saveArticulo(Articulo arti) {
		articuloDao.save(arti);
	}

	public void eliminarArticulo(Integer id) {
		articuloDao.deleteById(id);
	}

	public Articulo listarArticuloID(Integer id) {
		return articuloDao.findById(id).orElse(null);
	}

}
