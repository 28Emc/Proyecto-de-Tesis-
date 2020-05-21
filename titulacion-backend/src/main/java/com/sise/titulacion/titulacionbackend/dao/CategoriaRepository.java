package com.sise.titulacion.titulacionbackend.dao;

import com.sise.titulacion.titulacionbackend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "categorias", path = "categorias")
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}