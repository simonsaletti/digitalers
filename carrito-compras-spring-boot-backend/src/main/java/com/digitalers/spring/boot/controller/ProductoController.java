package com.digitalers.spring.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalers.spring.boot.entity.Producto;
import com.digitalers.spring.boot.service.ProductoService;

@CrossOrigin(origins = { "http://127.0.0.1:5500" })
@RestController
@RequestMapping("/productos")
public class ProductoController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private ProductoService productoService;

	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping
	public ResponseEntity<?> obtenerTodos() {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			log.info("Buscando todos los productos...");
			List<Producto> productos = this.productoService.listarTodos();
			log.info("Productos obtenidos: " + productos.size());
			respuesta.put("productos", productos);
			respuesta.put("mensaje", "Los productos han sido encontrados correctamente");
			respuesta.put("status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ha ocurrido un error interno en la aplicación: " + e.getMessage());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
