package com.digitalers.spring.boot.service;

import java.util.List;

import com.digitalers.spring.boot.dto.ProductoRequestDTO;
import com.digitalers.spring.boot.dto.ProductoResponseDTO;
import com.digitalers.spring.boot.entity.Producto;

public interface ProductoService {

	List<Producto> listarTodos();

	ProductoResponseDTO insertar(ProductoRequestDTO dto);

}
