package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Banner;

public interface IBannerDao extends JpaRepository<Banner, Integer> {

}
