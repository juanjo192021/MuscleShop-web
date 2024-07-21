package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IPopupDao;
import com.muscleshop.web.models.Popup;

@Service
public class PopupService {

	@Autowired
	private IPopupDao popupDao;

	public List<Popup> listarPopup() {
		return popupDao.findAll();
	}

	public Popup listarPopupID(Integer id) {
		return popupDao.findById(id).orElse(null);
	}

	public void savePopup(Popup popup) {
		popupDao.save(popup);
	}

	public void eliminarPopup(Integer id) {
		popupDao.deleteById(id);
	}
}
