package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.MenuSub;
import com.muscleshop.web.models.ProductoCategoria;

public interface IProductoCategoriaDao extends JpaRepository<ProductoCategoria, Integer> {

	ProductoCategoria findByUrl(String url);
    List<ProductoCategoria> findByMenuSub(MenuSub menuSub);
    //List<ProductoCategoria> findByMenuSubId(int menuSubId);
    List<ProductoCategoria> findByMenuSub_Id(int menuSubId);
}
