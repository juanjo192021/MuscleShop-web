package com.muscleshop.web.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.muscleshop.web.models.Producto;
import com.muscleshop.web.models.ProductoMenuSub;
import com.muscleshop.web.models.ProductoPropiedadDetalle;
import com.muscleshop.web.models.dto.ProductoItemsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoPropiedadDetalleDao;

@Service
public class ProductoPropiedadDetalleService {
	
	@Autowired
	private IProductoPropiedadDetalleDao productoProDetDao;
	
	public List<ProductoPropiedadDetalle> listarProDetPro() {
		return productoProDetDao.findAll();
	}

	public ProductoPropiedadDetalle listarPorID(Integer id) {
		return productoProDetDao.findById(id).orElse(null);
	}
	
	public List<ProductoPropiedadDetalle> obtenerProductosPorIdPresentacion(int idPresentacion) {
	    return productoProDetDao.findByPropiedadesDetallesId(idPresentacion);
	}
	
	public List<ProductoPropiedadDetalle> obtenerPorTipoDePropiedad(int productoId, int tipoPropiedadId) {
        return productoProDetDao.findByProductoIdAndPropiedadesDetalles_Propiedades_Id(productoId, tipoPropiedadId);
    }

	public List<ProductoPropiedadDetalle> findByMenuSubUrl(String menuSubUrl, Integer estado){
		return productoProDetDao.findByMenuSubUrl(menuSubUrl, estado);
	}

	public List<ProductoPropiedadDetalle> findByCategoriaUrl(String categoriaUrl, Integer estado){
		return productoProDetDao.findByCategoriaUrl(categoriaUrl, estado);
	}

	public ProductoPropiedadDetalle obtenerProductoPropiedadDetallePorVariaciones(Integer productoId, Integer propiedadesDetallesId, Integer propiedadesDetallesId2){
		return productoProDetDao.findByPropiedadesDetallesAndProductoId(productoId, propiedadesDetallesId, propiedadesDetallesId2);
	}

	public List<ProductoItemsDto> listarProductosIndividualesPorMenuSubId(Double minPrecio, Double maxPrecio, Integer menuSubId) {

		List<ProductoPropiedadDetalle> productos = new ArrayList<>();
		if(minPrecio == null && maxPrecio == null){
			productos = productoProDetDao.findByProducto_ProductoCategoria_MenuSub_Id(menuSubId);
		}
		if(minPrecio != null && maxPrecio != null){
			productos = productoProDetDao.findByPrecioBetweenAndMenuSubId(minPrecio, maxPrecio, menuSubId);
		}
		return listarProductosItemsModificados(productos);
	}

	public List<ProductoItemsDto> listarProductosIndividualesPorCategoriaId (Double minPrecio, Double maxPrecio,Integer categoriaId){

		List<ProductoPropiedadDetalle> productos = new ArrayList<>();
		if(minPrecio == null && maxPrecio == null){
			productos = productoProDetDao.findByProducto_ProductoCategoria_Id(categoriaId);
		}
		if(minPrecio != null && maxPrecio != null){
			productos = productoProDetDao.findByPrecioBetweenAndCategoriaId(minPrecio, maxPrecio, categoriaId);
		}

		return listarProductosItemsModificados(productos);
	}

	public List<ProductoItemsDto> listarProductosItemsModificados(List<ProductoPropiedadDetalle> productoPropiedadesDetalles){

		List<ProductoItemsDto> productosIndividuales = new ArrayList<>();

		for(ProductoPropiedadDetalle productoPropiedadDetalle: productoPropiedadesDetalles){
			ProductoItemsDto nuevoProducto = new ProductoItemsDto();
			nuevoProducto.setId(productoPropiedadDetalle.getProducto().getId());
			nuevoProducto.setNombre(productoPropiedadDetalle.getProducto().getNombre());
			nuevoProducto.setImagen(productoPropiedadDetalle.getImagen().isEmpty() ? productoPropiedadDetalle.getProducto().getImagen() : productoPropiedadDetalle.getImagen());
			nuevoProducto.setNombreCategoria(productoPropiedadDetalle.getProducto().getProductoCategoria().getNombre());
			nuevoProducto.setUrlCategoria(productoPropiedadDetalle.getProducto().getProductoCategoria().getUrl());
			nuevoProducto.setNombreMenuSub(productoPropiedadDetalle.getProducto().getProductoCategoria().getMenuSub().getNombre());
			nuevoProducto.setUrlMenuSub(productoPropiedadDetalle.getProducto().getProductoCategoria().getMenuSub().getUrl());
			nuevoProducto.setProductoPropiedadDetalleId(productoPropiedadDetalle.getId());
			nuevoProducto.setPrecio(productoPropiedadDetalle.getPrecio());
			nuevoProducto.setPrecioReducido(productoPropiedadDetalle.getPrecioReducido());
			nuevoProducto.setStock(productoPropiedadDetalle.getStock());
			nuevoProducto.setDetalleNombre(productoPropiedadDetalle.getPropiedadesDetalles().getDetalles().getNombre());
			nuevoProducto.setDetalleModificado(productoPropiedadDetalle.getPropiedadesDetalles2() == null ? "Sin sabor" : productoPropiedadDetalle.getPropiedadesDetalles2().getDetalles().getNombre());
			nuevoProducto.setPropiedadNombre(productoPropiedadDetalle.getPropiedadesDetalles().getPropiedades().getNombre());
			for(ProductoMenuSub pms : productoPropiedadDetalle.getProducto().getProductoMenusSub()){
				if(pms.getMenuSub().getMenu().getId() == 6){
					nuevoProducto.setNombreMarca(pms.getMenuSub().getNombre());
					nuevoProducto.setUrlMarca(pms.getMenuSub().getUrl());
					nuevoProducto.setUrlMenuMarca(pms.getMenuSub().getMenu().getUrl());
				}
			}
			productosIndividuales.add(nuevoProducto);
		}

		// Ordenar la lista por el id de forma descendente .reversed()
		productosIndividuales.sort(Comparator.comparing(ProductoItemsDto::getId));

		return productosIndividuales;
	}
}
