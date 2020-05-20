package com.sise.titulacion.titulacionbackend.service;

import java.util.List;
import com.sise.titulacion.titulacionbackend.entity.Categoria;

public interface ICategoriaService {

    public List<Categoria> findAll();

    public Categoria findOne(Long id);

    public Categoria save(Categoria categoria);

    // ELIMINACIÓN LÒGICA MEDIANTE CAMBIO DE ESTADO SOLAMENTE
    public void softDelete(Long id);

    // ELIMINACIÓN DEFINITIVA EN LA BD
    public void hardDelete(Long id);

}