package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IHeaderDao;
import com.muscleshop.web.models.Header;

@Service
public class HeaderService {
	
	@Autowired
	private IHeaderDao headerDao;
	
	public List<Header> listarHeader() {
		return headerDao.findAll();
	}
	
	public void saveHeader(Header header) {
		headerDao.save(header);
	}

	public void eliminarHeader(Integer id) {
		headerDao.deleteById(id);
	}

	public Header listarHeaderID(Integer id) {
		return headerDao.findById(id).orElse(null);
	}

}
