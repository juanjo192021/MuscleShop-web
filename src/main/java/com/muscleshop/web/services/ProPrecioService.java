package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoPrecioDao;
import com.muscleshop.web.models.ProductoPrecio;

@Service
public class ProPrecioService {

	@Autowired
	private IProductoPrecioDao proPrecioDao;

	public List<ProductoPrecio> listarProPrecio() {
		return proPrecioDao.findAll();
	}

	public ProductoPrecio listarProPrecioID(Integer id) {
		return proPrecioDao.findById(id).orElse(null);
	}

	public void saveProducto(ProductoPrecio proPrecio) {
		proPrecioDao.save(proPrecio);
	}

	public void eliminarProPrecio(Integer id) {
		proPrecioDao.deleteById(id);
	}
}
