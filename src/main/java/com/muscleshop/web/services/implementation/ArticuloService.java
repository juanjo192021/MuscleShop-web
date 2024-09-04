package com.muscleshop.web.services.implementation;

import java.util.List;

import com.muscleshop.web.services.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IArticuloDao;
import com.muscleshop.web.models.Articulo;

@Service
public class ArticuloService implements IArticuloService {

	@Autowired
	private IArticuloDao articuloDao;

	private Integer estadoId = 1;

	public List<Articulo> obtenerArticulos() {
		return articuloDao.findAll();
	}

	public List<Articulo> obtenerArticulosPorCantidad(Integer cantidad) {
		// Crear un Pageable con el tamaño deseado
		PageRequest pageable = PageRequest.of(0, cantidad);
		// Llamar al repositorio con el estado deseado y el Pageable
		return  articuloDao.findAllByEstado_IdOrderByIdDesc(estadoId, pageable);
	}

	public Articulo obtenerArticuloPorID(Integer id) {
		return articuloDao.findById(id).orElse(null);
	}

}
