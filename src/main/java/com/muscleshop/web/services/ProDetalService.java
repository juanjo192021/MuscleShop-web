package com.muscleshop.web.services;

import java.util.ArrayList;
import java.util.List;

import com.muscleshop.web.models.ProductoPropiedadDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoPropiedadDetalleDao;
import com.muscleshop.web.models.PropiedadesDetalles;

@Service
public class ProDetalService {
	
	@Autowired
	private IProductoPropiedadDetalleDao productoProDetDao;
	
   	public List<PropiedadesDetalles> obtenerDetallesPorProductoId(int productoId) {
        List<ProductoPropiedadDetalle> productoPropiedades = productoProDetDao.findByProductoId(productoId);
        List<PropiedadesDetalles> detalles = new ArrayList<>();
        for (ProductoPropiedadDetalle ppd : productoPropiedades) {
            PropiedadesDetalles detallePropiedad = ppd.getPropiedadesDetalles();
            if (detallePropiedad.getPropiedades().getId() == 1) {
                detalles.add(detallePropiedad);
            }
        }
        return detalles;
    }
   	
   	public List<PropiedadesDetalles> obtenerColores(int productoId) {
        List<ProductoPropiedadDetalle> productoPropiedades = productoProDetDao.findByProductoId(productoId);
        List<PropiedadesDetalles> detalles = new ArrayList<>();
        for (ProductoPropiedadDetalle ppd : productoPropiedades) {
            PropiedadesDetalles detallePropiedad = ppd.getPropiedadesDetalles();
            if (detallePropiedad.getPropiedades().getId() == 2) {
                detalles.add(detallePropiedad);
            }
        }
        return detalles;
    }
   	
   	public List<PropiedadesDetalles> obtenerTamaños(int productoId) {
        List<ProductoPropiedadDetalle> productoPropiedades = productoProDetDao.findByProductoId(productoId);
        List<PropiedadesDetalles> detalles = new ArrayList<>();
        for (ProductoPropiedadDetalle ppd : productoPropiedades) {
            PropiedadesDetalles detallePropiedad = ppd.getPropiedadesDetalles();
            if (detallePropiedad.getPropiedades().getId() == 3) {
                detalles.add(detallePropiedad);
            }
        }
        return detalles;
    }
   	
   	public List<PropiedadesDetalles> obtenerSabores(int productoId) {
        List<ProductoPropiedadDetalle> productoPropiedades = productoProDetDao.findByProductoId(productoId);
        List<PropiedadesDetalles> detalles = new ArrayList<>();
        for (ProductoPropiedadDetalle ppd : productoPropiedades) {
            PropiedadesDetalles detallePropiedad = ppd.getPropiedadesDetalles();
            if (detallePropiedad.getPropiedades().getId() == 4) {
                detalles.add(detallePropiedad);
            }
        }
        return detalles;
    }

}
