package com.muscleshop.web.dao;

import com.muscleshop.web.models.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMarketplaceDao extends JpaRepository<Marketplace, Integer> {
}
