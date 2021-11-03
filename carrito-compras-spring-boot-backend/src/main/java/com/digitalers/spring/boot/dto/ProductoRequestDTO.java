package com.digitalers.spring.boot.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductoRequestDTO implements Serializable {

	private static final long serialVersionUID = -6488649013018001224L;
	@NotEmpty
	@Size(min = 3, max = 40)
	private String descripcion;
	@Min(value = 0)
	private BigDecimal precio;
	@Min(value = 0)
	private Long stock;
	private String nombreImagen;

}
