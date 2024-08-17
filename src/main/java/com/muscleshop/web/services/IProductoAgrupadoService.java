package com.muscleshop.web.services;

import com.muscleshop.web.models.ProductoAgrupado;

import java.util.List;

public interface IProductoAgrupadoService {
    List<ProductoAgrupado> listarProductosAgrupados();

    /*List<ProductoAgrupado> listarProductosAgrupadosPorIdPadre(Integer idPadre);*/
}
