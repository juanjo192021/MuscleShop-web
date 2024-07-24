package com.muscleshop.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.muscleshop.web.models.Articulo;
import com.muscleshop.web.models.ArticuloComentario;
import com.muscleshop.web.models.Banner;
import com.muscleshop.web.models.Cupon;
import com.muscleshop.web.models.CuponCategoria;
import com.muscleshop.web.models.Estado;
import com.muscleshop.web.models.Footer;
import com.muscleshop.web.models.Header;
import com.muscleshop.web.models.Logos;
import com.muscleshop.web.models.Menu;
import com.muscleshop.web.models.MenuSub;
import com.muscleshop.web.models.PedidoProducto;
import com.muscleshop.web.models.PedidoProductoComentario;
import com.muscleshop.web.models.Popup;
import com.muscleshop.web.models.Producto;
import com.muscleshop.web.models.ProductoCategoria;
import com.muscleshop.web.models.ProductoProDetal;
import com.muscleshop.web.models.PropiedadesDetalles;
import com.muscleshop.web.models.RedesSociales;
import com.muscleshop.web.models.RolPerfil;
import com.muscleshop.web.models.Usuario;
import com.muscleshop.web.services.ArticuloComService;
import com.muscleshop.web.services.ArticuloService;
import com.muscleshop.web.services.BannerService;
import com.muscleshop.web.services.CuponService;
import com.muscleshop.web.services.EstadoService;
import com.muscleshop.web.services.FooterService;
import com.muscleshop.web.services.HeaderService;
import com.muscleshop.web.services.LogosService;
import com.muscleshop.web.services.MenuService;
import com.muscleshop.web.services.MenuSubService;
import com.muscleshop.web.services.PedProComentarioService;
import com.muscleshop.web.services.PopupService;
import com.muscleshop.web.services.ProCategoriaService;
import com.muscleshop.web.services.ProDetalService;
import com.muscleshop.web.services.ProductoPrecioService;
import com.muscleshop.web.services.ProductoProDetalService;
import com.muscleshop.web.services.ProductoService;
import com.muscleshop.web.services.RedesSocialesService;
import com.muscleshop.web.services.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/index")
public class HomeController {

	@Autowired
	ProductoService productoService;

	@Autowired
	BannerService bannerService;

	@Autowired
	PopupService popupService;

	@Autowired
	MenuService menuService;

	@Autowired
	ArticuloService articuloService;

	@Autowired
	ArticuloComService artiComService;

	@Autowired
	EstadoService estadoService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	MenuSubService menuSubService;

	@Autowired
	ProCategoriaService proCateService;

	@Autowired
	ProductoPrecioService proPrecioService;

	@Autowired
	ProDetalService proDetalService;

	@Autowired
	PedProComentarioService pedProComService;

	@Autowired
	FooterService footerService;

	@Autowired
	RedesSocialesService redesSocialesService;

	@Autowired
	HeaderService headerService;
	
	@Autowired
	ProductoProDetalService productoProDetService;
	
	@Autowired
	LogosService logosService;
	
	@Autowired
	CuponService cuponService;

	@ModelAttribute("menu")
	public List<Menu> categorias() {
		return menuService.listarMenu();
	}

	@ModelAttribute("productos")
	public List<Producto> productos() {
		return productoService.listarProducto();
	}

