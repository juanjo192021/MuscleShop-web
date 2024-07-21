package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.MetodoPago;


public interface IMetodoPagoDao extends JpaRepository<MetodoPago, Integer> {

	List<MetodoPago> findByNombre(String nombre);
}
