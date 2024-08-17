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

    // Listar productos por url del MenuSub: va según la escala ProductoCategoria.MenuSub.url
    List<Producto> findByProductoCategoria_MenuSub_Id(Integer menuSubId);

    // Listar productos por url de la categoría: va según la escala ProductoCategoria.id
    List<Producto> findByProductoCategoria_Id(Integer categoriaId);

    Producto findByProductoPropiedadesDetalles_Id(Integer propiedadesDetallesId);

    //Filtrar una lista de productos por precio mínimo, precio máximo, id del submenú y id de sus propiedades
    @Query("SELECT p FROM Producto p " +
            "join ProductoCategoria pc on p.productoCategoria.id=pc.id " +
            "join MenuSub ms on pc.menuSub.id=ms.id " +
            "join ProductoPropiedadDetalle ppd on p.id=ppd.producto.id " +
            "join PropiedadesDetalles pd on ppd.propiedadesDetalles.id=pd.id " +
            "where (ppd.precio BETWEEN :minPrecio and :maxPrecio) " +
            "and ms.id=:menuSubId " +
            "and pd.propiedades.id=:propiedadesId")
    List<Producto> findByPrecioBetweenAndMenuSubId(@Param("minPrecio") Double minPrecio,
                                                   @Param("maxPrecio") Double maxPrecio,
                                                   @Param("menuSubId") Integer menuSubId,
                                                   @Param("propiedadesId") Integer propiedadesId);

    //Filtrar una lista de productos por precio mínimo, precio máximo, id de la categoría y id de sus propiedades

    @Query("SELECT p FROM Producto p " +
            "join ProductoPropiedadDetalle ppd on p.id=ppd.producto.id " +
            "join PropiedadesDetalles pd on ppd.propiedadesDetalles.id=pd.id " +
            "where (ppd.precio BETWEEN :precioMin and :precioMax) " +
            "and p.productoCategoria.id =:productoCategoriaId " +
            "and pd.propiedades.id=:propiedadesId")
    List<Producto> findByPrecioBetweenAndCategoriaId(@Param("precioMin") Double precioMin,
                                                             @Param("precioMax") Double precioMax,
                                                             @Param("productoCategoriaId") Integer productoCategoriaId,
                                                             @Param("propiedadesId") Integer propiedadesId);
}
