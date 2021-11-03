package com.digitalers.spring.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitalers.spring.boot.dto.ProductoRequestDTO;
import com.digitalers.spring.boot.dto.ProductoResponseDTO;
import com.digitalers.spring.boot.entity.Producto;
import com.digitalers.spring.boot.service.ProductoService;
import com.digitalers.spring.boot.utils.FileUploadUtil;

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

	@PostMapping
	public ResponseEntity<?> crear(@Valid @ModelAttribute ProductoRequestDTO dto, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile archivo) {

		Map<String, Object> respuesta = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errorList = result.getFieldErrors().stream()
					.map(fieldError -> "Campo " + fieldError.getField() + "inválido: " + fieldError.getDefaultMessage())
					.collect(Collectors.toList());
			respuesta.put("errors", errorList);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			if (archivo != null && !archivo.isEmpty()) {
				dto.setNombreImagen(archivo.getOriginalFilename());
			}
			ProductoResponseDTO elementoGuardado = this.productoService.insertar(dto);
			FileUploadUtil.guardarArchivo(archivo);
			respuesta.put("mensaje", "Producto correctamente guardado");
			respuesta.put("elemento", elementoGuardado);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ha ocurrido un error: " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
