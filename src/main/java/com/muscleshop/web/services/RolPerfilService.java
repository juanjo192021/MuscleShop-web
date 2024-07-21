package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IRolPerfilDao;
import com.muscleshop.web.models.RolPerfil;

@Service
public class RolPerfilService {

	@Autowired
	private IRolPerfilDao rolPerfilDao;
	
	public List<RolPerfil> listarRolPerfil() {
		return rolPerfilDao.findAll();
	}

	public RolPerfil listarRolPerfilID(Integer id) {
		return rolPerfilDao.findById(id).orElse(null);
	}

	public void saveRolPerfil(RolPerfil rolPer) {
		rolPerfilDao.save(rolPer);
	}

	public void eliminarRolPerfil(Integer id) {
		rolPerfilDao.deleteById(id);
	}
}
