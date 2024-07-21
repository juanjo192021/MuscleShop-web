package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Estado;

public interface IEstadoDao extends JpaRepository<Estado, Integer> {

}
