package com.muscleshop.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.ICuponDao;
import com.muscleshop.web.models.Cupon;

@Service
public class CuponService {
	
	@Autowired
	private ICuponDao cuponDao;
	
	public Cupon buscarCuponNombre(String nombre) {
		return cuponDao.findByNombre(nombre);
	}

}
