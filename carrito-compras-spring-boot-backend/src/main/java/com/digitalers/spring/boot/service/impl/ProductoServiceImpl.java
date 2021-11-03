package com.digitalers.spring.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalers.spring.boot.dto.ProductoRequestDTO;
import com.digitalers.spring.boot.dto.ProductoResponseDTO;
import com.digitalers.spring.boot.entity.Producto;
import com.digitalers.spring.boot.repository.ProductoRepository;
import com.digitalers.spring.boot.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	private ProductoRepository dao;

	@Autowired
	public ProductoServiceImpl(ProductoRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Producto> listarTodos() {
		List<Producto> productos = this.dao.findAll();
		return productos;
	}

	@Override
	public ProductoResponseDTO insertar(ProductoRequestDTO dto) {
		Producto producto = new Producto(dto.getDescripcion(), dto.getPrecio(), dto.getStock(), dto.getNombreImagen());
		this.dao.save(producto);
		return new ProductoResponseDTO(producto.getId(), producto.getDescripcion(), producto.getPrecio(),
				producto.getStock(), producto.getFechaCreacion(), producto.getURLImagen());
	}

}
