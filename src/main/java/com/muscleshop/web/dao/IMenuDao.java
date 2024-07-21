package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.muscleshop.web.models.Menu;

public interface IMenuDao extends JpaRepository<Menu, Integer> {

}
