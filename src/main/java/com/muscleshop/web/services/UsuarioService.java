package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IUsuarioDao;
import com.muscleshop.web.models.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;

	public List<Usuario> listarUsuario() {
		return usuarioDao.findAll();
	}

	public Usuario listarUsuarioID(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

	public void saveUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	public void eliminarUsuario(Integer id) {
		usuarioDao.deleteById(id);
	}

	public Usuario buscarUsuario(String correo) {
		return usuarioDao.findByCorreo(correo);
	}
}
