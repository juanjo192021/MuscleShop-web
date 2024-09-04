package com.muscleshop.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoPropiedadesDetallesDao;

@Service
public class ProDetalService {
	
	@Autowired
	private IProductoPropiedadesDetallesDao productoProDetDao;
	
/*   	public List<PropiedadesDetalles> obtenerDetallesPorProductoId(int productoId) {
        List<ProductoPropiedadesDetalles> productoPropiedades = productoProDetDao.findByProductoId(productoId);
        List<PropiedadesDetalles> detalles = new ArrayList<>();
        *//*for (ProductoPropiedadesDetalles ppd : productoPropiedades) {
            PropiedadesDetalles detallePropiedad = ppd.getPropiedadesDetalles();
            if (detallePropiedad.getPropiedades().getId() == 1) {
                detalles.add(detallePropiedad);
            }
        }*//*
        return detalles;
    }
   	
   	public List<PropiedadesDetalles> obtenerColores(int productoId) {
        List<ProductoPropiedadesDetalles> productoPropiedades = productoProDetDao.findByProductoId(productoId);
        List<PropiedadesDetalles> detalles = new ArrayList<>();
        *//*for (ProductoPropiedadesDetalles ppd : productoPropiedades) {
            PropiedadesDetalles detallePropiedad = ppd.getPropiedadesDetalles();
            if (detallePropiedad.getPropiedades().getId() == 2) {
                detalles.add(detallePropiedad);
            }
        }*//*
        return detalles;
    }
   	
   	public List<PropiedadesDetalles> obtenerTamaños(int productoId) {
        List<ProductoPropiedadesDetalles> productoPropiedades = productoProDetDao.findByProductoId(productoId);
        List<PropiedadesDetalles> detalles = new ArrayList<>();
        *//*for (ProductoPropiedadesDetalles ppd : productoPropiedades) {
            PropiedadesDetalles detallePropiedad = ppd.getPropiedadesDetalles();
            if (detallePropiedad.getPropiedades().getId() == 3) {
                detalles.add(detallePropiedad);
            }
        }*//*
        return detalles;
    }
   	
   	public List<PropiedadesDetalles> obtenerSabores(int productoId) {
        List<ProductoPropiedadesDetalles> productoPropiedades = productoProDetDao.findByProductoId(productoId);
        List<PropiedadesDetalles> detalles = new ArrayList<>();
        *//*for (ProductoPropiedadesDetalles ppd : productoPropiedades) {
            PropiedadesDetalles detallePropiedad = ppd.getPropiedadesDetalles();
            if (detallePropiedad.getPropiedades().getId() == 4) {
                detalles.add(detallePropiedad);
            }
        }*//*
        return detalles;
    }*/

}
