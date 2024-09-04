package com.muscleshop.web.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.muscleshop.web.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.muscleshop.web.services.ComprobantePagoService;
import com.muscleshop.web.services.EstadoService;
import com.muscleshop.web.services.FooterService;
import com.muscleshop.web.services.HeaderService;
import com.muscleshop.web.services.LogosService;
import com.muscleshop.web.services.MenuService;
import com.muscleshop.web.services.MetodoPagoService;
import com.muscleshop.web.services.PedidoProductoService;
import com.muscleshop.web.services.PedidoService;
import com.muscleshop.web.services.ProductoService;
import com.muscleshop.web.services.RedesSocialesService;
import com.muscleshop.web.services.RolPerfilService;
import com.muscleshop.web.services.RolesService;
import com.muscleshop.web.services.UbigeoService;
import com.muscleshop.web.services.UsuarioDireccionService;
import com.muscleshop.web.services.UsuarioPerfilService;
import com.muscleshop.web.services.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	MenuService menuService;

	@Autowired
	ProductoService productoService;

	@Autowired
	MetodoPagoService metodoService;

	@Autowired
	ComprobantePagoService comprobanteService;

	@Autowired
	UsuarioPerfilService usuPerfilService;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	PedidoProductoService pedProService;

	@Autowired
	RolPerfilService rolPerfilService;

	@Autowired
	RolesService rolesService;

	@Autowired
	EstadoService estadoService;
	
	@Autowired
	UbigeoService ubigeoService;
	
	@Autowired
	FooterService footerService;
	
	@Autowired
	RedesSocialesService redesSocialesService;
	
	@Autowired
	HeaderService headerService;
	
	@Autowired
	UsuarioDireccionService direccionService;
	
	
	@Autowired
	LogosService logosService;

	//Modelo para el logo de la tienda
	@ModelAttribute("logosTienda")
	public List<Logos> logos() {
		return logosService.listarLogos();
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

	//Modelos para el menu de la barra Header Bottom
	@ModelAttribute("menusHeader")
	public List<Menu> menus() {
		return menuService.listarMenu();
	}

	/*@ModelAttribute
    public void addCommonAttributes(Model model, HttpSession session) {
        List<Producto> proCate = productoService.listarProducto();

        Map<Integer, Double> precios = new HashMap<>();
        Map<Integer, Double> preciosTachados = new HashMap<>();
        Map<Integer, Integer> porcentajes = new HashMap<>();

        if (session.getAttribute("usuario") != null) {
            Usuario usuario = usuarioService.buscarUsuario(session.getAttribute("usuario").toString());
            RolPerfil rolPerfil = usuario.getRolPerfil();
            for (Producto producto : proCate) {
                *//*double precioNormal = proPrecioService.obtenerPrecioProducto(producto.getId(), rolPerfil.getId());*//*
                double precioTachado = proPrecioService.obtenerPrecioTachado(producto.getId(), rolPerfil.getId());
                int porcentaje = proPrecioService.obtenerPorcentaje(producto.getId(), rolPerfil.getId());
                *//*precios.put(producto.getId(), precioNormal);*//*
                preciosTachados.put(producto.getId(), precioTachado);
                porcentajes.put(producto.getId(), porcentaje);
            }
        } else {
            for (Producto producto : proCate) {
                *//*double precioNormal = proPrecioService.obtenerPrecioProducto(producto.getId(), 1);*//*
                double precioTachado = proPrecioService.obtenerPrecioTachado(producto.getId(), 1);
                int porcentaje = proPrecioService.obtenerPorcentaje(producto.getId(), 1);
                *//*precios.put(producto.getId(), precioNormal);*//*
                preciosTachados.put(producto.getId(), precioTachado);
                porcentajes.put(producto.getId(), porcentaje);
            }
        }

        model.addAttribute("precios", precios);
        model.addAttribute("preciosTachados", preciosTachados);
        model.addAttribute("porcentajes", porcentajes);
        session.setAttribute("precios", precios);

    }*/

	@ModelAttribute("footer")
	public List<Footer> footers() {
		return footerService.listarFooter();
	}

	@ModelAttribute("redesSociales")
	public List<RedesSociales> redesSociales() {
		return redesSocialesService.listarRedes();
	}

	/* OAuth2AuthenticationToken token*/
	@GetMapping("/mi-cuenta")
	public String miCuenta(HttpSession session, Model model) {
		if (session.getAttribute("usuario") != null) {
			Usuario usuario = usuarioService.buscarUsuario(session.getAttribute("usuario").toString());
			UsuarioPerfil usuarioPerfil = usuPerfilService.buscarUsuario(usuario);

			session.setAttribute("usuarioPerfil", usuarioPerfil);
			model.addAttribute("usuarioPerfil", usuarioPerfil);
			return "usuario/cuenta";
		}
/*		if (token.getPrincipal().getAttribute("name") != null) {
			Usuario usuario = usuarioService.buscarUsuario(token.getPrincipal().getAttribute("email"));
			UsuarioPerfil usuarioPerfil = usuPerfilService.buscarUsuario(usuario);

			session.setAttribute("usuarioPerfil", usuarioPerfil);
			model.addAttribute("usuarioPerfil", usuarioPerfil);
			return "usuario/cuenta";
		}*/
		return "redirect:/login";
	}

	@PostMapping("/mi-cuenta/editar-perfil")
	public String editarPerfil(@ModelAttribute("usuarioPerfil") UsuarioPerfil usuarioPerfil, HttpSession session) {
		if (session.getAttribute("usuario") != null) {
			if (usuarioPerfil != null) {
				Usuario usuario = usuarioService.buscarUsuario(session.getAttribute("usuario").toString());
				usuarioPerfil.setUsuario(usuario);
				usuPerfilService.saveUsuPerfil(usuarioPerfil);
				session.setAttribute("usuarioPerfil", usuarioPerfil);
				return "redirect:/usuario/mi-cuenta";
			}
		}
		return "redirect:/index/inicio";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/carrito/finalizarCompra")
	public String finalizarCompra(HttpSession session) {
		if (session.getAttribute("carrito") != null) {
			List<PedidoProducto> carrito = (List<PedidoProducto>) session.getAttribute("carrito");
			int productosTotales = carrito.size();
			double precioTotal = 0.0;
			for (PedidoProducto pedidoPro : carrito) {
				double precio = pedidoPro.getSub_total();
				precioTotal += precio;
			}
			session.setAttribute("productosTotales", productosTotales);
			session.setAttribute("precioTotal", Math.round(precioTotal * 100.00) / 100.00);
			return "redirect:/usuario/pago";
		} else {
			return "redirect:/index/inicio";
		}
	}

	@GetMapping("/pago")
	public String pago(Model model, MetodoPago metodoPago, ComprobantePago comprobante, HttpSession session, HttpServletRequest request) {

		if (session.getAttribute("usuario") != null) {
	        Usuario usuario = usuarioService.buscarUsuario(session.getAttribute("usuario").toString());
	        
	        if (usuario != null) {
	            model.addAttribute("usuario", usuario);
	            List<UsuarioDireccion> direcciones = direccionService.listarDireccionxUsu(usuario);
	            model.addAttribute("direcciones", direcciones);
	        }
	    }
		
		List<MetodoPago> met = metodoService.listarMetodo();
		List<ComprobantePago> comp = comprobanteService.listarComprobante();
		
		model.addAttribute("comprobanteList", comp);
		model.addAttribute("metodo", met);
		model.addAttribute("metodoPago", metodoPago);
		model.addAttribute("comprobante", comprobante);

		return "usuario/pago";
	}

	@GetMapping("/validation")
	@ResponseBody
	public ResponseEntity<PreferenceResponse> validation() {


		MercadoPagoConfig.setAccessToken("APP_USR-8829939863721511-071009-2cdfade3a924b4419593926388dbeebb-1895878454");
		PreferenceClient client = new PreferenceClient();
		String preferenceId = "" ;

		try {
			PreferenceItemRequest itemRequest =
					PreferenceItemRequest.builder()
							.title("SHAABOOM PUMP | FRUIT PUNCH | 385 GR. | 44 SERV")
							.id("4")
							.description("SHAABOOM PUMP | FRUIT PUNCH | 385 GR. | 44 SERV")
							.pictureUrl("https://http2.mlstatic.com/D_NQ_NP_821185-MLU70810327615_082023-O.webp")
							.categoryId("Aminoácidos")
							.quantity(4)
							.currencyId("PEN")
							.unitPrice(new BigDecimal("300")).build();

			List<PreferenceItemRequest> items = new ArrayList<>();
			items.add(itemRequest);

			PreferenceRequest preferenceRequest = PreferenceRequest.builder()
					.backUrls(
							PreferenceBackUrlsRequest.builder()
									.success("http://104.218.48.244:8080/next/index/inicio")
									.failure("https://test.com/failure")
									.pending("https://test.com/pending").build())
					.items(items)
					.payer(
							PreferencePayerRequest.builder()
									.name("Test")
									.surname("User")
									.email("test_email@example.com")
									.phone(PhoneRequest.builder().areaCode("11").number("4444-4444").build())
									.identification(IdentificationRequest.builder()
											.type("DNI")
											.number("7598514236")
											.build())
									.address(AddressRequest.builder()
											.zipCode("06233200")
											.streetName("Street")
											.streetNumber("123")
											.build())
									.build())
					.marketplace("Muscle-Shop")
					//.autoReturn("approved")
					.build();

			Preference preference = client.create(preferenceRequest);
			preferenceId = preference.getInitPoint();

		} catch (MPException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MPApiException e){
			e.printStackTrace();
		}

		PreferenceResponse response = new PreferenceResponse(preferenceId);
		return ResponseEntity.ok(response) ;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("pago")
	public String pagoPed(RedirectAttributes ra, HttpSession session, Model model, HttpServletRequest request) {
		if (session.getAttribute("usuario") != null) {
			if (session.getAttribute("carrito") != null) {
				Usuario usuario = usuarioService.buscarUsuario(session.getAttribute("usuario").toString());
				
				String direccionIdParam = request.getParameter("direccionSelec");
				
				 if (direccionIdParam != null && !direccionIdParam.isEmpty()) {
					 Integer direccionId = Integer.parseInt(direccionIdParam);
					 UsuarioDireccion direccionSeleccionada = direccionService.listarUsuDireccion(direccionId);
					 
					 List<PedidoProducto> contenidoCart = (List<PedidoProducto>) session.getAttribute("carrito");
						Long datetime = System.currentTimeMillis();
						Timestamp fechaActual = new Timestamp(datetime);
						String numPedido = String.valueOf(usuario.getId()) + datetime.toString();
						
						Double total = 0.0;
						for (PedidoProducto contenido : contenidoCart) {
							double precio = contenido.getSub_total();
							total += precio;
						}
												
		  	            String metodoPagoIdParam = request.getParameter("metodoPago.id");
			            String comprobanteIdParam = request.getParameter("comprobante.id");
			            String ubigeoIdParam = request.getParameter("ubigeo_id");

			            Integer metodoPagoId = null;
			            Integer comprobanteId = null;
			            Integer ubigeoId = null;

			            if (metodoPagoIdParam != null && !metodoPagoIdParam.isEmpty()) {
			                metodoPagoId = Integer.parseInt(metodoPagoIdParam);
			            }

			            if (comprobanteIdParam != null && !comprobanteIdParam.isEmpty()) {
			                comprobanteId = Integer.parseInt(comprobanteIdParam);
			            }

			            if (ubigeoIdParam != null && !ubigeoIdParam.isEmpty()) {
			                ubigeoId = Integer.parseInt(ubigeoIdParam);
			            }

			            MetodoPago metodoPago = metodoPagoId != null ? metodoService.listarMetodoID(metodoPagoId) : null;
			            ComprobantePago comprobante = comprobanteId != null ? comprobanteService.listarComproID(comprobanteId) : null;
			            Ubigeo ubigeo = ubigeoId != null ? ubigeoService.obtenerUbigeoId(ubigeoId) : null;
			            
			            double costoEnvio = 0.0;
			            if (ubigeo != null) {
			                costoEnvio = ubigeo.getCosto();
			            }
			            
			            double totalPedido = total + costoEnvio;

						Pedidos pedidoNuevo = new Pedidos(fechaActual, Math.round(totalPedido * 100.00) / 100.00, numPedido, usuario, metodoPago,
								comprobante,ubigeo, contenidoCart, direccionSeleccionada);
						pedidoService.guardarPedido(pedidoNuevo);
						
						for (PedidoProducto contenido : contenidoCart) {
							int cantidad = contenido.getCantidad();
							Producto producto = contenido.getProducto();
							double subtotal = contenido.getSub_total();
							ProductoPropiedadesDetalles productoPropiedadesDetalles = contenido.getProductoProDetal();

							if (productoPropiedadesDetalles == null) {
								PedidoProducto pedidoProducto = new PedidoProducto(cantidad, subtotal, producto, pedidoNuevo, null);
						        pedProService.guardarPedidoPro(pedidoProducto);
						    } else {
						        PedidoProducto pedidoProducto = new PedidoProducto(cantidad, subtotal, producto, pedidoNuevo, productoPropiedadesDetalles);
						        pedProService.guardarPedidoPro(pedidoProducto);
						    }
						}

						session.setAttribute("pedido", pedidoNuevo);
						session.setAttribute("fechaActual", new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));


						List<PedidoProducto> detallePro = (List<PedidoProducto>) session.getAttribute("carrito");
						List<PedidoProducto> detalleProCopia = new ArrayList<>(detallePro);
						ra.addFlashAttribute("detalleProducto", detalleProCopia);
						session.setAttribute("carrito", null);

						return "redirect:/usuario/boleta";	
						
				 } else {
					 	String direccion = request.getParameter("direccion");
				        String referencia = request.getParameter("referencia");
				        
				        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
			            usuarioDireccion.setDireccion(direccion);
			            usuarioDireccion.setReferencia(referencia);
			            
			            usuarioDireccion.setUsuario(usuario);
			            
			            direccionService.saveUsuDireccion(usuarioDireccion);

						List<PedidoProducto> contenidoCart = (List<PedidoProducto>) session.getAttribute("carrito");
						Long datetime = System.currentTimeMillis();
						Timestamp fechaActual = new Timestamp(datetime);
						String numPedido = String.valueOf(usuario.getId()) + datetime.toString();
						
						Double total = 0.0;
						for (PedidoProducto contenido : contenidoCart) {
							double precio = contenido.getSub_total();
							total += precio;
						}
												
		  	            String metodoPagoIdParam = request.getParameter("metodoPago.id");
			            String comprobanteIdParam = request.getParameter("comprobante.id");
			            String ubigeoIdParam = request.getParameter("ubigeo_id");

			            Integer metodoPagoId = null;
			            Integer comprobanteId = null;
			            Integer ubigeoId = null;

			            if (metodoPagoIdParam != null && !metodoPagoIdParam.isEmpty()) {
			                metodoPagoId = Integer.parseInt(metodoPagoIdParam);
			            }

			            if (comprobanteIdParam != null && !comprobanteIdParam.isEmpty()) {
			                comprobanteId = Integer.parseInt(comprobanteIdParam);
			            }

			            if (ubigeoIdParam != null && !ubigeoIdParam.isEmpty()) {
			                ubigeoId = Integer.parseInt(ubigeoIdParam);
			            }

			            MetodoPago metodoPago = metodoPagoId != null ? metodoService.listarMetodoID(metodoPagoId) : null;
			            ComprobantePago comprobante = comprobanteId != null ? comprobanteService.listarComproID(comprobanteId) : null;
			            Ubigeo ubigeo = ubigeoId != null ? ubigeoService.obtenerUbigeoId(ubigeoId) : null;
			            
			            double costoEnvio = 0.0;
			            if (ubigeo != null) {
			                costoEnvio = ubigeo.getCosto();
			            }
			            
			            double totalPedido = total + costoEnvio;

						Pedidos pedidoNuevo = new Pedidos(fechaActual, Math.round(totalPedido * 100.00) / 100.00, numPedido, usuario, metodoPago,
								comprobante,ubigeo, contenidoCart, usuarioDireccion); 
						pedidoService.guardarPedido(pedidoNuevo);
						
						for (PedidoProducto contenido : contenidoCart) {
							int cantidad = contenido.getCantidad();
							Producto producto = contenido.getProducto();
							double subtotal = contenido.getSub_total();
							ProductoPropiedadesDetalles productoPropiedadesDetalles = contenido.getProductoProDetal();

							if (productoPropiedadesDetalles == null) {
								PedidoProducto pedidoProducto = new PedidoProducto(cantidad, subtotal, producto, pedidoNuevo, null);
						        pedProService.guardarPedidoPro(pedidoProducto);
						    } else {
						        PedidoProducto pedidoProducto = new PedidoProducto(cantidad, subtotal, producto, pedidoNuevo, productoPropiedadesDetalles);
						        pedProService.guardarPedidoPro(pedidoProducto);
						    }
						}

						session.setAttribute("pedido", pedidoNuevo);
						session.setAttribute("fechaActual", new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));


						List<PedidoProducto> detallePro = (List<PedidoProducto>) session.getAttribute("carrito");
						List<PedidoProducto> detalleProCopia = new ArrayList<>(detallePro);
						ra.addFlashAttribute("detalleProducto", detalleProCopia);
						session.setAttribute("carrito", null);

						return "redirect:/usuario/boleta";
				 }
			}
			return "redirect:/index";

		} else {
			String correo = request.getParameter("correo");
			String password = request.getParameter("password");
			String nombres = request.getParameter("nombres");
			String apellidos = request.getParameter("apellidos");
			String telefono = request.getParameter("telefono");
			String direccion = request.getParameter("direccion");
		    String referencia = request.getParameter("referencia");

			Usuario nuevoUsuario = new Usuario();
			nuevoUsuario.setCorreo(correo);
			String passwordEncriptada = new BCryptPasswordEncoder().encode(password);
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

			UsuarioPerfil nuevoUsuarioPerfil = new UsuarioPerfil();
			nuevoUsuarioPerfil.setNombres(nombres);
			nuevoUsuarioPerfil.setApellidos(apellidos);
			nuevoUsuarioPerfil.setTelefono(telefono);
			nuevoUsuarioPerfil.setUsuario(nuevoUsuario);
			
			UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
		    usuarioDireccion.setDireccion(direccion);
		    usuarioDireccion.setReferencia(referencia);
		    usuarioDireccion.setUsuario(nuevoUsuario);

			usuPerfilService.saveUsuPerfil(nuevoUsuarioPerfil);
		    direccionService.saveUsuDireccion(usuarioDireccion);

			List<PedidoProducto> contenidoCart = (List<PedidoProducto>) session.getAttribute("carrito");
			Long datetime = System.currentTimeMillis();
			Timestamp fechaActual = new Timestamp(datetime);
			String numPedido = String.valueOf(nuevoUsuario.getId()) + datetime.toString();

			Double total = 0.0;
			for (PedidoProducto contenido : contenidoCart) {
				double precio = contenido.getSub_total();
				total += precio;
			}
			
			String metodoPagoIdParam = request.getParameter("metodoPago.id");
            String comprobanteIdParam = request.getParameter("comprobante.id");
            String ubigeoIdParam = request.getParameter("ubigeo_id");

            Integer metodoPagoId = null;
            Integer comprobanteId = null;
            Integer ubigeoId = null;

            if (metodoPagoIdParam != null && !metodoPagoIdParam.isEmpty()) {
                metodoPagoId = Integer.parseInt(metodoPagoIdParam);
            }

            if (comprobanteIdParam != null && !comprobanteIdParam.isEmpty()) {
                comprobanteId = Integer.parseInt(comprobanteIdParam);
            }

            if (ubigeoIdParam != null && !ubigeoIdParam.isEmpty()) {
                ubigeoId = Integer.parseInt(ubigeoIdParam);
            }

            MetodoPago metodoPago = metodoPagoId != null ? metodoService.listarMetodoID(metodoPagoId) : null;
            ComprobantePago comprobante = comprobanteId != null ? comprobanteService.listarComproID(comprobanteId) : null;
            Ubigeo ubigeo = ubigeoId != null ? ubigeoService.obtenerUbigeoId(ubigeoId) : null;
            
            double costoEnvio = 0.0;
            if (ubigeo != null) {
                costoEnvio = ubigeo.getCosto();
            }
            
            double totalPedido = total + costoEnvio;
           

			Pedidos pedidoNuevo = new Pedidos(fechaActual, Math.round(totalPedido * 100.00) / 100.00, numPedido, nuevoUsuario, metodoPago,
					comprobante,ubigeo, contenidoCart, usuarioDireccion);
			pedidoService.guardarPedido(pedidoNuevo);

			for (PedidoProducto contenido : contenidoCart) {
				int cantidad = contenido.getCantidad();
				Producto producto = contenido.getProducto();
				double subtotal = contenido.getSub_total();
				ProductoPropiedadesDetalles productoPropiedadesDetalles = contenido.getProductoProDetal();

				if (productoPropiedadesDetalles == null) {
					PedidoProducto pedidoProducto = new PedidoProducto(cantidad, subtotal, producto, pedidoNuevo, null);
			        pedProService.guardarPedidoPro(pedidoProducto);
			    } else {
			        PedidoProducto pedidoProducto = new PedidoProducto(cantidad, subtotal, producto, pedidoNuevo, productoPropiedadesDetalles);
			        pedProService.guardarPedidoPro(pedidoProducto);
			    }
			}

			session.setAttribute("pedido", pedidoNuevo);
			session.setAttribute("fechaActual", new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));

			List<PedidoProducto> detallePro = (List<PedidoProducto>) session.getAttribute("carrito");
			List<PedidoProducto> detalleProCopia = new ArrayList<>(detallePro);
			ra.addFlashAttribute("detalleProducto", detalleProCopia);
			session.setAttribute("carrito", null);

			return "redirect:/usuario/boleta";
		}
	}
	
	@GetMapping("/boleta")
	public String cargaConfirmacion(Model model) {
		model.addAttribute("detalleProducto", model.getAttribute("detalleProducto"));
		return "usuario/boleta";
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
		return ubigeoService.listaDistrito(region, provincia);
	}
	
	


	
}