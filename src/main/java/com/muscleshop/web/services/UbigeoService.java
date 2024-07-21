package com.muscleshop.web.services;

import java.util.List;

import com.muscleshop.web.models.Ubigeo;

public interface UbigeoService {

	public abstract List<String> listaRegion();
	public abstract List<String> listaProvincia(String region);
	public abstract List<Ubigeo> listaDistrito(String region, String provincia);
    public abstract Ubigeo obtenerUbigeoId(Integer id);

}
