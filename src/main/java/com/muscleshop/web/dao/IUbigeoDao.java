package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.muscleshop.web.models.Ubigeo;

public interface IUbigeoDao extends JpaRepository<Ubigeo, Integer>{
	
	@Query("Select distinct x.region from Ubigeo x")
	public abstract List<String> listaRegion();
	
	@Query("Select distinct x.provincia from Ubigeo x where x.region = :var_reg")
	public abstract List<String> listaProvincia(@Param("var_reg") String region);
	
	@Query("Select distinct x from Ubigeo x where x.region = :var_reg and x.provincia = :var_pro")
	public abstract List<Ubigeo> listaDistrito(@Param("var_reg") String region, @Param("var_pro") String provincia);


}
