package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoPrecioDao;
import com.muscleshop.web.models.ProductoPrecio;

@Service
public class ProductoPrecioService {
	
	@Autowired
	private IProductoPrecioDao proPrecioDao;
	
	public double obtenerPrecioProducto(int idProducto, int idRolPerfil) {
	    ProductoPrecio productoPrecio = proPrecioDao.findByProductoIdAndRolPerfilId(idProducto, idRolPerfil);
	    return productoPrecio.getPrecio();
	}
	
	public List<ProductoPrecio> obtenerPreciosPorRolPerfilId(Integer rolPerfilId) {
        return proPrecioDao.findByRolPerfilId(rolPerfilId);
    }
	
	public double obtenerPrecioTachado(Integer idProducto, Integer idRolPerfil) {
	    ProductoPrecio productoPrecio = proPrecioDao.findByProductoIdAndRolPerfilId(idProducto, idRolPerfil);
	    return productoPrecio.getPrecio_tachado();
	}

	public int obtenerPorcentaje(Integer idProducto, Integer idRolPerfil) {
	    ProductoPrecio productoPrecio = proPrecioDao.findByProductoIdAndRolPerfilId(idProducto, idRolPerfil);
	    return productoPrecio.getPorcentaje();
	}


}
