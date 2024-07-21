package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.ComprobantePago;

public interface IComprobantePagoDao extends JpaRepository<ComprobantePago, Integer>{

	List<ComprobantePago> findByNombre(String nombre);

	
}
