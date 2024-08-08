package com.muscleshop.web.services;

import java.util.List;

import com.muscleshop.web.models.ProductoPropiedadDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoPropiedadDetalleDao;

@Service
public class ProductoPropiedadDetalleService {
	
	@Autowired
	private IProductoPropiedadDetalleDao productoProDetDao;
	
	public List<ProductoPropiedadDetalle> listarProDetPro() {
		return productoProDetDao.findAll();
	}

	public ProductoPropiedadDetalle listarPorID(Integer id) {
		return productoProDetDao.findById(id).orElse(null);
	}
	
	public List<ProductoPropiedadDetalle> obtenerProductosPorIdPresentacion(int idPresentacion) {
	    return productoProDetDao.findByPropiedadesDetallesId(idPresentacion);
	}
	
	public List<ProductoPropiedadDetalle> obtenerPorTipoDePropiedad(int productoId, int tipoPropiedadId) {
        return productoProDetDao.findByProductoIdAndPropiedadesDetalles_Propiedades_Id(productoId, tipoPropiedadId);
    }

	public List<ProductoPropiedadDetalle> findByMenuSubUrl(String menuSubUrl, Integer estado){
		return productoProDetDao.findByMenuSubUrl(menuSubUrl, estado);
	}

	public List<ProductoPropiedadDetalle> findByCategoriaUrl(String categoriaUrl, Integer estado){
		return productoProDetDao.findByCategoriaUrl(categoriaUrl, estado);
	}


}
