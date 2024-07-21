package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IFooterDao;
import com.muscleshop.web.models.Footer;

@Service
public class FooterService {

	@Autowired
	private IFooterDao footerDao;
	
	public List<Footer> listarFooter() {
		return footerDao.findAll();
	}
	public Footer listarHFooterID(Integer id) {
		return footerDao.findById(id).orElse(null);
	}
}
