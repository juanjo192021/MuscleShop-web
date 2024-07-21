package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.ILogosDao;
import com.muscleshop.web.models.Logos;

@Service
public class LogosService {
	
	@Autowired
	private ILogosDao logosDao;
	
	public List<Logos> listarLogos() {
		return logosDao.findAll();
	}
	
	public void saveLogos(Logos logos) {
		logosDao.save(logos);
	}

	public void eliminarLogos(Integer id) {
		logosDao.deleteById(id);
	}

	public Logos listarLogosID(Integer id) {
		return logosDao.findById(id).orElse(null);
	}

}
