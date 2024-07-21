package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.ProductoImagen;

public interface IProImgDao extends JpaRepository<ProductoImagen, Integer> {

}
