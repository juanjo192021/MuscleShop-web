package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IBannerDao;
import com.muscleshop.web.models.Banner;

@Service
public class BannerService {

	@Autowired
	private IBannerDao bannerDao;

	public List<Banner> listarBanner() {
		return bannerDao.findAll();
	}

	public void saveBanner(Banner banner) {
		bannerDao.save(banner);
	}

	public void eliminarBanner(Integer id) {
		bannerDao.deleteById(id);
	}

	public Banner listarBannerID(Integer id) {
		return bannerDao.findById(id).orElse(null);
	}

}
