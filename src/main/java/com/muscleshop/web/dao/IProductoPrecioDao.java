package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.ProductoPrecio;
import com.muscleshop.web.models.RolPerfil;

public interface IProductoPrecioDao extends JpaRepository<ProductoPrecio, Integer> {
	
    ProductoPrecio findByProductoIdAndRolPerfilId(int idProducto, int idRolPerfil);
    List<ProductoPrecio> findByRolPerfil(RolPerfil rolPerfil);
    List<ProductoPrecio> findByRolPerfilId(Integer rolPerfilId);


}
