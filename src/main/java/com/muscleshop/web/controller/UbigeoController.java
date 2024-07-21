package com.muscleshop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muscleshop.web.models.Ubigeo;
import com.muscleshop.web.services.UbigeoService;

@Controller
@RequestMapping("/ejemplo")
public class UbigeoController {
	
	@Autowired
	UbigeoService ubigeoService;
	
	@GetMapping("/index")
	public String ver() {
		return "ejemplo";
	}
	
	@GetMapping("/listaRegion")
	@ResponseBody
	public List<String> verRegion(){
		return ubigeoService.listaRegion();
	}
	
	@GetMapping("/listaProvincia")
	@ResponseBody
	public List<String> verProvincia(String region){
		return ubigeoService.listaProvincia(region);
	}

	@GetMapping("/listaDistrito")
	@ResponseBody
	public List<Ubigeo> verDistrito(String region, String provincia){
	    List<Ubigeo> distritos = ubigeoService.listaDistrito(region, provincia);
	    return distritos;
	}

}
