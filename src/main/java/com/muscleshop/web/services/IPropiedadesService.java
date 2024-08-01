package com.muscleshop.web.services;

import com.muscleshop.web.models.Propiedades;

import java.util.List;

public interface IPropiedadesService {
    List<Propiedades> findProductosPropiedadesByMenuSubUrl(String menuSubUrl, Integer estado);
}
