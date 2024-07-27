package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.muscleshop.web.models.MenuSub;
import com.muscleshop.web.models.Producto;
import com.muscleshop.web.models.ProductoCategoria;

public interface IProductoDao extends JpaRepository<Producto, Integer> {

	List<Producto> findByCategoriaUrl(String categoriaUrl);
    List<Producto> findByCategoria(ProductoCategoria categoria);
    /*List<Producto> findByMenuSub(MenuSub menuSub);*/
    List<Producto> findByProductoFormaId(int productoFormaId);
    
    @Query("select p from Producto p where p.nombre like %?1%")
	List<Producto> buscarProducto(String nombre);



}
