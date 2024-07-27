package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoImagenDao;
import com.muscleshop.web.models.ProductoImagen;

@Service
public class ProImgService {

	@Autowired
	private IProductoImagenDao proImgDao;

	public List<ProductoImagen> listarProImg() {
		return proImgDao.findAll();
	}

	public ProductoImagen listarProImgID(Integer id) {
		return proImgDao.findById(id).orElse(null);
	}

	public void saveProImg(ProductoImagen proImg) {
		proImgDao.save(proImg);
	}

	public void eliminarProImg(Integer id) {
		proImgDao.deleteById(id);
	}
}
