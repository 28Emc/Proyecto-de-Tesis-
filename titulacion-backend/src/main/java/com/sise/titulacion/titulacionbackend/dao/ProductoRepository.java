package com.sise.titulacion.titulacionbackend.dao;

import com.sise.titulacion.titulacionbackend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "productos", path = "productos")
// @Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}