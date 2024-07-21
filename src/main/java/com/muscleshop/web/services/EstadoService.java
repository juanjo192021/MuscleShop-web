package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IEstadoDao;
import com.muscleshop.web.models.Estado;

@Service
public class EstadoService {

	@Autowired
	private IEstadoDao estadoDao;

	public List<Estado> listarEstado() {
		return estadoDao.findAll();
	}

	public Estado listarEstadoID(Integer id) {
		return estadoDao.findById(id).orElse(null);
	}
}
