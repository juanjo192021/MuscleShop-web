package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IUsuarioPerfilDao;
import com.muscleshop.web.models.Usuario;
import com.muscleshop.web.models.UsuarioPerfil;

@Service
public class UsuarioPerfilService {

	@Autowired
	private IUsuarioPerfilDao usuPerfilDao;

	public List<UsuarioPerfil> listarUsuPerfil() {
		return usuPerfilDao.findAll();
	}

	public UsuarioPerfil listarUsuPerfilID(Integer id) {
		return usuPerfilDao.findById(id).orElse(null);
	}

	public void saveUsuPerfil(UsuarioPerfil usuPerfil) {
		usuPerfilDao.save(usuPerfil);
	}

	public void eliminarUsuPerfil(Integer id) {
		usuPerfilDao.deleteById(id);
	}
	
	public UsuarioPerfil buscarUsuario(Usuario usuario) {
		return usuPerfilDao.findByUsuario(usuario);
	}
}
