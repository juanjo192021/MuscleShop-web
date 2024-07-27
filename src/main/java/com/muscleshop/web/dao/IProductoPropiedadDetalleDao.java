package com.muscleshop.web.dao;

import java.util.List;

import com.muscleshop.web.models.ProductoPropiedadDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoPropiedadDetalleDao extends JpaRepository<ProductoPropiedadDetalle, Integer> {
    List<ProductoPropiedadDetalle> findByProductoId(int productoId);
    
    List<ProductoPropiedadDetalle> findByPropiedadesDetallesId(int id);
    List<ProductoPropiedadDetalle> findByProductoIdAndPropiedadesDetalles_Propiedades_Id(int productoId, int propiedadId);


}
