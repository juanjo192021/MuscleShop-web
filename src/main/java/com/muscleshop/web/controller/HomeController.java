package com.muscleshop.web.controller;

import java.util.*;

import com.muscleshop.web.models.*;
import com.muscleshop.web.models.dto.*;
import com.muscleshop.web.services.*;
import com.muscleshop.web.services.implementation.ProductoPropiedadDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/index")
public class HomeController {

	@Autowired
	ProductoService productoService;

	@Autowired
	IBannerService bannerService;

	@Autowired
	IPopupService popupService;

	@Autowired
	MenuService menuService;

	@Autowired
	IArticuloService articuloService;

	@Autowired
	IProductoPropiedadesDetallesService productoPropiedadesDetallesService;

	@Autowired
	ArticuloComService artiComService;

	@Autowired
	EstadoService estadoService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	MenuSubService menuSubService;

	@Autowired
	ProductoCategoriaService proCateService;

	@Autowired
	ProDetalService proDetalService;

	@Autowired
	PedidoProductoComentarioService pedProComService;

	@Autowired
	FooterService footerService;

	@Autowired
	RedesSocialesService redesSocialesService;

	@Autowired
	HeaderService headerService;

	@Autowired
	IProductoPropiedadesDetallesService iProductoPropiedadesDetallesService;

	@Autowired
	LogosService logosService;

	@Autowired
	public IProductoAgrupadoService iProductoAgrupadoService;

	@Autowired
	CuponService cuponService;

	@Autowired
	IMarketplaceService iMarketplaceService;

	@Autowired
	private ProductoPropiedadDetalleService productoPropiedadDetalleService;

	@Autowired
	private ProductoCategoriaService productoCategoriaService;

	//Modelo para el logo de la tienda
	@ModelAttribute("logosTienda")
	public List<Logos> logos() {
		return logosService.listarLogos();
	}

	@ModelAttribute("header")
	public Map<Integer, Header> headers() {
		List<Header> headers = headerService.listarHeader();

		/*Se usa el map para poder usar la lista con sus indices[0,1,2,3]*/
		Map<Integer, Header> headerMap = new HashMap<>();

		for (Header header : headers) {
			headerMap.put(header.getId(), header);
		}
		return headerMap;
	}

	//Modelos para el menu de la barra Header Bottom
	@ModelAttribute("menusHeader")
	public List<Menu> menus() {
		return menuService.listarMenu();
	}

/*	@ModelAttribute("productos")
	public List<Producto> productos() {
		return productoService.listarProducto();
	}*/

	@ModelAttribute("footer")
	public List<Footer> footers() {
		return footerService.listarFooter();
	}

	@ModelAttribute("redesSociales")
	public List<RedesSociales> redesSociales() {
		return redesSocialesService.listarRedes();
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

    }*/

	@Autowired
	IPropiedadesService iPropiedadesService;

	@GetMapping("/prueba")
	@ResponseBody
	public ResponseEntity<ProductoCarritoDto> resp() {
		ProductoCarritoDto producto = new ProductoCarritoDto();
		return ResponseEntity.ok(producto);
	}

/*	@GetMapping("/prueba2")
	@ResponseBody
	public ResponseEntity<ProductoPropiedadesDetalles> respta() {
		ProductoPropiedadesDetalles a = productoPropiedadDetalleService.obtenerProductoPropiedadDetallePorVariaciones(9, 12, 8);
		return ResponseEntity.ok(a);
	}
*/
	@GetMapping("/prueba3")
	@ResponseBody
	public ResponseEntity<List<ProductoItemsDto>> respues() {
		List<ProductoItemsDto> productoPropiedadesDetalles= productoPropiedadesDetallesService.obtenerProductoPropiedadesDetallesPorForma(2);

		return ResponseEntity.ok(productoPropiedadesDetalles);
	}

