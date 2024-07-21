package com.muscleshop.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muscleshop.web.models.Header;

public interface IHeaderDao extends JpaRepository<Header, Integer>{

}
