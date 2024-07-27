package com.muscleshop.web.services;

import com.muscleshop.web.dao.IMarketplaceDao;
import com.muscleshop.web.models.Marketplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketplaceService implements IMarketplaceService {

    @Autowired
    private IMarketplaceDao marketplaceDao;

    @Override
    public List<Marketplace> listarMarketplaces() {
        return marketplaceDao.findAll();
    }
}
