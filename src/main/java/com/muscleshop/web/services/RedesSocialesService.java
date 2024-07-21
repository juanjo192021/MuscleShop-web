package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IRedesSocialesDao;
import com.muscleshop.web.models.RedesSociales;

@Service
public class RedesSocialesService {
	
	@Autowired
	private IRedesSocialesDao redesSocialesDao;
	
	public List<RedesSociales> listarRedes() {
		return redesSocialesDao.findAll();
	}
	
	public void saveRedes(RedesSociales redes) {
		redesSocialesDao.save(redes);
	}

	public void eliminarRedes(Integer id) {
		redesSocialesDao.deleteById(id);
	}

	public RedesSociales listarRedesID(Integer id) {
		return redesSocialesDao.findById(id).orElse(null);
	}

}
