package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.RolPerfil;

public interface IRolPerfilDao extends JpaRepository<RolPerfil, Integer>{
	RolPerfil findById(int id);
}
