package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.ArticuloComentario;

public interface IArticuloComentarioDao extends JpaRepository<ArticuloComentario, Integer> {

}
