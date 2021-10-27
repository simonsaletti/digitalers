package com.digitalers.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalers.spring.boot.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
