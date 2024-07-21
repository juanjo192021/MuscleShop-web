package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {

	public Usuario findByCorreo(String correo);
}
