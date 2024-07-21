package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IMetodoPagoDao;
import com.muscleshop.web.models.MetodoPago;


@Service
public class MetodoPagoService {

	@Autowired
	private IMetodoPagoDao metodoDao;

	public List<MetodoPago> listarMetodo() {
		return metodoDao.findAll();
	}

	public void saveMetodo(MetodoPago metod) {
		metodoDao.save(metod);
	}

	public void eliminarMetodo(Integer id) {
		metodoDao.deleteById(id);
	}

	public MetodoPago listarMetodoID(Integer id) {
		return metodoDao.findById(id).orElse(null);
	}

	public MetodoPago obtenerNombre(String nombre) {
		List<MetodoPago> metodos = metodoDao.findByNombre(nombre);
		return metodos.isEmpty() ? null : metodos.get(0);
	}
	
}
