package com.digitalers.spring.boot.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
public class Producto implements Serializable {

	private static final long serialVersionUID = 3006480524381487863L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descripcion;

	private BigDecimal precio;

	private Long stock;

	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;

	private String URLImagen;

	public Producto(String descripcion, BigDecimal precio, Long stock, String URLImagen) {
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.URLImagen = URLImagen;
	}

	@PrePersist
	public void guardar() {
		this.fechaCreacion = LocalDate.now();
	}

}
