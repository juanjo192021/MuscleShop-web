package com.muscleshop.web.services;

import java.util.ArrayList;
import java.util.List;

import com.muscleshop.web.models.*;
import com.muscleshop.web.models.dto.ProductoCarritoDto;
import com.muscleshop.web.models.dto.ProductoItemsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoDao;

@Service
public class ProductoService {

	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	UsuarioService usuarioService;

	public List<Producto> listarProducto() {
		return productoDao.findAll();
	}

	public Producto listarProductoPorID(Integer id) {
		return productoDao.findById(id).orElse(null);
	}

	public Producto obtenerProductoPorUrl(String productoUrl){
		return productoDao.findByUrl(productoUrl);
	}

/*	public List<Producto> listarProCate(String categoriaUrl) {
		return productoDao.findByCategoriaUrl(categoriaUrl);
	}
	
	public List<Producto> listarPorCategoria(ProductoCategoria categoria) {
        return productoDao.findByCategoria(categoria);
    }*/
	
/*
    public List<Producto> listarPorMenuSub(MenuSub menuSub) {
        return productoDao.findByMenuSub(menuSub);
    }
*/

/*    public List<Producto> listarProForma(int productoFormaId){
    	return productoDao.findByProductoFormaId(productoFormaId);
    }
    */
    public List<Producto> buscarProducto(String nombre) {
		return productoDao.buscarProducto(nombre);
	}


/*	public List<Producto> obtenerResultado(Integer productoFormaId,Integer estadoId){
		return productoDao.listarProductosPorForma(productoFormaId, estadoId);
	}*/

	public ProductoCarritoDto obtenerProductoPorProductoPropiedadId(Integer id) {
		Producto producto = productoDao.findByProductoPropiedadesDetalles_Id(id);
		ProductoCarritoDto productoCarritoDto = new ProductoCarritoDto();
		for (ProductoPropiedadesDetalles ppd : producto.getProductoPropiedadesDetalles()) {
			if(ppd.getId() == id){
				/*
				*     private int productoId;
						private int productoPropiedadDetalleId;
						private String nombreProducto;
						private String imagenProducto;
						private String nombrePropiedadDetalle;
						private String nombrePropiedadDetalle2;
						private Integer cantidadProducto;
						private Double precio;
						private Double precioReducido;
				* */
				productoCarritoDto.setProductoId(producto.getId());
				productoCarritoDto.setProductoPropiedadDetalleId(ppd.getId());
				productoCarritoDto.setNombreProducto(producto.getNombre());
				productoCarritoDto.setImagenProducto(ppd.getImagen());
				/*productoCarritoDto.setNombrePropiedadDetalle(ppd.getPropiedadesDetalles().getDetalles().getNombre());
				productoCarritoDto.setNombrePropiedadDetalle2(ppd.getPropiedadesDetalles2().getDetalles().getNombre());*/
				productoCarritoDto.setPrecio(ppd.getPrecio());
				productoCarritoDto.setPrecioReducido(ppd.getPrecioReducido());
			}
		}
		return productoCarritoDto;
	}

/*	public List<ProductoItemsDto> listarProductosIndividualesPorMenuSubId(Double minPrecio, Double maxPrecio, Integer menuSubId) {

		List<Producto> productos = new ArrayList<>();
		if(minPrecio == null && maxPrecio == null){
			productos = productoDao.findByProductoCategoria_MenuSub_Id(menuSubId);
		}
		if(minPrecio != null && maxPrecio != null){
			productos = productoDao.findByPrecioBetweenAndMenuSubId(minPrecio, maxPrecio, menuSubId);
		}
		return listarProductosItemsModificados(productos);
	}

	public List<ProductoItemsDto> listarProductosIndividualesPorCategoriaId (Double minPrecio, Double maxPrecio,Integer categoriaId, Integer propiedadesId){

		List<Producto> productos = new ArrayList<>();
		if(minPrecio == null && maxPrecio == null){
			productos = productoDao.findByProductoCategoria_Id(categoriaId);
		}
		if(minPrecio != null && maxPrecio != null){
			productos = productoDao.findByPrecioBetweenAndCategoriaId(minPrecio, maxPrecio, categoriaId);
		}

		return listarProductosItemsModificados(productos);
	}*/

