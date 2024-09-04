package com.muscleshop.web.services;

import com.muscleshop.web.dao.IPropiedadesDao;
import com.muscleshop.web.models.Propiedades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropiedadesService implements IPropiedadesService {
    @Autowired
    private IPropiedadesDao iPropiedadesDao;

    @Override
    public List<Propiedades> findProductosPropiedadesByMenuSubUrl(String menuSubUrl, Integer estado) {
        /*return iPropiedadesDao.findProductosPropiedadesByMenuSubUrl(menuSubUrl,estado);*/
        return iPropiedadesDao.findAll();
    }
}
