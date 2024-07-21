package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Cupon;
import java.util.List;


public interface ICuponDao extends JpaRepository<Cupon, Integer> {
	
	Cupon findByNombre(String nombre);

}
