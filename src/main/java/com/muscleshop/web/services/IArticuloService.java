package com.muscleshop.web.services;

import com.muscleshop.web.models.Articulo;

import java.util.List;

public interface IArticuloService {
    List<Articulo> listarArticulo();
    List<Articulo> listarArticulosPorCantidad(Integer cantidad, Integer estadoId);
    Articulo listarArticuloID(Integer id);
}
