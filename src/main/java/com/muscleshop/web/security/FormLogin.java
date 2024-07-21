package com.muscleshop.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.muscleshop.web.models.Roles;
import com.muscleshop.web.models.Usuario;
import com.muscleshop.web.services.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Component
public class FormLogin implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuar = usuarioService.buscarUsuario(username);

		if (usuar == null) {
			throw new UsernameNotFoundException("No existe el Usuario");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.setAttribute("usuario", username);

		for (Roles ro : usuar.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(ro.getNombre()));
		}

		if (authorities.isEmpty()) {
			throw new UsernameNotFoundException("No tiene los permisos necesarios para ingresar");
		}

		return new User(usuar.getCorreo(), usuar.getPassword(), authorities);
	}

}
