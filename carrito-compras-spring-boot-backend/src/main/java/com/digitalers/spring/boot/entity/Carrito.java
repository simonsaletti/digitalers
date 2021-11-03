package com.digitalers.spring.boot.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "carritos")
@Data
public class Carrito implements Serializable {

	private static final long serialVersionUID = 7796172142451602209L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private String observacion;
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;

	// Un carrito puede tener muchos items, pero un item pertenece a un único
	// carrito.
	@OneToMany(cascade = CascadeType.ALL)
	// La clave foránea estará en la tabla cuya entidad es ItemCarrito.
	// Como ésta no es una relación bidireccional, no tenemos un atributo llamado
	// "carrito" en la clase ItemCarrito, por lo tanto, esta llave foránea no se
	// crea de forma automática. Debemos usar obligatoriamente @JoinColumn.
	@JoinColumn(name = "carrito_id")
	// Inicializamos esta lista en el constructor de la clase.
	private List<ItemCarrito> items = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@PrePersist
	public void guardar() {
		this.fechaCreacion = LocalDate.now();
	}

	// Método para calcular el total del carrito.
	public BigDecimal getTotal() {
		return this.items.stream().map(item -> item.getImporte()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
