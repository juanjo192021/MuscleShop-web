package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IMenuDao;
import com.muscleshop.web.models.Menu;

@Service
public class MenuService {

	@Autowired
	private IMenuDao menuDao;

	public List<Menu> listarMenu() {
		return menuDao.findAll();
	}

	public Menu listarMenuID(Integer id) {
		return menuDao.findById(id).orElse(null);
	}

	public void saveMenu(Menu menu) {
		menuDao.save(menu);
	}

	public void eliminarMenu(Integer id) {
		menuDao.deleteById(id);
	}
}
