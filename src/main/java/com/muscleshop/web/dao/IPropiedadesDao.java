package com.muscleshop.web.dao;

import com.muscleshop.web.models.Propiedades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPropiedadesDao extends JpaRepository<Propiedades, Integer> {

/*    @Query("select pro from Propiedades pro " +
            "join PropiedadesDetalles pd on pro.id = pd.propiedades.id " +
            "join Detalles d on pd.detalles.id = d.id " +
            "join ProductoPropiedadesDetalles ppd on pd.id = ppd.propiedadesDetalles.id " +
            "join Producto p on ppd.producto.id = p.id " +
            "join ProductoCategoria pc on p.productoCategoria.id = pc.id " +
            "JOIN MenuSub ms on pc.menuSub.id=ms.id " +
            "where ms.url=:menuSubUrl and p.estado.id=:estado and p.id=1" +
            "order by p.id asc")
    List<Propiedades> findProductosPropiedadesByMenuSubUrl(@Param("menuSubUrl") String menuSubUrl,
                                                           @Param("estado") Integer estado);*/
}
