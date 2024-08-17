package com.muscleshop.web.services;

import com.muscleshop.web.dao.IProductoAgrupadoDao;
import com.muscleshop.web.models.ProductoAgrupado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoAgrupadoService implements IProductoAgrupadoService {

    @Autowired
    IProductoAgrupadoDao iProductoAgrupadoDao;

    @Override
    public List<ProductoAgrupado> listarProductosAgrupados() {
        return iProductoAgrupadoDao.findAll();
    }

/*    @Override
    public List<ProductoAgrupado> listarProductosAgrupadosPorIdPadre(Integer productoPadreId) {
        return iProductoAgrupadoDao.findByProductoPadreId(productoPadreId);
    }*/
}
