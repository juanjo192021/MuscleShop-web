package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Usuario;
import com.muscleshop.web.models.UsuarioPerfil;

public interface IUsuarioPerfilDao extends JpaRepository<UsuarioPerfil, Integer> {
	
	public UsuarioPerfil findByUsuario (Usuario usuario);

}
