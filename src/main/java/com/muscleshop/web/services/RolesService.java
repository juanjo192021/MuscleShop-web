package com.muscleshop.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IRolesDao;
import com.muscleshop.web.models.Roles;

@Service
public class RolesService {

	@Autowired
	private IRolesDao rolesDao;
	
	public Roles listarROlesID(Integer id) {
		return rolesDao.findById(id).orElse(null);
	}
	
}