	@ModelAttribute("footer")
	public List<Footer> footer() {
		return footerService.listarFooter();
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
	
	@ModelAttribute("logos")
	public List<Logos> obtenerLogos() {
		return logosService.listarLogos();
	}

	@ModelAttribute("redesSociales")
	public List<RedesSociales> obtenerRedes() {
		return redesSocialesService.listarRedes();
	}
	
	@ModelAttribute
    public void addCommonAttributes(Model model, HttpSession session) {
        List<Producto> proCate = productoService.listarProducto();

        Map<Integer, Double> precios = new HashMap<>();
        Map<Integer, Double> preciosTachados = new HashMap<>();
        Map<Integer, Integer> porcentajes = new HashMap<>();

        if (session.getAttribute("usuario") != null) {
            Usuario usuario = usuarioService.buscarUsuario(session.getAttribute("usuario").toString());
            RolPerfil rolPerfil = usuario.getRolPerfil();
            for (Producto producto : proCate) {
                double precioNormal = proPrecioService.obtenerPrecioProducto(producto.getId(), rolPerfil.getId());
                double precioTachado = proPrecioService.obtenerPrecioTachado(producto.getId(), rolPerfil.getId());
                int porcentaje = proPrecioService.obtenerPorcentaje(producto.getId(), rolPerfil.getId());
                precios.put(producto.getId(), precioNormal);
                preciosTachados.put(producto.getId(), precioTachado);
                porcentajes.put(producto.getId(), porcentaje);
            }
        } else {
            for (Producto producto : proCate) {
                double precioNormal = proPrecioService.obtenerPrecioProducto(producto.getId(), 1);
                double precioTachado = proPrecioService.obtenerPrecioTachado(producto.getId(), 1);
                int porcentaje = proPrecioService.obtenerPorcentaje(producto.getId(), 1);
                precios.put(producto.getId(), precioNormal);
                preciosTachados.put(producto.getId(), precioTachado);
                porcentajes.put(producto.getId(), porcentaje);
            }
        }

        model.addAttribute("precios", precios);
        model.addAttribute("preciosTachados", preciosTachados);
        model.addAttribute("porcentajes", porcentajes);
        session.setAttribute("precios", precios);

    }

	@GetMapping("/prueba")
	@ResponseBody
	public ResponseEntity<List<Banner>> resp(){
		/*List<PedidoProductoComentario> mostrarCom = pedProComService.comentariosMostrables();*/
		List<Banner> ban = bannerService.listarBanner()
				.stream()
				.filter(banner -> "movil_tablet".equals(banner.getTipoDispositivo()))
				.collect(Collectors.toList());
		return  ResponseEntity.ok(ban);
	}
	// MENÚS
	@SuppressWarnings("unchecked")
	@GetMapping("/inicio")
	public String Inicio(Model model, HttpSession session) {

		List<Producto> pro = productoService.listarProducto();
		List<Banner> banner = bannerService.listarBanner();
		List<Banner> bannerMovilTablet = banner.stream()
				.filter(ban -> "movil_tablet".equals(ban.getTipoDispositivo()))
				.collect(Collectors.toList());
		List<Banner> bannerLaptopPc = banner.stream()
				.filter(ban-> "laptop_pc".equals(ban.getTipoDispositivo()))
				.collect(Collectors.toList());

		List<Popup> pop = popupService.listarPopup();
		/*List<Articulo> articulosBlog = articuloService.listarArticulo();*/
		Integer cantidadArticulos = 3;
		Page<Articulo> articulosBlog = articuloService.listarArticuloPorPage(cantidadArticulos);


		model.addAttribute("productos", pro);
		model.addAttribute("popup", pop);
		model.addAttribute("banner", banner);
		model.addAttribute("bannerMovilTablet",bannerMovilTablet);
		model.addAttribute("bannerLaptopPc", bannerLaptopPc);

		boolean isFirstMovilTabletActive = bannerLaptopPc.isEmpty() && !bannerMovilTablet.isEmpty();

		model.addAttribute("isFirstMovilTabletActive", isFirstMovilTabletActive);

		model.addAttribute("articulosBlog", articulosBlog);

		Integer menuId = 3;
		List<MenuSub> subMenu = menuSubService.obtenerMenuID(menuId);
		model.addAttribute("subMenu", subMenu);

		List<Producto> proForma = productoService.listarProForma(2);
		model.addAttribute("proForma", proForma);

		List<PedidoProductoComentario> mostrarCom = pedProComService.comentariosMostrables();
		model.addAttribute("mostrarCom", mostrarCom);
		
		// CARRITO
		if (session.getAttribute("usuario") != null) {
			Usuario usuario = usuarioService.buscarUsuario(session.getAttribute("usuario").toString());

			if (usuario != null && usuario.getRolPerfil() != null && usuario.getRolPerfil().getId() == 2) {
				session.removeAttribute("carrito");
				session.removeAttribute("totalCarrito");
			}
		}

		Object carritoSession = session.getAttribute("carrito");
		List<PedidoProducto> carrito;

		if (carritoSession == null) {
			carrito = new ArrayList<PedidoProducto>();
		} else {
			carrito = (List<PedidoProducto>) carritoSession;
		}

		session.setAttribute("carrito", carrito);

		return "inicio";
	}

	@GetMapping("/inicio/{categoriaUrl}")
	public String listarProCateInicio(Model model, @PathVariable String categoriaUrl, HttpSession session) {

		ProductoCategoria proCategoria = proCateService.obtenerUrl(categoriaUrl);
		List<Producto> proCate = productoService.listarProCate(categoriaUrl);

		model.addAttribute("proCate", proCate);
		model.addAttribute("proCateUrl", proCategoria);
		model.addAttribute("nombreCategoria", proCategoria.getNombre());

		return "anonimo/inicioCate";
	}
/*

MODIFICAR 22-07-2024

* */
	@GetMapping("/inicio/{categoriaUrl}/{id}")
	public String verProductoInicio(@PathVariable("id") int id, @PathVariable String categoriaUrl, Model model,
			HttpSession session) {

		ProductoCategoria procate = proCateService.obtenerUrl(categoriaUrl);

		Producto proDetalles = productoService.listarProductoPorID(id);

		List<ProductoProDetal> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1); 
		List<ProductoProDetal> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3); 
		List<ProductoProDetal> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoProDetal> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);
        
        model.addAttribute("detallePresentacion", detallePresentacion);
        model.addAttribute("detalleTam", detalleTam);
        model.addAttribute("detalleColor", detalleColor);
        model.addAttribute("detalleSabor", detalleSabor);
		
		model.addAttribute("proCateUrl", procate);
		model.addAttribute("producto", proDetalles);
		

		return "anonimo/detalle";
	}

	// MENÚ PRODUCTOS
	@GetMapping("/categorias")
	public String menuProducto(Model model, HttpSession session) {

		Integer menuId = 2;
		List<MenuSub> subMenu = menuSubService.obtenerMenuID(menuId);
		model.addAttribute("subMenu", subMenu);
		Menu menu = menuService.listarMenuID(menuId);
		model.addAttribute("cate", menu);

		return "porProductos/menuProducto";
	}

	@GetMapping("/categorias/{MenuSubUrl}")
	public String productoCategoria(Model model, @PathVariable String MenuSubUrl, HttpSession session) {

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		List<ProductoCategoria> proCate = proCateService.listarPorMenuSub(menuSub);

		List<Producto> productos = productoService.listarPorMenuSub(menuSub);
		model.addAttribute("productos", productos);
		
		Integer menuIdCate = 2;
		List<MenuSub> subMenuCate = menuSubService.obtenerMenuID(menuIdCate);
		model.addAttribute("subMenuCate", subMenuCate);

		Integer menuId = 3;
		List<MenuSub> subMenu = menuSubService.obtenerMenuID(menuId);
		model.addAttribute("subMenu", subMenu);

		Integer menuMarcasId = 4;
		List<MenuSub> subMenumarcas = menuSubService.obtenerMenuID(menuMarcasId);
		model.addAttribute("subMenumarcas", subMenumarcas);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("proCate", proCate);

		return "porProductos/subMenuProductos";
	}

	@GetMapping("/categorias/{MenuSubUrl}/{categoriaUrl}")
	public String listarProCate(Model model, HttpSession session, @PathVariable String MenuSubUrl, @PathVariable String categoriaUrl) {

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		ProductoCategoria proCategoria = proCateService.obtenerUrl(categoriaUrl);
		List<Producto> proCate = productoService.listarProCate(categoriaUrl);

		model.addAttribute("proCate", proCate);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("proCateUrl", proCategoria);
		model.addAttribute("nombreCategoria", proCategoria.getNombre());
		return "porProductos/categoriaPro";
	}

	@GetMapping("/categorias/{MenuSubUrl}/{categoriaUrl}/{id}")
	public String verProducto(@PathVariable("id") int id, @PathVariable("MenuSubUrl") String MenuSubUrl,
			@PathVariable String categoriaUrl, Model model, HttpSession session) {

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		ProductoCategoria procate = proCateService.obtenerUrl(categoriaUrl);

		Producto proDetalles = productoService.listarProductoPorID(id);

		List<ProductoProDetal> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1); 
		List<ProductoProDetal> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3); 
		List<ProductoProDetal> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoProDetal> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);
        
        model.addAttribute("detallePresentacion", detallePresentacion);
        model.addAttribute("detalleTam", detalleTam);
        model.addAttribute("detalleColor", detalleColor);
        model.addAttribute("detalleSabor", detalleSabor);

		model.addAttribute("menuSub", menuSub);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("proCateUrl", procate);
		model.addAttribute("producto", proDetalles);

		return "porProductos/detalleProducto";
	}

	// MENÚ POR OBJETIVOS
	@GetMapping("/objetivo")
	public String menuObjetivos(Model model, HttpSession session) {

		Integer menuId = 3;
		List<MenuSub> subMenu = menuSubService.obtenerMenuID(menuId);
		model.addAttribute("subMenu", subMenu);
		Menu menu = menuService.listarMenuID(menuId);
		model.addAttribute("obj", menu);

		return "porObjetivos/menuObjetivo";
	}

	@GetMapping("/objetivo/{MenuSubUrl}")
	public String objetivosCate(Model model, @PathVariable String MenuSubUrl, HttpSession session) {
		Integer menuId = 3;
		List<MenuSub> subMenu = menuSubService.obtenerMenuID(menuId);
		model.addAttribute("subMenu", subMenu);

		Integer menuProId = 2;
		List<MenuSub> subMenuPro = menuSubService.obtenerMenuID(menuProId);
		model.addAttribute("subMenuPro", subMenuPro);

		Integer menuMarcasId = 4;
		List<MenuSub> subMenumarcas = menuSubService.obtenerMenuID(menuMarcasId);
		model.addAttribute("subMenumarcas", subMenumarcas);

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);

		List<Producto> productos = productoService.listarPorMenuSub(menuSub);
		model.addAttribute("productos", productos);

		model.addAttribute("menuSub", menuSub);
		model.addAttribute("nombreObjetivo", menuSub.getNombre());
		return "porObjetivos/subMenuObjetivos";
	}

	@GetMapping("/objetivo/{MenuSubUrl}/{categoriaUrl}")
	public String listarObjCate(Model model, @PathVariable String MenuSubUrl, @PathVariable String categoriaUrl, HttpSession session) {

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		ProductoCategoria proCategoria = proCateService.obtenerUrl(categoriaUrl);
		List<Producto> proCate = productoService.listarProCate(categoriaUrl);

		model.addAttribute("proCate", proCate);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("proCateUrl", proCategoria);
		model.addAttribute("nombreCategoria", proCategoria.getNombre());
		return "porObjetivos/categoriaObj";
	}

	@GetMapping("/objetivo/{MenuSubUrl}/{categoriaUrl}/{id}")
	public String verProductoObj(@PathVariable("id") int id, @PathVariable("MenuSubUrl") String MenuSubUrl,
			@PathVariable String categoriaUrl, Model model, HttpSession session) {


		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		ProductoCategoria procate = proCateService.obtenerUrl(categoriaUrl);

		Producto proDetalles = productoService.listarProductoPorID(id);

		List<ProductoProDetal> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1); 
		List<ProductoProDetal> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3); 
		List<ProductoProDetal> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoProDetal> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);
        
        model.addAttribute("detallePresentacion", detallePresentacion);
        model.addAttribute("detalleTam", detalleTam);
        model.addAttribute("detalleColor", detalleColor);
        model.addAttribute("detalleSabor", detalleSabor);

		model.addAttribute("menuSub", menuSub);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("proCateUrl", procate);
		model.addAttribute("producto", proDetalles);

		return "porObjetivos/detalleProObj";
	}

	// MENÚ MARCAS
	@GetMapping("/marcas")
	public String menuMarca(Model model, HttpSession session) {

		Integer menuId = 4;
		List<MenuSub> subMenu = menuSubService.obtenerMenuID(menuId);
		model.addAttribute("subMenu", subMenu);

		return "porMarcas/menuMarca";
	}

	@GetMapping("/marcas/{MenuSubUrl}")
	public String marcasCate(Model model, @PathVariable String MenuSubUrl, HttpSession session) {
		Integer menuId = 3;
		List<MenuSub> subMenu = menuSubService.obtenerMenuID(menuId);
		model.addAttribute("subMenu", subMenu);

		Integer menuProId = 2;
		List<MenuSub> subMenuPro = menuSubService.obtenerMenuID(menuProId);
		model.addAttribute("subMenuPro", subMenuPro);

		Integer menuMarcaId = 4;
		List<MenuSub> subMenuMarcas = menuSubService.obtenerMenuID(menuMarcaId);
		model.addAttribute("subMenuMarcas", subMenuMarcas);

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);

		List<Producto> productos = productoService.listarPorMenuSub(menuSub);
		model.addAttribute("productos", productos);


		model.addAttribute("menuSub", menuSub);
		model.addAttribute("nombreMarca", menuSub.getNombre());
		model.addAttribute("imagenMarca", menuSub.getImagen());

		return "porMarcas/subMenuMarcas";
	}

	@GetMapping("/marcas/{MenuSubUrl}/{categoriaUrl}")
	public String listarMarcaCate(Model model, @PathVariable String MenuSubUrl, @PathVariable String categoriaUrl, HttpSession session) {

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		ProductoCategoria proCategoria = proCateService.obtenerUrl(categoriaUrl);
		List<Producto> proCate = productoService.listarProCate(categoriaUrl);

		model.addAttribute("proCate", proCate);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("proCateUrl", proCategoria);
		model.addAttribute("nombreCategoria", proCategoria.getNombre());
		return "porMarcas/categoriaMarca";
	}

	@GetMapping("/marcas/{MenuSubUrl}/{categoriaUrl}/{id}")
	public String verProductoMarca(@PathVariable("id") int id, @PathVariable("MenuSubUrl") String MenuSubUrl,
			@PathVariable String categoriaUrl, Model model, HttpSession session) {

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		ProductoCategoria procate = proCateService.obtenerUrl(categoriaUrl);

		Producto proDetalles = productoService.listarProductoPorID(id);

		List<ProductoProDetal> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1); 
		List<ProductoProDetal> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3); 
		List<ProductoProDetal> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoProDetal> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);
        
        model.addAttribute("detallePresentacion", detallePresentacion);
        model.addAttribute("detalleTam", detalleTam);
        model.addAttribute("detalleColor", detalleColor);
        model.addAttribute("detalleSabor", detalleSabor);

		model.addAttribute("menuSub", menuSub);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("proCateUrl", procate);
		model.addAttribute("producto", proDetalles);

		return "porMarcas/detalleProMarca";
	}

	// MENÚ LIFESTYLE
	@GetMapping("/lifestyle")
	public String menuLifestyle(Model model, HttpSession session) {

		Integer menuId = 5;
		List<MenuSub> subMenu = menuSubService.obtenerMenuID(menuId);
		model.addAttribute("subMenu", subMenu);

		return "lifestyle/menuLifestyle";
	}

	@GetMapping("/lifestyle/{MenuSubUrl}")
	public String lifestyle(Model model, @PathVariable String MenuSubUrl) {

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		List<ProductoCategoria> proCate = proCateService.listarPorMenuSub(menuSub);
		List<ProductoCategoria> subMenu2 = proCateService.listarPorMenuSubId(24);
		List<ProductoCategoria> subMenu3 = proCateService.listarPorMenuSubId(25);
		List<ProductoCategoria> subMenu1 = proCateService.listarPorMenuSubId(23);

		List<Producto> productos = new ArrayList<>();
		for (ProductoCategoria categoria : proCate) {
			productos.addAll(productoService.listarPorCategoria(categoria));
		}
		model.addAttribute("productos", productos);

		model.addAttribute("proCate", proCate);
		model.addAttribute("subMenu1", subMenu1);
		model.addAttribute("subMenu2", subMenu2);
		model.addAttribute("subMenu3", subMenu3);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("nombreLifestyle", menuSub.getNombre());

		return "lifestyle/subMenuLife";
	}

	@GetMapping("/lifestyle/{MenuSubUrl}/{categoriaUrl}")
	public String listarLifeCate(Model model, @PathVariable String MenuSubUrl, @PathVariable String categoriaUrl, HttpSession session) {

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		ProductoCategoria proCategoria = proCateService.obtenerUrl(categoriaUrl);
		List<Producto> proCate = productoService.listarProCate(categoriaUrl);

		model.addAttribute("proCate", proCate);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("proCateUrl", proCategoria);
		model.addAttribute("nombreCategoria", proCategoria.getNombre());
		return "lifestyle/categoriaLife";
	}

	@GetMapping("/lifestyle/{MenuSubUrl}/{categoriaUrl}/{id}")
	public String verProLifestyle(@PathVariable("id") int id, @PathVariable("MenuSubUrl") String MenuSubUrl,
			@PathVariable String categoriaUrl, Model model, HttpSession session) {

		MenuSub menuSub = menuSubService.obtenerUrl(MenuSubUrl);
		ProductoCategoria procate = proCateService.obtenerUrl(categoriaUrl);

		Producto proDetalles = productoService.listarProductoPorID(id);

		List<ProductoProDetal> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1); 
		List<ProductoProDetal> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3); 
		List<ProductoProDetal> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoProDetal> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);
        
        model.addAttribute("detallePresentacion", detallePresentacion);
        model.addAttribute("detalleTam", detalleTam);
        model.addAttribute("detalleColor", detalleColor);
        model.addAttribute("detalleSabor", detalleSabor);

		model.addAttribute("menuSub", menuSub);
		model.addAttribute("menuSub", menuSub);
		model.addAttribute("proCateUrl", procate);
		model.addAttribute("producto", proDetalles);

		return "lifestyle/detalleProLife";
	}

	// MENÚ BLOG
	@GetMapping("/blog")
	public String blog(HttpSession session, Model model) {
		List<Articulo> arti = articuloService.listarArticulo();
		model.addAttribute("articulo", arti);
		return "porBlog/menuBlog";
	}

	@GetMapping("blog/ver-blog/{id}")
	public String verBlog(@PathVariable("id") int id, Model model, HttpSession session) {
		Articulo artiVer = articuloService.listarArticuloID(id);
		session.setAttribute("articulo", artiVer);

		List<Articulo> arti = articuloService.listarArticulo();
		int size = arti.size();
		int startIndex = Math.max(size - 2, 0);
		List<Articulo> ultimosArti = arti.subList(startIndex, size);
		model.addAttribute("listaArticulos", ultimosArti);
		model.addAttribute("articulosList", arti);
		return "porBlog/blogID";
	}

	@PostMapping("/blog/agregar-comentario/{id}")
	public String agregarComentario(@PathVariable("id") int id, @RequestParam("nombre") String nombre,
			@RequestParam("comentario") String comentario, HttpSession session) {
		Articulo articulo = articuloService.listarArticuloID(id);

		ArticuloComentario nuevoComentario = new ArticuloComentario();
		nuevoComentario.setArticulo(articulo);
		nuevoComentario.setNombre(nombre);
		nuevoComentario.setComentario(comentario);

		Estado estado = estadoService.listarEstadoID(1);
		nuevoComentario.setEstado(estado);

		artiComService.saveArticuloCom(nuevoComentario);
		return "redirect:/index/blog/ver-blog/" + id;
	}

	// SECCIÓN OFERTAS
	@GetMapping("/ofertas")
	public String ofertas(Model model, HttpSession session) {
		List<Producto> proForma = productoService.listarProForma(2);
		model.addAttribute("proForma", proForma);
		return "ofertas/menuOfertas";
	}

	@GetMapping("/ofertas/{id}")
	public String verOfertaID(@PathVariable("id") int id, Model model, HttpSession session) {
		Producto proDetalles = productoService.listarProductoPorID(id);
		List<ProductoProDetal> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1); 
		List<ProductoProDetal> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3); 
		List<ProductoProDetal> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoProDetal> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);
        
        model.addAttribute("detallePresentacion", detallePresentacion);
        model.addAttribute("detalleTam", detalleTam);
        model.addAttribute("detalleColor", detalleColor);
        model.addAttribute("detalleSabor", detalleSabor);
		model.addAttribute("producto", proDetalles);

		return "ofertas/detallesOferta";
	}

	// CARRITO DE COMPRAS
	@GetMapping("/carrito")
	public String carrito(Model model, HttpSession session) {

		List<Producto> proCate = productoService.listarProducto();

		Map<Integer, Double> precios    = new HashMap<>();
		Map<Integer, Double> preciosTachados = new HashMap<>();
		Map<Integer, Integer> porcentajes = new HashMap<>();
		
		if (session.getAttribute("usuario") != null) {
		    Usuario usuario = usuarioService.buscarUsuario(session.getAttribute("usuario").toString());
		    RolPerfil rolPerfil = usuario.getRolPerfil();
		    for (Producto producto : proCate) {
		        double precioNormal = proPrecioService.obtenerPrecioProducto(producto.getId(), rolPerfil.getId());
		        double precioTachado = proPrecioService.obtenerPrecioTachado(producto.getId(), rolPerfil.getId());
		        int porcentaje = proPrecioService.obtenerPorcentaje(producto.getId(), rolPerfil.getId());
		        precios.put(producto.getId(), precioNormal);
		        preciosTachados.put(producto.getId(), precioTachado);
		        porcentajes.put(producto.getId(), porcentaje);
		    }
		} else {
		    for (Producto producto : proCate) {
		        double precioNormal = proPrecioService.obtenerPrecioProducto(producto.getId(), 1); 
		        double precioTachado = proPrecioService.obtenerPrecioTachado(producto.getId(), 1);
		        int porcentaje = proPrecioService.obtenerPorcentaje(producto.getId(), 1);
		        precios.put(producto.getId(), precioNormal);
		        preciosTachados.put(producto.getId(), precioTachado);
		        porcentajes.put(producto.getId(), porcentaje);
		    }
		}
		
		model.addAttribute("precios", precios);
		model.addAttribute("preciosTachados", preciosTachados);
		model.addAttribute("porcentajes", porcentajes);
		
		
		return "anonimo/carrito";
	}

	// MÉTODO PARA AGREGAR PRODUCTOS AL CARRITO
	@SuppressWarnings("unchecked")
	@PostMapping("/productoAgregar")
	public String agregarProducto(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "id", required = false) int id,
			@RequestParam(name = "cantidad", required = false, defaultValue = "1") int cantidad,
	        @RequestParam(name = "idPresentacion", required = false) Integer idPresentacion,
			RedirectAttributes ra) {
		

		Producto producto = productoService.listarProductoPorID(id);
		ProductoProDetal productoProDetal = null;

		if (idPresentacion != null) {
	        productoProDetal = productoProDetService.listarPorID(idPresentacion);
	    }
		
		List<PedidoProducto> carrito = (List<PedidoProducto>) session.getAttribute("carrito");

		PedidoProducto pedidoProducto = new PedidoProducto();
		pedidoProducto.setProducto(producto);
		pedidoProducto.setCantidad(cantidad);
		if (productoProDetal != null) {
		    pedidoProducto.setProductoProDetal(productoProDetal);
		}
		carrito.add(pedidoProducto);
		session.setAttribute("carrito", carrito);
		
		double precioProducto;

	    if (productoProDetal != null && productoProDetal.getPrecio() != null) {
	        precioProducto = productoProDetal.getPrecio();
	    } else {
	    	Map<Integer, Double> precios = (Map<Integer, Double>) session.getAttribute("precios");
	        if (precios != null && precios.containsKey(id)) {
	            precioProducto = precios.get(id);
	            double subtotal = precioProducto * cantidad;
	    		pedidoProducto.setSub_total(subtotal);

	    		double totalCart = 0.0;
	    		for (PedidoProducto pedidoProd : carrito) {
	    			totalCart += pedidoProd.getSub_total();
	    		}

	    		session.setAttribute("totalCarrito", Math.round(totalCart * 100.00) / 100.00);
	    		System.out.println("Recuperando precio del producto con ID: " + id);

	   	     System.out.println("Precio del producto: " + precioProducto);
	        }
	    }

		String referer = request.getHeader("referer");
		return "redirect:" + referer;
	}

	// MÉTODO PARA AGREGAR PRODUCTOS AL CARRITO
	@SuppressWarnings("unchecked")
	@GetMapping("/productoAgregar")
	public String agregarProductoGet(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "id", required = false) int id,
			@RequestParam(name = "cantidad", required = false, defaultValue = "1") int cantidad,
			RedirectAttributes ra) {

		Producto producto = productoService.listarProductoPorID(id);

		List<PedidoProducto> carrito = (List<PedidoProducto>) session.getAttribute("carrito");

		PedidoProducto pedidoProducto = new PedidoProducto();
		pedidoProducto.setProducto(producto);
		pedidoProducto.setCantidad(cantidad);
		carrito.add(pedidoProducto);
		session.setAttribute("carrito", carrito);
		
	    Map<Integer, Double> precios = (Map<Integer, Double>) session.getAttribute("precios");
	    if (precios != null && precios.containsKey(id)) {
	        double precioProducto = precios.get(id);
	        double subtotal = precioProducto * cantidad;
	        pedidoProducto.setSub_total(subtotal);

	        double totalCart = 0.0;
	        for (PedidoProducto pedidoProd : carrito) {
	            totalCart += pedidoProd.getSub_total();
	            
	            
	        }
	        session.setAttribute("totalCarrito", Math.round(totalCart * 100.00) / 100.00);
	    }
	    
	    

		String referer = request.getHeader("referer");
		return "redirect:" + referer;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/carrito/eliminarProducto/{id}")
	public String eliminarProducto(HttpServletRequest request, HttpSession session, @PathVariable int id) {

		List<PedidoProducto> carrito = (List<PedidoProducto>) session.getAttribute("carrito");
		List<PedidoProducto> nuevoCarrito = new ArrayList<PedidoProducto>();

		double nuevoTotal = 0.0;
		for (PedidoProducto pedidoPro : carrito) {
			if (pedidoPro.getProducto().getId() != id) {
				nuevoCarrito.add(pedidoPro);
				nuevoTotal += pedidoPro.getSub_total();
			}
		}

		carrito = nuevoCarrito;
		session.setAttribute("totalCarrito", nuevoTotal);
		session.setAttribute("carrito", carrito);

		String referer = request.getHeader("referer");
		return "redirect:" + referer;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/carrito/actualizarCantidad")
	public String actualizarCantidad(HttpServletRequest request, HttpSession session, @RequestParam(name = "id") int id,
	        @RequestParam(name = "cantidad") int cantidad, RedirectAttributes ra) {

	    List<PedidoProducto> carrito = (List<PedidoProducto>) session.getAttribute("carrito");

	    for (PedidoProducto pedidoProducto : carrito) {
	        if (pedidoProducto.getProducto().getId() == id) {
	            pedidoProducto.setCantidad(cantidad);

	            Map<Integer, Double> precios = (Map<Integer, Double>) session.getAttribute("precios");
	            if (precios != null && precios.containsKey(id)) {
	                double precioProducto = precios.get(id);
	                double subtotal = precioProducto * cantidad;
	                pedidoProducto.setSub_total(subtotal);
	            }
	        }
	    }

	    double totalCart = 0.0;
	    for (PedidoProducto pedidoProd : carrito) {
	        totalCart += pedidoProd.getSub_total();
	    }
	    session.setAttribute("totalCarrito", Math.round(totalCart * 100.00) / 100.00);

	    String referer = request.getHeader("referer");
	    return "redirect:" + referer;
	}
	
	//CUPONES
	@SuppressWarnings("unchecked")
	@ModelAttribute("cupon")
	public String comprobar() {
		String prueba = "unaPrueba";
		return prueba;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/cupon")
	public String aplicarCupon(HttpServletRequest request, HttpSession session, @RequestParam(name = "imputCupon") String cupon) {
		
		Cupon cuponResponse = cuponService.buscarCuponNombre(cupon);
		List<PedidoProducto> carrito = (List<PedidoProducto>) session.getAttribute("carrito");
		double descuento = 0.0;
		double totalCart = 0.0;
		
		for (PedidoProducto pedidoProd : carrito) {
			totalCart += pedidoProd.getSub_total();
		}
		
		
		if(cuponResponse == null) {
			Cupon cuponvacio = new Cupon("El cupon no existe", "0");
			session.setAttribute("cupon", cuponvacio);
			session.setAttribute("descuento", descuento);
			session.setAttribute("totalCarrito", Math.round(totalCart * 100.00) / 100.00);
		}else {
			double porcentaje = Double.parseDouble(cuponResponse.getValor());
			descuento = Math.round((porcentaje/100)* totalCart);
			totalCart=totalCart-descuento;
			session.setAttribute("cupon", cuponResponse);
			session.setAttribute("descuento", descuento);
			session.setAttribute("totalCarrito", totalCart);
		}
		
		
		String referer = request.getHeader("referer");
		return "redirect:" + referer;
	}
	
	
	//fin cupones

	@GetMapping("/precioPresentacion")
	@ResponseBody
	public double actualizarPrecioPorPresentacion(@RequestParam("idPresentacion") int idPresentacion) {
	    List<ProductoProDetal> productos = productoProDetService.obtenerProductosPorIdPresentacion(idPresentacion);
	    if (!productos.isEmpty()) {
	        return productos.get(0).getPrecio();
	    } else {
	        return 0.0;
	    }
	}
	
	// Buscar un producto
	@GetMapping("/productobuscar")
	public String buscar(Model model) {
		return "anonimo/buscar";
	}
	
	@PostMapping("/productoBuscar")
	public String buscarProducto(Model model, HttpSession session,
			@RequestParam(name = "buscar", required = false) String buscarProducto) {

		List<Producto> prodBuscar = new ArrayList<Producto>();

		prodBuscar = productoService.buscarProducto(buscarProducto);
		session.setAttribute("prodBuscados", prodBuscar);
		model.addAttribute("productos", prodBuscar);

		if (buscarProducto != null && prodBuscar.isEmpty()) {
			model.addAttribute("mensaje", "No se encontraron productos con el nombre ingresado.");
		} else {
			model.addAttribute("mensaje", null);
		}

		return "anonimo/buscar";
	}
}
