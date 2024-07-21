package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IUsuarioDireccionDao;
import com.muscleshop.web.models.Usuario;
import com.muscleshop.web.models.UsuarioDireccion;

@Service
public class UsuarioDireccionService {
	
	@Autowired
	private IUsuarioDireccionDao direccionDao;
	
	public UsuarioDireccion listarUsuDireccion(Integer id) {
		return direccionDao.findById(id).orElse(null);
	}

	public void saveUsuDireccion(UsuarioDireccion usuDireccion) {
		direccionDao.save(usuDireccion);
	}
	
	public List<UsuarioDireccion> listarDireccionxUsu(Usuario usuario){
		return direccionDao.findByUsuario(usuario);
	}

}
