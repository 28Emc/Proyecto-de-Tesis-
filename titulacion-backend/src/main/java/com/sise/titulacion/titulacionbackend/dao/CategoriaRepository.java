package com.sise.titulacion.titulacionbackend.dao;

import com.sise.titulacion.titulacionbackend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "categorias", path = "categorias")
// @Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}