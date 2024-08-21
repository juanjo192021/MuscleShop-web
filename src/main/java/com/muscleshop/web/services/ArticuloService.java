package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IArticuloDao;
import com.muscleshop.web.models.Articulo;

@Service
public class ArticuloService implements IArticuloService {

	@Autowired
	private IArticuloDao articuloDao;

	public List<Articulo> listarArticulo() {
		return articuloDao.findAll();
	}

	public List<Articulo> listarArticulosPorCantidad(Integer cantidad, Integer estadoId) {
		// Crear un Pageable con el tamaño deseado
		PageRequest pageable = PageRequest.of(0, cantidad);
		// Llamar al repositorio con el estado deseado y el Pageable
		return  articuloDao.findAllByEstado_IdOrderByIdDesc(estadoId, pageable);
	}

	public Articulo listarArticuloID(Integer id) {
		return articuloDao.findById(id).orElse(null);
	}

}
