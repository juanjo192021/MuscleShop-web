package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.muscleshop.web.models.Menu;

import java.util.List;

public interface IMenuDao extends JpaRepository<Menu, Integer> {

    Menu findByUrl(String url);
}
