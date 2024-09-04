package com.muscleshop.web.services;

import com.muscleshop.web.models.Articulo;

import java.util.List;

public interface IArticuloService {
    List<Articulo> obtenerArticulos();
    List<Articulo> obtenerArticulosPorCantidad(Integer cantidad);
    Articulo obtenerArticuloPorID(Integer id);
}