	public List<ProductoItemsDto> listarProductosItemsModificados(List<Producto> productos){

		List<ProductoItemsDto> productosIndividuales = new ArrayList<>();

		for(Producto producto: productos){
			for(ProductoPropiedadesDetalles ppd : producto.getProductoPropiedadesDetalles()){
				ProductoItemsDto nuevoProducto = new ProductoItemsDto();
				nuevoProducto.setId(producto.getId());
				nuevoProducto.setNombre(producto.getNombre());
				nuevoProducto.setImagen(ppd.getImagen().isEmpty() ? producto.getImagen() : ppd.getImagen());
				nuevoProducto.setNombreCategoria(producto.getProductoCategoria().getNombre());
				nuevoProducto.setUrlCategoria(producto.getProductoCategoria().getUrl());
				nuevoProducto.setNombreMenuSub(producto.getProductoCategoria().getMenuSub().getNombre());
				nuevoProducto.setUrlMenuSub(producto.getProductoCategoria().getMenuSub().getUrl());
				nuevoProducto.setProductoPropiedadDetalleId(ppd.getId());
				nuevoProducto.setPrecio(ppd.getPrecio());
				nuevoProducto.setPrecioReducido(ppd.getPrecioReducido());
				nuevoProducto.setStock(ppd.getStock());
				/*nuevoProducto.setDetalleNombre(ppd.getPropiedadesDetalles().getDetalles().getNombre());
				nuevoProducto.setDetalleModificado(ppd.getPropiedadesDetalles2() == null ? "Sin sabor" : ppd.getPropiedadesDetalles2().getDetalles().getNombre());
				nuevoProducto.setPropiedadNombre(ppd.getPropiedadesDetalles().getPropiedades().getNombre());*/
				for(ProductoMenuSub pms : ppd.getProducto().getProductoMenusSub()){
					if(pms.getMenuSub().getMenu().getId() == 6){
						nuevoProducto.setNombreMarca(pms.getMenuSub().getNombre());
						nuevoProducto.setUrlMarca(pms.getMenuSub().getUrl());
						nuevoProducto.setUrlMenuMarca(pms.getMenuSub().getMenu().getUrl());
					}
				}
				productosIndividuales.add(nuevoProducto);
			}
		}
		/*for (Producto producto : productos) {
			String nombreProducto = producto.getNombre();
			String imagenProducto = producto.getImagen();

			// Mapas para agrupar las presentaciones por sabor y color
			Map<String, List<ProductoPropiedadDetalle>> presentacionesPorSabor = new LinkedHashMap<>();
			Map<String, List<ProductoPropiedadDetalle>> presentacionesPorColor = new LinkedHashMap<>();
			boolean tieneSabor = false;
			boolean tieneColor = false;

			// Recopilar todas las presentaciones agrupadas por sabor y color
			for (ProductoPropiedadDetalle ppd : producto.getProductoPropiedadesDetalles()) {
				String propiedad = ppd.getPropiedadesDetalles().getPropiedades().getNombre();
				String detalle = ppd.getPropiedadesDetalles().getDetalles().getNombre();

				if ("Sabor".equals(propiedad)) {
					tieneSabor = true;
					String sabor = detalle;
					// Asignar cada presentación a su sabor
					for (ProductoPropiedadDetalle ppdAux : producto.getProductoPropiedadesDetalles()) {
						String propiedadAux = ppdAux.getPropiedadesDetalles().getPropiedades().getNombre();
						if ("Presentación".equals(propiedadAux)) {
							presentacionesPorSabor.computeIfAbsent(sabor, k -> new ArrayList<>()).add(ppdAux);
						}
					}
				}

				if ("Color".equals(propiedad)) {
					tieneColor = true;
					String color = detalle;
					// Asignar cada tamaño a su color
					for (ProductoPropiedadDetalle ppdAux : producto.getProductoPropiedadesDetalles()) {
						String propiedadAux = ppdAux.getPropiedadesDetalles().getPropiedades().getNombre();
						if ("Tamaños".equals(propiedadAux)) {
							presentacionesPorColor.computeIfAbsent(color, k -> new ArrayList<>()).add(ppdAux);
						}
					}
				}
			}

			// Manejar los productos con sabor
			if (tieneSabor) {
				for (Map.Entry<String, List<ProductoPropiedadDetalle>> entry : presentacionesPorSabor.entrySet()) {
					String sabor = entry.getKey();
					List<ProductoPropiedadDetalle> detallesPorSabor = entry.getValue();

					for (ProductoPropiedadDetalle ppd : detallesPorSabor) {
						ProductoItemsDto nuevoProducto = new ProductoItemsDto();
						nuevoProducto.setId(producto.getId());
						nuevoProducto.setNombre(nombreProducto);
						nuevoProducto.setImagen(ppd.getPropiedadesDetallesImagenes().isEmpty() ? imagenProducto : ppd.getPropiedadesDetallesImagenes().get(0).getNombre());
						nuevoProducto.setNombreCategoria(producto.getProductoCategoria().getNombre());
						nuevoProducto.setUrlCategoria(producto.getProductoCategoria().getUrl());
						nuevoProducto.setNombreMenuSub(producto.getProductoCategoria().getMenuSub().getNombre());
						nuevoProducto.setUrlMenuSub(producto.getProductoCategoria().getMenuSub().getUrl());
						nuevoProducto.setPrecio(ppd.getPrecio());
						nuevoProducto.setStock(ppd.getStock());
						nuevoProducto.setDetalleNombre(ppd.getPropiedadesDetalles().getDetalles().getNombre());
						nuevoProducto.setDetalleModificado(sabor);
						nuevoProducto.setPropiedadNombre(ppd.getPropiedadesDetalles().getPropiedades().getNombre());
						for(ProductoMenuSub pms : ppd.getProducto().getProductoMenusSub()){
							if(pms.getMenuSub().getMenu().getId() == 6){
								nuevoProducto.setNombreMarca(pms.getMenuSub().getNombre());
								nuevoProducto.setUrlMarca(pms.getMenuSub().getUrl());
								nuevoProducto.setUrlMenuMarca(pms.getMenuSub().getMenu().getUrl());
							}
						}

						productosIndividuales.add(nuevoProducto);
					}
				}
			} else if (tieneColor) {
				// Manejar los productos con color
				for (Map.Entry<String, List<ProductoPropiedadDetalle>> entry : presentacionesPorColor.entrySet()) {
					String color = entry.getKey();
					List<ProductoPropiedadDetalle> detallesPorColor = entry.getValue();

					for (ProductoPropiedadDetalle ppd : detallesPorColor) {
						ProductoItemsDto nuevoProducto = new ProductoItemsDto();
						nuevoProducto.setId(producto.getId());
						nuevoProducto.setNombre(nombreProducto);
						nuevoProducto.setImagen(ppd.getPropiedadesDetallesImagenes().isEmpty() ? imagenProducto : ppd.getPropiedadesDetallesImagenes().get(0).getNombre());
						nuevoProducto.setNombreCategoria(producto.getProductoCategoria().getNombre());
						nuevoProducto.setUrlCategoria(producto.getProductoCategoria().getUrl());
						nuevoProducto.setNombreMenuSub(producto.getProductoCategoria().getMenuSub().getNombre());
						nuevoProducto.setUrlMenuSub(producto.getProductoCategoria().getMenuSub().getUrl());
						nuevoProducto.setPrecio(ppd.getPrecio());
						nuevoProducto.setStock(ppd.getStock());
						nuevoProducto.setDetalleNombre(ppd.getPropiedadesDetalles().getDetalles().getNombre());
						nuevoProducto.setDetalleModificado(color);
						nuevoProducto.setPropiedadNombre(ppd.getPropiedadesDetalles().getPropiedades().getNombre());
						for(ProductoMenuSub pms : ppd.getProducto().getProductoMenusSub()){
							if(pms.getMenuSub().getMenu().getId() == 6){
								nuevoProducto.setNombreMarca(pms.getMenuSub().getNombre());
								nuevoProducto.setUrlMarca(pms.getMenuSub().getUrl());
								nuevoProducto.setUrlMenuMarca(pms.getMenuSub().getMenu().getUrl());
							}
						}
						productosIndividuales.add(nuevoProducto);
					}
				}
			} else {
				// Manejar el caso cuando no hay ni sabor ni color
				for (ProductoPropiedadDetalle ppd : producto.getProductoPropiedadesDetalles()) {
					if ("Presentación".equals(ppd.getPropiedadesDetalles().getPropiedades().getNombre()) ||
							"Tamaños".equals(ppd.getPropiedadesDetalles().getPropiedades().getNombre())) {
						ProductoItemsDto nuevoProducto = new ProductoItemsDto();
						nuevoProducto.setId(producto.getId());
						nuevoProducto.setNombre(nombreProducto);
						nuevoProducto.setImagen(ppd.getPropiedadesDetallesImagenes().isEmpty() ? imagenProducto : ppd.getPropiedadesDetallesImagenes().get(0).getNombre());
						nuevoProducto.setNombreCategoria(producto.getProductoCategoria().getNombre());
						nuevoProducto.setUrlCategoria(producto.getProductoCategoria().getUrl());
						nuevoProducto.setNombreMenuSub(producto.getProductoCategoria().getMenuSub().getNombre());
						nuevoProducto.setUrlMenuSub(producto.getProductoCategoria().getMenuSub().getUrl());
						nuevoProducto.setPrecio(ppd.getPrecio());
						nuevoProducto.setStock(ppd.getStock());
						nuevoProducto.setDetalleNombre(ppd.getPropiedadesDetalles().getDetalles().getNombre());
						nuevoProducto.setDetalleModificado("");
						nuevoProducto.setPropiedadNombre(ppd.getPropiedadesDetalles().getPropiedades().getNombre());
						for(ProductoMenuSub pms : ppd.getProducto().getProductoMenusSub()){
							if(pms.getMenuSub().getMenu().getId() == 6){
								nuevoProducto.setNombreMarca(pms.getMenuSub().getNombre());
								nuevoProducto.setUrlMarca(pms.getMenuSub().getUrl());
								nuevoProducto.setUrlMenuMarca(pms.getMenuSub().getMenu().getUrl());
							}
						}
						productosIndividuales.add(nuevoProducto);
					}
				}
			}
		}*/
		return productosIndividuales;
	}

}
