package com.digitalers.spring.boot.service;

import java.util.List;

import com.digitalers.spring.boot.entity.Cliente;

public interface ClienteService {

	List<Cliente> listarTodos();

	Cliente obtener(Long id);

}
