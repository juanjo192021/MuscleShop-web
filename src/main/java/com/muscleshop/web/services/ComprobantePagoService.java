package com.muscleshop.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscleshop.web.dao.IComprobantePagoDao;
import com.muscleshop.web.models.ComprobantePago;


@Service
public class ComprobantePagoService {

	@Autowired
	private IComprobantePagoDao comprobanteDao;

	public List<ComprobantePago> listarComprobante() {
		return comprobanteDao.findAll();
	}

	public void saveCompro(ComprobantePago compro) {
		comprobanteDao.save(compro);
	}

	public void eliminarCompro(Integer id) {
		comprobanteDao.deleteById(id);
	}

	public ComprobantePago listarComproID(Integer id) {
		return comprobanteDao.findById(id).orElse(null);
	}
	
	public ComprobantePago obtenerNombreComp(String nombre) {
		List<ComprobantePago> comprobante = comprobanteDao.findByNombre(nombre);
		return comprobante.isEmpty() ? null : comprobante.get(0);
	}

}
