package com.muscleshop.web.dao;

import java.util.List;

import com.muscleshop.web.models.ProductoPropiedadDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductoPropiedadDetalleDao extends JpaRepository<ProductoPropiedadDetalle, Integer> {
    List<ProductoPropiedadDetalle> findByProductoId(int productoId);
    
    List<ProductoPropiedadDetalle> findByPropiedadesDetallesId(int id);
    List<ProductoPropiedadDetalle> findByProductoIdAndPropiedadesDetalles_Propiedades_Id(int productoId, int propiedadId);

    /*
    * SELECT * FROM muscleshop.producto_propiedades_detalles  ppd
join muscleshop.producto p on p.id = ppd.producto_id
join muscleshop.propiedades_detalles pd on pd.id = ppd.propiedades_detalles_id
join muscleshop.detalles d on d.id = pd.detalles_id
where producto_id=1;
    * */
    @Query("select ppd from ProductoPropiedadDetalle ppd " +
            "join PropiedadesDetalles pd on ppd.propiedadesDetalles.id=pd.id " +
            "join Detalles d on pd.detalles.id=d.id " +
            "join Producto  p on ppd.producto.id=p.id " +
            "join ProductoCategoria  pc on p.productoCategoria.id= pc.id "+
            "join MenuSub ms on pc.menuSub.id=ms.id " +
            "where ms.url=:menuSubUrl and p.estado.id=:estado")
    List<ProductoPropiedadDetalle> findByProductosMenuSubUrl(@Param("menuSubUrl") String menuSubUrl,
                                                    @Param("estado") Integer estado);
}
