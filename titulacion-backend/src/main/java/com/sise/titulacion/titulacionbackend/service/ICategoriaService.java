package com.sise.titulacion.titulacionbackend.service;

import java.util.List;
import com.sise.titulacion.titulacionbackend.entity.Categoria;
import org.springframework.dao.DataAccessException;

public interface ICategoriaService {

    public List<Categoria> findAll();

    public Categoria findOne(Long id);

    public Categoria save(Categoria categoria) throws DataAccessException;

    // ELIMINACIÓN LÒGICA MEDIANTE CAMBIO DE ESTADO SOLAMENTE
    public void softDelete(Long id);

    // ELIMINACIÓN DEFINITIVA EN LA BD
    public void hardDelete(Long id);

}