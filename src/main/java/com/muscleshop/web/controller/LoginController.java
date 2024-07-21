package com.muscleshop.web.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.muscleshop.web.models.Estado;
import com.muscleshop.web.models.Footer;
import com.muscleshop.web.models.Header;
import com.muscleshop.web.models.Logos;
import com.muscleshop.web.models.Menu;
import com.muscleshop.web.models.RedesSociales;
import com.muscleshop.web.models.RolPerfil;
import com.muscleshop.web.models.Roles;
import com.muscleshop.web.models.Usuario;
import com.muscleshop.web.models.UsuarioPerfil;
import com.muscleshop.web.services.EstadoService;
import com.muscleshop.web.services.FooterService;
import com.muscleshop.web.services.HeaderService;
import com.muscleshop.web.services.LogosService;
import com.muscleshop.web.services.MenuService;
import com.muscleshop.web.services.RedesSocialesService;
import com.muscleshop.web.services.RolPerfilService;
import com.muscleshop.web.services.RolesService;
import com.muscleshop.web.services.UsuarioPerfilService;
import com.muscleshop.web.services.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolPerfilService rolPerfilService;
	
	@Autowired
	RolesService rolesService;
	
	@Autowired
	EstadoService estadoService;

	@Autowired
	UsuarioPerfilService usuPerfilService;
	
	@Autowired
	FooterService footerService;

	@Autowired
	RedesSocialesService redesSocialesService;

	@Autowired
	HeaderService headerService;
	
	@Autowired
	MenuService menuService;
	
	@ModelAttribute("footer")
	public List<Footer> footer() {
		return footerService.listarFooter();
	}
	
	@ModelAttribute("redesSociales")
	public List<RedesSociales> obtenerRedes() {
		return redesSocialesService.listarRedes();
	}

	@ModelAttribute("header")
	public Map<Integer, Header> obtenerHeader() {
		Map<Integer, Header> header = new HashMap<>();
		for (int i = 1; i <= 5; i++) {
			Header hea = headerService.listarHeaderID(i);
			header.put(i, hea);
		}
		return header;
	}
	
	@Autowired
	LogosService logosService;
	
	@ModelAttribute("logos")
	public List<Logos> obtenerLogos() {
		return logosService.listarLogos();
	}
	
	@ModelAttribute("menu")
	public List<Menu> categorias() {
		return menuService.listarMenu();
	}
	
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error", "Usuario y/o Contraseña son incorrectos");
		}
		return "login";
	}
	
	@GetMapping("/registrar")
	public String registrar(Model model) {
		model.addAttribute("usuarioPerfil", new UsuarioPerfil());
		return "registrar";
	}
	
	@PostMapping("/registrar")
	public String registrar(@Valid @ModelAttribute UsuarioPerfil usuarioPerfil, BindingResult br, Model model,
	                        RedirectAttributes ra, HttpSession session) {

	    if (br.hasErrors()) {
	        return "registrar";
	    } else {
	        Usuario usuExiste = usuarioService.buscarUsuario(usuarioPerfil.getUsuario().getCorreo());
	        if (usuExiste == null) {
	        	
	            Usuario nuevoUsuario = new Usuario();
	            nuevoUsuario.setCorreo(usuarioPerfil.getUsuario().getCorreo());
				String passwordEncriptada = new BCryptPasswordEncoder().encode(usuarioPerfil.getUsuario().getPassword());
				nuevoUsuario.setPassword(passwordEncriptada);
	            nuevoUsuario.setFecha(LocalDate.now());

	            RolPerfil rolPerfil = rolPerfilService.listarRolPerfilID(1);
	            nuevoUsuario.setRolPerfil(rolPerfil);
	            
	            Estado estado = estadoService.listarEstadoID(1);
	            nuevoUsuario.setEstado(estado);

	            Set<Roles> roles = new HashSet<>();
	            Roles rol = rolesService.listarROlesID(3);
	            roles.add(rol);
	            nuevoUsuario.setRoles(roles);

	            usuarioService.saveUsuario(nuevoUsuario);

	            usuarioPerfil.setUsuario(nuevoUsuario);
	            usuPerfilService.saveUsuPerfil(usuarioPerfil);
	            return "redirect:/login";
	        }
	    }
	    return "redirect:/registrar";
	}
	
	@GetMapping("/cambiar-contrasena")
	public String mostrarFormularioCambioContraseña() {
	    return "contrasena";
	}
	
	@PostMapping("/cambiar-contrasena")
    public String cambiarContraseña(@RequestParam("correo") String correo,
                                    @RequestParam("nuevaContraseña") String nuevaContraseña,
                                    RedirectAttributes ra, HttpSession session) {

        Usuario usuario = usuarioService.buscarUsuario(correo);
        if (usuario != null) {
            String passwordEncriptada = new BCryptPasswordEncoder().encode(nuevaContraseña);
            usuario.setPassword(passwordEncriptada);           
            usuarioService.saveUsuario(usuario);
            
            ra.addFlashAttribute("successMessage", "Contraseña cambiada exitosamente");
            return "redirect:/cambiar-contrasena";
        } else {
            ra.addFlashAttribute("errorMessage", "Usuario no encontrado");
            return "redirect:/cambiar-contrasena";
        }
    }




}
