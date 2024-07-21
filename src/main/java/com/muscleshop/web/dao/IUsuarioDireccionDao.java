package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Usuario;
import com.muscleshop.web.models.UsuarioDireccion;

public interface IUsuarioDireccionDao extends JpaRepository<UsuarioDireccion, Integer> {
	List<UsuarioDireccion> findByUsuario(Usuario usuario);
}
