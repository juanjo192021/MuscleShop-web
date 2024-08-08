package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IMenuSubDao;
import com.muscleshop.web.models.MenuSub;

@Service
public class MenuSubService {

	@Autowired
	private IMenuSubDao menuSubDao;

	//Listar todos los submenus
	public List<MenuSub> listarMenuSub() {
		return menuSubDao.findAll();
	}

	//Obtener Sub Menu por medio del ID del mismo
	public MenuSub listarMenuSubID(Integer id) {
		return menuSubDao.findById(id).orElse(null);
	}

	//Listar Sub Menus por medio del ID del menu
	public List<MenuSub> obtenerMenuSubsPorMenuId(Integer menuID) {
		return menuSubDao.findByMenu_Id(menuID);
	}

	//Obtener Sub Menu por medio de la url del mismo
	public MenuSub obtenerMenuSubPorUrl(String url) {
		return menuSubDao.findByUrl(url);
	}

	// Listar Sub Menus por medio del url del menu
	public List<MenuSub> obtenerMenuSubsPorMenuUrl(String menuUrl){
		return menuSubDao.findByMenu_Url(menuUrl);
	}
}
