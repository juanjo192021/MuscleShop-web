package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.MenuSub;

public interface IMenuSubDao extends JpaRepository<MenuSub, Integer> {

	List<MenuSub> findByMenuId(Integer MenuId);

	public MenuSub findByUrl(String url);

}
