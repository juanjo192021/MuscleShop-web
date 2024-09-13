package com.muscleshop.web.services;

import java.util.ArrayList;
import java.util.List;

import com.muscleshop.web.models.*;
import com.muscleshop.web.models.dto.ProductoCarritoDto;
import com.muscleshop.web.models.dto.ProductoItemsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoDao;

@Service
public class ProductoService {

	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	UsuarioService usuarioService;

	public List<Producto> listarProducto() {
		return productoDao.findAll();
	}

	public Producto listarProductoPorID(Integer id) {
		return productoDao.findById(id).orElse(null);
	}

	public Producto obtenerProductoPorUrl(String productoUrl){
		return productoDao.findByUrl(productoUrl);
	}

    public List<Producto> buscarProducto(String nombre) {
		return productoDao.buscarProducto(nombre);
	}

}
