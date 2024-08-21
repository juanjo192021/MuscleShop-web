package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Popup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPopupDao extends JpaRepository<Popup, Integer> {
    //Falta estado para el popup
    //List<Popup> findAllByEstado_Id(Integer estadoId);
}
