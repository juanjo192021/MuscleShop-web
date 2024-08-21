package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IPopupDao;
import com.muscleshop.web.models.Popup;

@Service
public class PopupService implements IPopupService {

	@Autowired
	private IPopupDao popupDao;

	public List<Popup> listarPopups(Integer estadoId) {
		/*return popupDao.findAllByEstado_Id(estadoId);*/
		return  popupDao.findAll();
	}

}
