package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoProDetDao;
import com.muscleshop.web.models.ProductoProDetal;

@Service
public class ProductoProDetalService {
	
	@Autowired
	private IProductoProDetDao productoProDetDao;
	
	public List<ProductoProDetal> listarProDetPro() {
		return productoProDetDao.findAll();
	}

	public ProductoProDetal listarPorID(Integer id) {
		return productoProDetDao.findById(id).orElse(null);
	}
	
	public List<ProductoProDetal> obtenerProductosPorIdPresentacion(int idPresentacion) {
	    return productoProDetDao.findByPropiedadesDetallesId(idPresentacion);
	}
	
	public List<ProductoProDetal> obtenerPorTipoDePropiedad(int productoId, int tipoPropiedadId) {
        return productoProDetDao.findByProductoIdAndPropiedadesDetalles_Propiedades_Id(productoId, tipoPropiedadId);
    }



}
