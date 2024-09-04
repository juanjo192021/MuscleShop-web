package com.muscleshop.web.services.implementation;

import java.util.List;

import com.muscleshop.web.services.IPopupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IPopupDao;
import com.muscleshop.web.models.Popup;

@Service
public class PopupService implements IPopupService {

	@Autowired
	private IPopupDao popupDao;

	private Integer estadoId=1;

	public List<Popup> obtenerPopups() {
		/*return popupDao.findAllByEstado_Id(estadoId);*/
		return  popupDao.findAll();
	}

}
