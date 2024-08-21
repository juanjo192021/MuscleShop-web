package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBannerDao extends JpaRepository<Banner, Integer> {

    List<Banner> findAllByEstado_Id(Integer estadoId);
}
