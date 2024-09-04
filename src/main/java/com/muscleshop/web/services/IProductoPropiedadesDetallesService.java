package com.muscleshop.web.services;

import com.muscleshop.web.models.ProductoPropiedadesDetalles;
import com.muscleshop.web.models.dto.ProductoItemsDto;

import java.util.List;

public interface IProductoPropiedadesDetallesService {

    List<ProductoPropiedadesDetalles> obtenerProductoPropiedadesDetallesPorForma(int productoFormaId);
    List<ProductoItemsDto> obtenerProductosIndividualesPorMenuSubId(Double minPrecio, Double maxPrecio, Integer menuSubId);
    List<ProductoItemsDto> obtenerProductosIndividualesPorCategoriaId (Double minPrecio, Double maxPrecio,Integer categoriaId);
    ProductoPropiedadesDetalles obtenerProductoPropiedadDetallesPorVariaciones(int productoId, String variacion1, String variacion2);
}
