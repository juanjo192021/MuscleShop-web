package com.muscleshop.web.dao;

import java.util.List;

import com.muscleshop.web.models.Producto;
import com.muscleshop.web.models.ProductoPropiedadDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductoPropiedadDetalleDao extends JpaRepository<ProductoPropiedadDetalle, Integer> {
    List<ProductoPropiedadDetalle> findByProductoId(int productoId);
    
    List<ProductoPropiedadDetalle> findByPropiedadesDetallesId(int id);
    List<ProductoPropiedadDetalle> findByProductoIdAndPropiedadesDetalles_Propiedades_Id(int productoId, int propiedadId);

    /*
     Listar los detalles de las propiedades de un producto por el url del menuSub
     para poder visualizar los productos con base en sus detalles {presentación, tamaño, sabor, color}
     */

    @Query("select ppd from ProductoPropiedadDetalle ppd " +
            "join PropiedadesDetalles pd on ppd.propiedadesDetalles.id=pd.id " +
            "join Detalles d on pd.detalles.id=d.id " +
            "join Producto  p on ppd.producto.id=p.id " +
            "join ProductoCategoria  pc on p.productoCategoria.id= pc.id "+
            "join MenuSub ms on pc.menuSub.id=ms.id " +
            "where ms.url=:menuSubUrl and p.estado.id=:estado")
    List<ProductoPropiedadDetalle> findByMenuSubUrl(@Param("menuSubUrl") String menuSubUrl,
                                                            @Param("estado") Integer estado);

    @Query("select ppd from ProductoPropiedadDetalle ppd " +
            "join PropiedadesDetalles pd on ppd.propiedadesDetalles.id=pd.id " +
            "join Detalles d on pd.detalles.id=d.id " +
            "join Producto  p on ppd.producto.id=p.id " +
            "join ProductoCategoria  pc on p.productoCategoria.id= pc.id "+
            "where pc.url=:categoriaUrl and p.estado.id=:estado")
    List<ProductoPropiedadDetalle> findByCategoriaUrl(@Param("categoriaUrl") String categoriaUrl,
                                                      @Param("estado") Integer estado);


    @Query("select ppd from ProductoPropiedadDetalle ppd " +
            "join PropiedadDetalleImagen pdi on ppd.id=pdi.productoPropiedadDetalle.id " +
            "where ppd.producto.id=:productoId and ppd.propiedadesDetalles.id =:propiedadesDetallesId and ppd.propiedadesDetalles2.id=:propiedadesDetallesId2")
    ProductoPropiedadDetalle findByPropiedadesDetallesAndProductoId(@Param("productoId") Integer productoId,
                                                                    @Param("propiedadesDetallesId") Integer propiedadesDetallesId,
                                                                    @Param("propiedadesDetallesId2") Integer propiedadesDetallesId2);


    //Filtrar una lista de productos por precio mínimo, precio máximo, id del submenú y id de sus propiedades
    @Query("SELECT ppd FROM ProductoPropiedadDetalle ppd " +
            "join Producto p on ppd.producto.id=p.id " +
            "join ProductoCategoria pc on p.productoCategoria.id=pc.id " +
            "join MenuSub ms on pc.menuSub.id=ms.id " +
            "join PropiedadesDetalles pd on ppd.propiedadesDetalles.id=pd.id " +
            "left join PropiedadesDetalles pd2 on ppd.propiedadesDetalles2.id=pd2.id " +
            "where (ppd.precioReducido BETWEEN :minPrecio and :maxPrecio) " +
            "and ms.id=:menuSubId")
    List<ProductoPropiedadDetalle> findByPrecioBetweenAndMenuSubId(@Param("minPrecio") Double minPrecio,
                                                                   @Param("maxPrecio") Double maxPrecio,
                                                                   @Param("menuSubId") Integer menuSubId);

    //Filtrar una lista de productos por precio mínimo, precio máximo, id del submenú y id de sus propiedades
    @Query("SELECT ppd FROM ProductoPropiedadDetalle ppd " +
            "join Producto p on ppd.producto.id=p.id " +
            "join PropiedadesDetalles pd on ppd.propiedadesDetalles.id=pd.id " +
            "left join PropiedadesDetalles pd2 on ppd.propiedadesDetalles2.id=pd2.id " +
            "where (ppd.precioReducido BETWEEN :minPrecio and :maxPrecio) " +
            "and p.productoCategoria.id=:productoCategoriaId")
    List<ProductoPropiedadDetalle> findByPrecioBetweenAndCategoriaId(@Param("minPrecio") Double minPrecio,
                                                                   @Param("maxPrecio") Double maxPrecio,
                                                                   @Param("productoCategoriaId") Integer productoCategoriaId);

    // Listar productos por url del MenuSub: va según la escala ProductoCategoria.MenuSub.url
    List<ProductoPropiedadDetalle> findByProducto_ProductoCategoria_MenuSub_Id(Integer menuSubId);

    // Listar productos por url de la categoría: va según la escala ProductoCategoria.id
    List<ProductoPropiedadDetalle> findByProducto_ProductoCategoria_Id(Integer categoriaId);
}
