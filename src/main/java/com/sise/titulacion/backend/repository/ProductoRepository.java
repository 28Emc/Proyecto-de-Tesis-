package com.sise.titulacion.backend.repository;

import com.sise.titulacion.backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}