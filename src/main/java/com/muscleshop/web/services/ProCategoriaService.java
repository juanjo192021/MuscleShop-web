package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProCategoriaDao;
import com.muscleshop.web.models.MenuSub;
import com.muscleshop.web.models.ProductoCategoria;

@Service
public class ProCategoriaService {

	@Autowired
	private IProCategoriaDao proCateDao;

	public List<ProductoCategoria> listarProCate() {
		return proCateDao.findAll();
	}

	public ProductoCategoria listarProCateID(Integer id) {
		return proCateDao.findById(id).orElse(null);
	}

	public void saveProCate(ProductoCategoria proCate) {
		proCateDao.save(proCate);
	}

	public void eliminarProCate(Integer id) {
		proCateDao.deleteById(id);
	}

	public ProductoCategoria obtenerUrl(String url) {
		return proCateDao.findByUrl(url);
	}
	
	public List<ProductoCategoria> listarPorMenuSub(MenuSub menuSub) {
        return proCateDao.findByMenuSub(menuSub);
    }
	
	public List<ProductoCategoria> listarPorMenuSubId(int menuSubId) {
        return proCateDao.findByMenuSubId(menuSubId);
    }
}
