package com.digitalers.spring.boot.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "carritos_items")
@Data
public class ItemCarrito implements Serializable {

	private static final long serialVersionUID = -3093932710131489150L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;

	public BigDecimal getImporte() {
		return this.producto.getPrecio().multiply(BigDecimal.valueOf(this.cantidad));
	}

}
