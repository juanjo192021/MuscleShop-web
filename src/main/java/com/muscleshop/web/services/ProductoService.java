package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IProductoDao;
import com.muscleshop.web.models.MenuSub;
import com.muscleshop.web.models.Producto;
import com.muscleshop.web.models.ProductoCategoria;

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

	public void saveProducto(Producto producto) {
		productoDao.save(producto);
	}

	public void eliminarProducto(Integer id) {
		productoDao.deleteById(id);
	}

	public List<Producto> listarProCate(String categoriaUrl) {
		return productoDao.findByCategoriaUrl(categoriaUrl);
	}
	
	public List<Producto> listarPorCategoria(ProductoCategoria categoria) {
        return productoDao.findByCategoria(categoria);
    }
	
/*
    public List<Producto> listarPorMenuSub(MenuSub menuSub) {
        return productoDao.findByMenuSub(menuSub);
    }
*/

    public List<Producto> listarProForma(int productoFormaId){
    	return productoDao.findByProductoFormaId(productoFormaId);
    }
    
    public List<Producto> buscarProducto(String nombre) {
		return productoDao.buscarProducto(nombre);
	}
	
	


}
