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

	public List<MenuSub> listarMenuSub() {
		return menuSubDao.findAll();
	}

	public MenuSub listarMenuSubID(Integer id) {
		return menuSubDao.findById(id).orElse(null);
	}

	public void savePopup(MenuSub menuSub) {
		menuSubDao.save(menuSub);
	}

	public void eliminarMenuSub(Integer id) {
		menuSubDao.deleteById(id);
	}

	public List<MenuSub> obtenerMenuID(Integer menuID) {
		return menuSubDao.findByMenuId(menuID);
	}

	public MenuSub obtenerUrl(String url) {
		return menuSubDao.findByUrl(url);
	}
}
