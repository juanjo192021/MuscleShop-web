package com.muscleshop.web.services.implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.muscleshop.web.models.ProductoMenuSub;
import com.muscleshop.web.models.ProductoPropiedadesDetalles;
import com.muscleshop.web.models.dto.ProductoItemsDto;
import com.muscleshop.web.services.IProductoPropiedadesDetallesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoPropiedadesDetallesDao;

@Service
public class ProductoPropiedadDetalleService implements IProductoPropiedadesDetallesService {
	
	@Autowired
	private IProductoPropiedadesDetallesDao iProductoPropiedadesDetallesDao;

	private int estadoId=1;

	public List<ProductoPropiedadesDetalles> obtenerProductoPropiedadesDetallesPorForma(int productoFormaId){
		return iProductoPropiedadesDetallesDao.findByProductoForma_IdAndEstado_Id(productoFormaId,estadoId);
	}

	/*public List<ProductoPropiedadesDetalles> listarProDetPro() {
		return productoProDetDao.findAll();
	}

	public ProductoPropiedadesDetalles listarPorID(Integer id) {
		return productoProDetDao.findById(id).orElse(null);
	}*/
	
/*	public List<ProductoPropiedadesDetalles> obtenerProductosPorIdPresentacion(int idPresentacion) {
	    return productoProDetDao.findByPropiedadesDetallesId(idPresentacion);
	}*/
	
/*
	public List<ProductoPropiedadesDetalles> obtenerPorTipoDePropiedad(int productoId, int tipoPropiedadId) {
        return productoProDetDao.findByProductoIdAndPropiedadesDetalles_Propiedades_Id(productoId, tipoPropiedadId);
    }
*/



/*	public List<ProductoPropiedadesDetalles> findByMenuSubUrl(String menuSubUrl, Integer estado){
		return productoProDetDao.findByMenuSubUrl(menuSubUrl, estado);
	}

	public List<ProductoPropiedadesDetalles> findByCategoriaUrl(String categoriaUrl, Integer estado){
		return productoProDetDao.findByCategoriaUrl(categoriaUrl, estado);
	}

	public ProductoPropiedadesDetalles obtenerProductoPropiedadDetallePorVariaciones(Integer productoId, Integer propiedadesDetallesId, Integer propiedadesDetallesId2){
		return productoProDetDao.findByPropiedadesDetallesAndProductoId(productoId, propiedadesDetallesId, propiedadesDetallesId2);
	}
*/
	public List<ProductoItemsDto> obtenerProductosIndividualesPorMenuSubId(Double minPrecio, Double maxPrecio, Integer menuSubId) {

		List<ProductoPropiedadesDetalles> productos = new ArrayList<>();
		if(minPrecio == null && maxPrecio == null){
			productos = iProductoPropiedadesDetallesDao.findByProducto_ProductoCategoria_MenuSub_Id(menuSubId);
		}
		if(minPrecio != null && maxPrecio != null){
			productos = iProductoPropiedadesDetallesDao.findByPrecioBetweenAndMenuSubId(minPrecio, maxPrecio, menuSubId);
		}
		return obtenerProductosItemsModificados(productos);
	}
	public List<ProductoItemsDto> obtenerProductosIndividualesPorCategoriaId (Double minPrecio, Double maxPrecio,Integer categoriaId){

		List<ProductoPropiedadesDetalles> productos = new ArrayList<>();
		if(minPrecio == null && maxPrecio == null){
			productos = iProductoPropiedadesDetallesDao.findByProducto_ProductoCategoria_Id(categoriaId);
		}
		if(minPrecio != null && maxPrecio != null){
			productos = iProductoPropiedadesDetallesDao.findByPrecioBetweenAndCategoriaId(minPrecio, maxPrecio, categoriaId);
		}

		return obtenerProductosItemsModificados(productos);
	}

	public ProductoPropiedadesDetalles obtenerProductoPropiedadDetallesPorVariaciones(int productoId, String variacion1, String variacion2){
		return iProductoPropiedadesDetallesDao.findByProducto_IdAndProductoVariacion1_ValorAndProductoVariacion2_Valor(productoId, variacion1, variacion2);
	}

	public List<ProductoItemsDto> obtenerProductosItemsModificados(List<ProductoPropiedadesDetalles> productoPropiedadesDetalles){

		List<ProductoItemsDto> productosIndividuales = new ArrayList<>();

		for(ProductoPropiedadesDetalles productoPropiedadDetalle: productoPropiedadesDetalles){
			ProductoItemsDto nuevoProducto = new ProductoItemsDto();
			nuevoProducto.setId(productoPropiedadDetalle.getProducto().getId());
			nuevoProducto.setNombre(productoPropiedadDetalle.getProducto().getNombre());
			nuevoProducto.setImagen(productoPropiedadDetalle.getImagen().isEmpty() ? productoPropiedadDetalle.getProducto().getImagen() : productoPropiedadDetalle.getImagen());
			nuevoProducto.setUrlProducto(productoPropiedadDetalle.getProducto().getUrl());
			nuevoProducto.setNombreCategoria(productoPropiedadDetalle.getProducto().getProductoCategoria().getNombre());
			nuevoProducto.setUrlCategoria(productoPropiedadDetalle.getProducto().getProductoCategoria().getUrl());
			nuevoProducto.setNombreMenuSub(productoPropiedadDetalle.getProducto().getProductoCategoria().getMenuSub().getNombre());
			nuevoProducto.setUrlMenuSub(productoPropiedadDetalle.getProducto().getProductoCategoria().getMenuSub().getUrl());
			nuevoProducto.setProductoPropiedadDetalleId(productoPropiedadDetalle.getId());
			nuevoProducto.setPrecio(productoPropiedadDetalle.getPrecio());
			nuevoProducto.setPrecioReducido(productoPropiedadDetalle.getPrecioReducido());
			nuevoProducto.setStock(productoPropiedadDetalle.getStock());
			nuevoProducto.setDetalleNombre(productoPropiedadDetalle.getProductoVariacion1().getValor());
			nuevoProducto.setDetalleModificado(productoPropiedadDetalle.getProductoVariacion2() == null || productoPropiedadDetalle.getProductoVariacion2().getValor() == null ? "Sin sabor" : productoPropiedadDetalle.getProductoVariacion2().getValor());
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
