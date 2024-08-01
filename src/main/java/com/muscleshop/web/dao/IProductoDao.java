package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.muscleshop.web.models.MenuSub;
import com.muscleshop.web.models.Producto;
import com.muscleshop.web.models.ProductoCategoria;
import org.springframework.data.repository.query.Param;

public interface IProductoDao extends JpaRepository<Producto, Integer> {

/*	List<Producto> findByCategoriaUrl(String categoriaUrl);
    List<Producto> findByCategoria(ProductoCategoria categoria);*/
    /*List<Producto> findByMenuSub(MenuSub menuSub);*/
    /*List<Producto> findByProductoFormaId(int productoFormaId);*/
    
    @Query("select p from Producto p where p.nombre like %?1%")
	List<Producto> buscarProducto(String nombre);


    /*Listar todos los productos con ofertas ya sean únicos o agrupados */

    @Query("SELECT p FROM Producto p join ProductoFormaProducto pfp on p.id = pfp.producto.id " +
            "where pfp.productoForma.id=:productoFormaId and " +
            "p.estado.id=:estadoId")
    List<Producto> listarProductosPorForma(@Param("productoFormaId") Integer productoFormaId,
                                           @Param("estadoId") Integer estadoId);

    /*Listar productos por url del MenuSub */

    @Query("SELECT p FROM Producto p " +
            "JOIN ProductoCategoria pc on p.productoCategoria.id = pc.id " +
            "JOIN MenuSub ms on pc.menuSub.id = ms.id " +
            "JOIN ProductoPropiedadDetalle ppd on p.id=ppd.producto.id " +
            "JOIN PropiedadesDetalles pd on ppd.propiedadesDetalles.id = pd.detalles.id " +
            "JOIN Propiedades pro on pd.propiedades.id= pro.id " +
            "WHERE ms.url = :menuSubUrl")
    List<Producto> findProductosByMenuSubUrl(@Param("menuSubUrl") String menuSubUrl);
}
