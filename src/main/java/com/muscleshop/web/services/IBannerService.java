package com.muscleshop.web.services;

import com.muscleshop.web.models.Banner;

import java.util.List;

public interface IBannerService {
    public List<Banner> listarBanners(Integer estadoId);
}
