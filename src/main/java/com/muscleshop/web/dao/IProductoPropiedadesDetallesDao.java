package com.muscleshop.web.dao;

import java.util.List;

import com.muscleshop.web.models.ProductoPropiedadesDetalles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductoPropiedadesDetallesDao extends JpaRepository<ProductoPropiedadesDetalles, Integer> {
/*
    List<ProductoPropiedadesDetalles> findByProductoId(int productoId);
    
    List<ProductoPropiedadesDetalles> findByPropiedadesDetallesId(int id);
    List<ProductoPropiedadesDetalles> findByProductoIdAndPropiedadesDetalles_Propiedades_Id(int productoId, int propiedadId);
*/

    /*
     Listar los detalles de las propiedades de un producto por el url del menuSub
     para poder visualizar los productos con base en sus detalles {presentación, tamaño, sabor, color}
     */

    List<ProductoPropiedadesDetalles> findByProductoForma_IdAndEstado_Id(int productoFormaId, int estadoId);

    /*@Query("select ppd from ProductoPropiedadesDetalles ppd " +
            "join PropiedadesDetalles pd on ppd.propiedadesDetalles.id=pd.id " +
            "join Detalles d on pd.detalles.id=d.id " +
            "join Producto  p on ppd.producto.id=p.id " +
            "join ProductoCategoria  pc on p.productoCategoria.id= pc.id "+
            "join MenuSub ms on pc.menuSub.id=ms.id " +
            "where ms.url=:menuSubUrl and p.estado.id=:estado")
    List<ProductoPropiedadesDetalles> findByMenuSubUrl(@Param("menuSubUrl") String menuSubUrl,
                                                       @Param("estado") Integer estado);

    @Query("select ppd from ProductoPropiedadesDetalles ppd " +
            "join PropiedadesDetalles pd on ppd.propiedadesDetalles.id=pd.id " +
            "join Detalles d on pd.detalles.id=d.id " +
            "join Producto  p on ppd.producto.id=p.id " +
            "join ProductoCategoria  pc on p.productoCategoria.id= pc.id "+
            "where pc.url=:categoriaUrl and p.estado.id=:estado")
    List<ProductoPropiedadesDetalles> findByCategoriaUrl(@Param("categoriaUrl") String categoriaUrl,
                                                         @Param("estado") Integer estado);


    @Query("select ppd from ProductoPropiedadesDetalles ppd " +
            "join PropiedadDetalleImagen pdi on ppd.id=pdi.productoPropiedadDetalle.id " +
            "where ppd.producto.id=:productoId and ppd.propiedadesDetalles.id =:propiedadesDetallesId and ppd.propiedadesDetalles2.id=:propiedadesDetallesId2")
    ProductoPropiedadesDetalles findByPropiedadesDetallesAndProductoId(@Param("productoId") Integer productoId,
                                                                       @Param("propiedadesDetallesId") Integer propiedadesDetallesId,
                                                                       @Param("propiedadesDetallesId2") Integer propiedadesDetallesId2);
*/

    //Filtrar una lista de productos por precio mínimo, precio máximo, id del submenú y id de sus propiedades
    @Query("SELECT ppd FROM ProductoPropiedadesDetalles ppd " +
            "join Producto p on ppd.producto.id=p.id " +
            "join ProductoCategoria pc on p.productoCategoria.id=pc.id " +
            "join MenuSub ms on pc.menuSub.id=ms.id " +
            "where (ppd.precioReducido BETWEEN :minPrecio and :maxPrecio) " +
            "and ms.id=:menuSubId")
    List<ProductoPropiedadesDetalles> findByPrecioBetweenAndMenuSubId(@Param("minPrecio") Double minPrecio,
                                                                      @Param("maxPrecio") Double maxPrecio,
                                                                      @Param("menuSubId") Integer menuSubId);
    //Filtrar una lista de productos por precio mínimo, precio máximo, id del submenú y id de sus propiedades
    @Query("SELECT ppd FROM ProductoPropiedadesDetalles ppd " +
            "join Producto p on ppd.producto.id=p.id " +
            "where (ppd.precioReducido BETWEEN :minPrecio and :maxPrecio) " +
            "and p.productoCategoria.id=:productoCategoriaId")
    List<ProductoPropiedadesDetalles> findByPrecioBetweenAndCategoriaId(@Param("minPrecio") Double minPrecio,
                                                                        @Param("maxPrecio") Double maxPrecio,
                                                                        @Param("productoCategoriaId") Integer productoCategoriaId);
    // Listar productos por url del MenuSub: va según la escala ProductoCategoria.MenuSub.url
    List<ProductoPropiedadesDetalles> findByProducto_ProductoCategoria_MenuSub_Id(Integer menuSubId);

    // Listar productos por url de la categoría: va según la escala ProductoCategoria.id
    List<ProductoPropiedadesDetalles> findByProducto_ProductoCategoria_Id(Integer categoriaId);

    ProductoPropiedadesDetalles findByProducto_IdAndProductoVariacion1_ValorAndProductoVariacion2_Valor(int productoId, String variacion1, String variacion2);
}
