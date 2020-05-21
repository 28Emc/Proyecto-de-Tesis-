package com.sise.titulacion.titulacionbackend.dao;

import com.sise.titulacion.titulacionbackend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "productos", path = "productos")
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}