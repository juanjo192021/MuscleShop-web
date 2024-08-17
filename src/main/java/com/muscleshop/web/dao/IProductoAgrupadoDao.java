package com.muscleshop.web.dao;

import com.muscleshop.web.models.ProductoAgrupado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoAgrupadoDao extends JpaRepository<ProductoAgrupado, Integer> {

    // Buscar productos agrupados basados en su producto_padre_id

    /*List<ProductoAgrupado> findByProductoPadreId(Integer productoPadreId);*/
}
