package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.ProductoProDetal;

public interface IProductoProDetDao extends JpaRepository<ProductoProDetal, Integer> {
    List<ProductoProDetal> findByProductoId(int productoId);
    
    List<ProductoProDetal> findByPropiedadesDetallesId(int id);
    List<ProductoProDetal> findByProductoIdAndPropiedadesDetalles_Propiedades_Id(int productoId, int propiedadId);


}
