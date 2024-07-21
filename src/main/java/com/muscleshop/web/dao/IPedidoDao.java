package com.muscleshop.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.muscleshop.web.models.Pedidos;
import com.muscleshop.web.models.Usuario;


public interface IPedidoDao extends JpaRepository<Pedidos, Integer> {
	List<Pedidos> findByUsuario(Usuario usuario);

}
