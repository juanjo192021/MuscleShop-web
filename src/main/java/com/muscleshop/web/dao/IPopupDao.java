package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Popup;

public interface IPopupDao extends JpaRepository<Popup, Integer> {

}
