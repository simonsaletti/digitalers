package com.digitalers.spring.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalers.spring.boot.entity.Cliente;
import com.digitalers.spring.boot.service.ClienteService;

@CrossOrigin(origins = { "http://127.0.0.1:5500" })
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public ResponseEntity<?> obtenerTodos() {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			log.info("Buscando todos los clientes...");
			List<Cliente> clientes = this.clienteService.listarTodos();
			log.info("Productos obtenidos: " + clientes.size());
			respuesta.put("clientes", clientes);
			respuesta.put("mensaje", "Los clientes han sido encontrados correctamente");
			respuesta.put("status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ha ocurrido un error interno en la aplicación: " + e.getMessage());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			Cliente cliente = this.clienteService.obtener(id);
			respuesta.put("cliente", cliente);
			respuesta.put("mensaje", "EL cliente ha sido encontrado correctamente");
			respuesta.put("status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			respuesta.put("mensaje", "Error al encontrar el cliente con ID: " + id + ".");
			respuesta.put("status", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ha ocurrido un error interno en la aplicación: " + e.getMessage());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