	// MENÚS
	@SuppressWarnings("unchecked")
	@GetMapping("/inicio")
	public String Inicio(Model model, HttpSession session) {

		/*List<Producto> productos = productoService.listarProducto();*/

		List<Banner> banners = bannerService.obtenerBanners();

		List<Banner> bannerMovilTablet = banners.stream()
				.filter(ban -> "movil_tablet".equals(ban.getTipoDispositivo()))
				.toList();

		List<Banner> bannerLaptopPc = banners.stream()
				.filter(ban -> "laptop_pc".equals(ban.getTipoDispositivo()))
				.toList();

		List<Popup> popups = popupService.obtenerPopups();


		/*{1,Inicio,inicio},
		{2,Categorias,categorias},
		{3,Objetivo,objetivo},
		{4,Por Marca,marcas},
		{5,Lifestyle,lifestyle},
		{6,Blog,blog},
		{7,Contactanos,contactanos}	*/

		// Si es 2 se mostrara otro contenido

		Integer menuId = 5;
		List<MenuSub> subMenus = menuSubService.obtenerMenuSubsPorMenuId(menuId);

		/*[{1,normal},{2,bestsellers},{3,ofertas}]*/
		List<ProductoItemsDto> productoPropiedadesDetalles = productoPropiedadesDetallesService.obtenerProductoPropiedadesDetallesPorForma(2);

		Integer cantidadArticulos = 8;
		List<Articulo> articulosBlog = articuloService.obtenerArticulosPorCantidad(cantidadArticulos);

		List<PedidoProductoComentario> comentariosProductos = pedProComService.comentariosMostrables(1);

		List<Marketplace> marketplaces = iMarketplaceService.listarMarketplaces();


		/*model.addAttribute("productos", productos);*/
		model.addAttribute("popup", popups);
		model.addAttribute("bannerMovilTablet", bannerMovilTablet);
		model.addAttribute("bannerLaptopPc", bannerLaptopPc);
		model.addAttribute("articulosBlog", articulosBlog);
		model.addAttribute("subMenu", subMenus);
		model.addAttribute("productosPorForma", productoPropiedadesDetalles);
		model.addAttribute("comentariosProductos", comentariosProductos);
		model.addAttribute("marketplaces", marketplaces);

		// LÓGICA CARRITO

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

	@GetMapping("/{menuUrl}")
	public String menuProducto(Model model, @PathVariable String menuUrl, HttpSession session) {

		Menu menu = menuService.obtenerMenuPorUrl(menuUrl);
		MenuDto menuDto = new MenuDto();
		menuDto.setId(menu.getId());
		menuDto.setNombre(menu.getNombre());
		menuDto.setImagen(menu.getImagen());
		menuDto.setUrl(menu.getUrl());
		menuDto.setEstado(menu.getEstado().getNombre());

		if(menu.getId()==7) {
			List<Articulo> arti = articuloService.obtenerArticulos();
			model.addAttribute("articulo", arti);
			return "porBlog/menuBlog";
		}
		else {
			List<MenuSub> menuSubs = menuSubService.obtenerMenuSubsPorMenuId(menu.getId());
			List<MenuSubDto> menuSubsDto = menuSubs.stream()
					.map(menuSub -> {
						MenuSubDto dto = new MenuSubDto();
						dto.setId(menuSub.getId());
						dto.setNombre(menuSub.getNombre());
						dto.setUrl(menuSub.getUrl());
						dto.setImagen(menuSub.getImagen());
						dto.setBanner(menuSub.getBanner());
						dto.setNombreMenu(menuSub.getMenu().getNombre());
						dto.setUrlMenu(menuSub.getMenu().getUrl());
						dto.setEstado(menuSub.getEstado().getNombre());
						return dto;
					})
					.toList();

			model.addAttribute("menuDto", menuDto);
			model.addAttribute("menuSubsDto", menuSubsDto);
			return "porProductos/menuProducto";
		}
	}

	@GetMapping("/{menuUrl}/{menuSubUrl}")
	public String productoCategoria(Model model,
									@PathVariable String menuUrl,
									@PathVariable String menuSubUrl,
									@RequestParam(required = false) Double minPrecio,
									@RequestParam(required = false) Double maxPrecio,
									HttpSession session) {

		Menu menu = menuService.obtenerMenuPorUrl(menuUrl);
		MenuSub menuSub = menuSubService.obtenerMenuSubPorUrl(menuSubUrl);

		List<BannerMenuSub> bannerMovilTablet = (menuSub.getBannerMenuSub()).stream()
				.filter(ban -> "movil_tablet".equals(ban.getTipoDispositivo()))
				.toList();

		List<BannerMenuSub> bannerLaptopPc = (menuSub.getBannerMenuSub()).stream()
				.filter(ban -> "laptop_pc".equals(ban.getTipoDispositivo()))
				.toList();

		List<ProductoCategoria> categoriasMenuSub = productoCategoriaService.listarPorMenuSubId(menuSub.getId());
		List<ProductoCategoriaDto> categoriasMenuSubDto = categoriasMenuSub.stream()
				.map(categorias -> {
					ProductoCategoriaDto categoriaDto = new ProductoCategoriaDto();
					categoriaDto.setId(categorias.getId());
					categoriaDto.setNombre(categorias.getNombre());
					categoriaDto.setUrl(categorias.getUrl());
					categoriaDto.setNombreMenuSub(categorias.getMenuSub().getNombre());
					categoriaDto.setUrlMenuSub(categorias.getMenuSub().getUrl());
					categoriaDto.setNombreMenu(categorias.getMenuSub().getMenu().getNombre());
					categoriaDto.setUrlMenu(categorias.getMenuSub().getMenu().getUrl());
					categoriaDto.setEstado(categorias.getEstado().getNombre());
					return categoriaDto;
				})
				.toList();

		List<MenuSub> otrasCategorias = menuSubService.obtenerMenuSubsPorMenuId(menu.getId());
		List<MenuSubDto> otrasCategoriasDto = otrasCategorias.stream()
				.map(oc -> {
					MenuSubDto menuSubDto = new MenuSubDto();
					menuSubDto.setId(oc.getId());
					menuSubDto.setNombre(oc.getNombre());
					menuSubDto.setUrl(oc.getUrl());
					menuSubDto.setImagen(oc.getImagen());
					menuSubDto.setBanner(oc.getBanner());
					menuSubDto.setNombreMenu(oc.getMenu().getNombre());
					menuSubDto.setUrlMenu(oc.getMenu().getUrl());
					menuSubDto.setEstado(oc.getEstado().getNombre());
					return menuSubDto;
				}).toList();

		List<MenuSub> porObjetivos = menuSubService.obtenerMenuSubsPorMenuId(5);
		List<MenuSubDto> porObjetivosDto = porObjetivos.stream()
				.map(po -> {
					MenuSubDto menuSubDto = new MenuSubDto();
					menuSubDto.setId(po.getId());
					menuSubDto.setNombre(po.getNombre());
					menuSubDto.setUrl(po.getUrl());
					menuSubDto.setImagen(po.getImagen());
					menuSubDto.setBanner(po.getBanner());
					menuSubDto.setNombreMenu(po.getMenu().getNombre());
					menuSubDto.setUrlMenu(po.getMenu().getUrl());
					menuSubDto.setEstado(po.getEstado().getNombre());
					return menuSubDto;
				}).toList();

		List<MenuSub> porMarcas = menuSubService.obtenerMenuSubsPorMenuId(6);
		List<MenuSubDto> porMarcasDto = porMarcas.stream()
				.map(pm -> {
					MenuSubDto menuSubDto = new MenuSubDto();
					menuSubDto.setId(pm.getId());
					menuSubDto.setNombre(pm.getNombre());
					menuSubDto.setUrl(pm.getUrl());
					menuSubDto.setImagen(pm.getImagen());
					menuSubDto.setBanner(pm.getBanner());
					menuSubDto.setNombreMenu(pm.getMenu().getNombre());
					menuSubDto.setUrlMenu(pm.getMenu().getUrl());
					menuSubDto.setEstado(pm.getEstado().getNombre());
					return menuSubDto;
				}).toList();

		List<ProductoItemsDto> productos = iProductoPropiedadesDetallesService.obtenerProductosIndividualesPorMenuSubId(minPrecio, maxPrecio, menuSub.getId());

		model.addAttribute("nombreMenuSub", menuSub.getNombre());
		//*model.addAttribute("bannerMenuSub", menuSub.getBanner());*//*
		model.addAttribute("bannerMenuSubMovilTablet", bannerMovilTablet);
		model.addAttribute("bannerMenuSubLaptopPc", bannerLaptopPc);
		model.addAttribute("categoriasMenuSub", categoriasMenuSubDto);
		model.addAttribute("otrasCategoriasMenuSubs", otrasCategoriasDto);
		model.addAttribute("porObjetivos", porObjetivosDto);
		model.addAttribute("porMarcas", porMarcasDto);
		//*model.addAttribute("nombrePropiedad", nombrePropiedad);*//*
		model.addAttribute("productos", productos);

		return "porProductos/subMenuProductos";
	}

	@GetMapping("/{menuUrl}/{menuSubUrl}/{categoriaUrl}")
	public String listarProCate(Model model,
								HttpSession session,
								@PathVariable String menuUrl,
								@PathVariable String menuSubUrl,
								@PathVariable String categoriaUrl,
								@RequestParam(required = false) Double minPrecio,
								@RequestParam(required = false) Double maxPrecio) {

		Menu menu = menuService.obtenerMenuPorUrl(menuUrl);
		MenuSub menuSub = menuSubService.obtenerMenuSubPorUrl(menuSubUrl);

		List<ProductoCategoria> categoriasMenuSub = productoCategoriaService.listarPorMenuSubId(menuSub.getId());
		List<ProductoCategoriaDto> categoriasMenuSubDto = categoriasMenuSub.stream()
				.map(categorias -> {
					ProductoCategoriaDto categoriaDto = new ProductoCategoriaDto();
					categoriaDto.setId(categorias.getId());
					categoriaDto.setNombre(categorias.getNombre());
					categoriaDto.setUrl(categorias.getUrl());
					categoriaDto.setNombreMenuSub(categorias.getMenuSub().getNombre());
					categoriaDto.setUrlMenuSub(categorias.getMenuSub().getUrl());
					categoriaDto.setNombreMenu(categorias.getMenuSub().getMenu().getNombre());
					categoriaDto.setUrlMenu(categorias.getMenuSub().getMenu().getUrl());
					categoriaDto.setEstado(categorias.getEstado().getNombre());
					return categoriaDto;
				})
				.toList();

		List<MenuSub> otrasCategorias = menuSubService.obtenerMenuSubsPorMenuId(menu.getId());
		List<MenuSubDto> otrasCategoriasDto = otrasCategorias.stream()
				.map(oc -> {
					MenuSubDto menuSubDto = new MenuSubDto();
					menuSubDto.setId(oc.getId());
					menuSubDto.setNombre(oc.getNombre());
					menuSubDto.setUrl(oc.getUrl());
					menuSubDto.setImagen(oc.getImagen());
					menuSubDto.setBanner(oc.getBanner());
					menuSubDto.setNombreMenu(oc.getMenu().getNombre());
					menuSubDto.setUrlMenu(oc.getMenu().getUrl());
					menuSubDto.setEstado(oc.getEstado().getNombre());
					return menuSubDto;
				}).toList();

		List<MenuSub> porObjetivos = menuSubService.obtenerMenuSubsPorMenuId(5);
		List<MenuSubDto> porObjetivosDto = porObjetivos.stream()
				.map(po -> {
					MenuSubDto menuSubDto = new MenuSubDto();
					menuSubDto.setId(po.getId());
					menuSubDto.setNombre(po.getNombre());
					menuSubDto.setUrl(po.getUrl());
					menuSubDto.setImagen(po.getImagen());
					menuSubDto.setBanner(po.getBanner());
					menuSubDto.setNombreMenu(po.getMenu().getNombre());
					menuSubDto.setUrlMenu(po.getMenu().getUrl());
					menuSubDto.setEstado(po.getEstado().getNombre());
					return menuSubDto;
				}).toList();

		List<MenuSub> porMarcas = menuSubService.obtenerMenuSubsPorMenuId(6);
		List<MenuSubDto> porMarcasDto = porMarcas.stream()
				.map(pm -> {
					MenuSubDto menuSubDto = new MenuSubDto();
					menuSubDto.setId(pm.getId());
					menuSubDto.setNombre(pm.getNombre());
					menuSubDto.setUrl(pm.getUrl());
					menuSubDto.setImagen(pm.getImagen());
					menuSubDto.setBanner(pm.getBanner());
					menuSubDto.setNombreMenu(pm.getMenu().getNombre());
					menuSubDto.setUrlMenu(pm.getMenu().getUrl());
					menuSubDto.setEstado(pm.getEstado().getNombre());
					return menuSubDto;
				}).toList();

		ProductoCategoria productoCategoria = productoCategoriaService.obtenerProductoCategoriaPorUrl(categoriaUrl);

		List<ProductoItemsDto> productos = iProductoPropiedadesDetallesService.obtenerProductosIndividualesPorCategoriaId(minPrecio, maxPrecio, productoCategoria.getId());

		model.addAttribute("nombreMenuSub", menuSub.getNombre());
		model.addAttribute("bannerMenuSub", menuSub.getBanner());
		model.addAttribute("categoriasMenuSub", categoriasMenuSubDto);
		model.addAttribute("otrasCategoriasMenuSubs", otrasCategoriasDto);
		model.addAttribute("porObjetivos", porObjetivosDto);
		model.addAttribute("porMarcas", porMarcasDto);
		model.addAttribute("productos", productos);

		return "porProductos/categoriaPro";
	}

	@GetMapping("/{menuUrl}/{menuSubUrl}/{categoriaUrl}/{productoUrl}")
	public String verProducto(@PathVariable String menuUrl,
							  @PathVariable String menuSubUrl,
							  @PathVariable String categoriaUrl,
							  @PathVariable String productoUrl,
							  @RequestParam(required = false) String detalleNombre,
							  @RequestParam(required = false) String detalleModificado,
							  Model model,
							  HttpSession session) {


		Producto producto = productoService.obtenerProductoPorUrl(productoUrl);

		ProductoPropiedadesDetalles productoPropiedadesDetalles = productoPropiedadDetalleService.obtenerProductoPropiedadDetallesPorVariaciones(producto.getId(),detalleNombre,detalleModificado);

		List<PropiedadDetalleImagen> propiedadesDetallesImagenes = new ArrayList<>();
		if (productoPropiedadesDetalles.getPropiedadesDetallesImagenes().isEmpty()){
			PropiedadDetalleImagen propiedadDetalleImagen = new PropiedadDetalleImagen();
			propiedadDetalleImagen.setId(productoPropiedadesDetalles.getId());
			propiedadDetalleImagen.setNombre(productoPropiedadesDetalles.getImagen());
			propiedadesDetallesImagenes.add(propiedadDetalleImagen);
		}else {
			for (PropiedadDetalleImagen pdi : productoPropiedadesDetalles.getPropiedadesDetallesImagenes()) {

				PropiedadDetalleImagen propiedadDetalleImagen = new PropiedadDetalleImagen();
				propiedadDetalleImagen.setId(pdi.getId());
				propiedadDetalleImagen.setNombre(pdi.getNombre());
				propiedadesDetallesImagenes.add(propiedadDetalleImagen);

			}
		}


		// Inicializar listas para cada propiedad
		List<ProductoVariacion> listaDetallesPresentacion = new ArrayList<>();
		List<ProductoVariacion> listaDetallesColor = new ArrayList<>();
		List<ProductoVariacion> listaDetallesTamanio = new ArrayList<>();
		List<ProductoVariacion> listaDetallesSabor = new ArrayList<>();

		for (ProductoVariacion productoVariacion : producto.getProductoVariaciones()) {

			switch (productoVariacion.getPropiedades().getId()){
				case 1: listaDetallesPresentacion.add(productoVariacion);break;
				case 2: listaDetallesColor.add(productoVariacion);break;
				case 3: listaDetallesTamanio.add(productoVariacion);break;
				case 4: listaDetallesSabor.add(productoVariacion);break;
				default: listaDetallesPresentacion.add(new ProductoVariacion());break;
			}
		}



		model.addAttribute("producto", producto);
		model.addAttribute("productoPropiedadesDetallesId", productoPropiedadesDetalles.getId());
		model.addAttribute("precio", productoPropiedadesDetalles.getPrecio());
		model.addAttribute("precioReducido", productoPropiedadesDetalles.getPrecioReducido());

		model.addAttribute("propiedadesDetallesImagenes", propiedadesDetallesImagenes);

		model.addAttribute("detalleNombre", detalleNombre);
		model.addAttribute("detalleModificado", detalleModificado);

		model.addAttribute("agrupacionDetallesPresentacion", listaDetallesPresentacion);
		model.addAttribute("agrupacionDetallesColor", listaDetallesColor);
		model.addAttribute("agrupacionDetallesTamanio", listaDetallesTamanio);
		model.addAttribute("agrupacionDetallesSabor", listaDetallesSabor);

		return "porProductos/detalleProducto";
	}

	@GetMapping("/buscarPropiedadDetalleImagen")
	@ResponseBody
	public ResponseEntity<ProductoPropiedadesDetalles> buscarPropiedadDetalleImagen(Integer productoId,
                                                                                    String presentacion,
                                                                                    String sabor) {
		ProductoPropiedadesDetalles a = productoPropiedadDetalleService.obtenerProductoPropiedadDetallesPorVariaciones(productoId,
				presentacion,
				sabor);
		if (a == null) {
			return ResponseEntity.ofNullable(a);
		}
		return ResponseEntity.ok(a);
	}

	/*
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

		/*List<Producto> productos = productoService.listarPorMenuSub(menuSub);
		model.addAttribute("productos", productos);//

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

		List<ProductoPropiedadDetalle> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1);
		List<ProductoPropiedadDetalle> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3);
		List<ProductoPropiedadDetalle> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoPropiedadDetalle> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);

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

		/*List<Producto> productos = productoService.listarPorMenuSub(menuSub);
		model.addAttribute("productos", productos);//


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

		List<ProductoPropiedadDetalle> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1);
		List<ProductoPropiedadDetalle> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3);
		List<ProductoPropiedadDetalle> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoPropiedadDetalle> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);

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

		List<ProductoPropiedadDetalle> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1);
		List<ProductoPropiedadDetalle> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3);
		List<ProductoPropiedadDetalle> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoPropiedadDetalle> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);

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
*/
	// MENÚ BLOG
	@GetMapping("/blog")
	public String blog(HttpSession session, Model model) {
		List<Articulo> arti = articuloService.obtenerArticulos();
		model.addAttribute("articulo", arti);
		return "porBlog/menuBlog";
	}

	@GetMapping("blog/ver-blog/{id}")
	public String verBlog(@PathVariable("id") int id, Model model, HttpSession session) {
		Articulo artiVer = articuloService.obtenerArticuloPorID(id);
		session.setAttribute("articulo", artiVer);

		List<Articulo> arti = articuloService.obtenerArticulos();
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
		Articulo articulo = articuloService.obtenerArticuloPorID(id);

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
		List<Producto> productos = productoService.listarProducto();
		/*List<Producto> productosAgrupados = productos.stream()
				.filter(ban -> ban.getAgrupacion().getId() == 2 && ban.getEstado().getId() == 1)//
				.filter(ban -> ban.getAgrupacion().getId() == 2 && ban.getEstado().getId() == 1)
				.toList();*/
		/*List<Producto> proForma = productoService.listarProForma(2);
		model.addAttribute("proForma", proForma);*/
		model.addAttribute("productosAgrupados", productos);
		return "ofertas/menuOfertas";
	}

	/*
	@GetMapping("/ofertas/{id}")
	public String verOfertaID(@PathVariable("id") int id, Model model, HttpSession session) {
		// Producto por su id

		List<ProductoAgrupado> productos = iProductoAgrupadoService.listarProductosAgrupadosPorIdPadre(id);

		Producto proDetalles = productoService.listarProductoPorID(id);

		List<ProductoPropiedadDetalle> detallePresentacion = productoProDetService.obtenerPorTipoDePropiedad(id, 1);
		List<ProductoPropiedadDetalle> detalleColor = productoProDetService.obtenerPorTipoDePropiedad(id, 2);
		List<ProductoPropiedadDetalle> detalleTam = productoProDetService.obtenerPorTipoDePropiedad(id, 3);
		List<ProductoPropiedadDetalle> detalleSabor = productoProDetService.obtenerPorTipoDePropiedad(id, 4);

		model.addAttribute("productos", productos);
		model.addAttribute("producto", proDetalles);
        model.addAttribute("detallePresentacion", detallePresentacion);
        model.addAttribute("detalleTam", detalleTam);
        model.addAttribute("detalleColor", detalleColor);
        model.addAttribute("detalleSabor", detalleSabor);

		return "ofertas/detallesOferta";
	}
*/

	// MÉTODO PARA AGREGAR PRODUCTOS AL CARRITO
	@SuppressWarnings("unchecked")
	@GetMapping("/productoAgregar")
	public String agregarProductoGet(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "id", required = false) int id,
			@RequestParam(name = "cantidad", required = false, defaultValue = "1") int cantidad,
			RedirectAttributes ra) {

		ProductoCarritoDto productoCarritoDto = productoPropiedadDetalleService.obtenerProductoPropiedadDetallePorId(id);

		List<PedidoProductoDto> carrito = (List<PedidoProductoDto>) session.getAttribute("carrito");

		PedidoProductoDto pedidoProducto = new PedidoProductoDto();
		/*pedidoProducto.setProductoId(productoCarritoDto.getProductoId());
		pedidoProducto.setProductoPropiedadesDetallesId(productoCarritoDto.getProductoPropiedadDetalleId());*/
		pedidoProducto.setProductoCarrito(productoCarritoDto);
		pedidoProducto.setCantidad(cantidad);
		carrito.add(pedidoProducto);
		session.setAttribute("carrito", carrito);

		double precioUnitario = productoCarritoDto.getPrecioReducido();
		double subTotales = precioUnitario * cantidad;
		pedidoProducto.setSub_total(subTotales);
		double totalCart = 0.0;
		for (PedidoProductoDto pedidoProd : carrito) {
			totalCart += pedidoProd.getSub_total();
		}
		session.setAttribute("totalCarrito", String.format("%.2f", totalCart));
		String referer = request.getHeader("referer");
		return "redirect:" + referer;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/carrito/eliminarProducto/{id}")
	public String eliminarProducto(HttpServletRequest request, HttpSession session, @PathVariable int id) {

		List<PedidoProductoDto> carrito = (List<PedidoProductoDto>) session.getAttribute("carrito");
		List<PedidoProductoDto> nuevoCarrito = new ArrayList<PedidoProductoDto>();

		double nuevoTotal = 0.0;
		for (PedidoProductoDto pedidoPro : carrito) {
			if (pedidoPro.getProductoCarrito().getProductoPropiedadDetalleId() != id) {
				nuevoCarrito.add(pedidoPro);
				nuevoTotal += pedidoPro.getSub_total();
			}
		}

		carrito = nuevoCarrito;
		session.setAttribute("totalCarrito", String.format("%.2f",nuevoTotal));
		session.setAttribute("carrito", carrito);

		String referer = request.getHeader("referer");
		return "redirect:" + referer;
	}

	// CARRITO DE COMPRAS
	@GetMapping("/carrito")
	public String carrito(Model model, HttpSession session) {

		/*List<Producto> proCate = productoService.listarProducto();

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
		}*/

		/*model.addAttribute("precios", precios);
		model.addAttribute("preciosTachados", preciosTachados);
		model.addAttribute("porcentajes", porcentajes);*/


		return "anonimo/carrito";
	}


	@SuppressWarnings("unchecked")
	@PostMapping("/carrito/actualizarCantidad")
	public String actualizarCantidad(HttpServletRequest request, HttpSession session, @RequestParam(name = "id") int id,
	        @RequestParam(name = "cantidad") int cantidad, RedirectAttributes ra) {

	    List<PedidoProductoDto> carrito = (List<PedidoProductoDto>) session.getAttribute("carrito");

	    for (PedidoProductoDto pedidoProducto : carrito) {
	        if (pedidoProducto.getProductoCarrito().getProductoPropiedadDetalleId() == id) {
	            pedidoProducto.setCantidad(cantidad);
				double precioUnitario = pedidoProducto.getProductoCarrito().getPrecioReducido();
				double subtotal = precioUnitario * cantidad;
				pedidoProducto.setSub_total(subtotal);
	        }
	    }

		double totalCart = 0.0;
		for (PedidoProductoDto pedidoProd : carrito) {
			totalCart += pedidoProd.getSub_total();
		}
	    session.setAttribute("totalCarrito", String.format("%.2f",totalCart));

	    String referer = request.getHeader("referer");
	    return "redirect:" + referer;
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
