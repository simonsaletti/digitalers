package com.digitalers.spring.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalers.spring.boot.entity.Cliente;
import com.digitalers.spring.boot.repository.ClienteRepository;
import com.digitalers.spring.boot.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository dao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listarTodos() {
		List<Cliente> clientes = this.dao.findAll();
		return clientes;
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente obtener(Long id) {
		Cliente cliente = this.dao.findById(id).orElseThrow();
		return cliente;
	}

}
