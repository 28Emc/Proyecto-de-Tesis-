package com.sise.titulacion.titulacionbackend.service;

import java.util.List;
import com.sise.titulacion.titulacionbackend.entity.Producto;
import org.springframework.dao.DataAccessException;

public interface IProductoService {

    public List<Producto> findAll();

    public Producto findOne(Long id);

    public Producto save(Producto producto) throws DataAccessException;

    // ELIMINACIÓN LÒGICA MEDIANTE CAMBIO DE ESTADO SOLAMENTE
    public void softDelete(Long id);

    // ELIMINACIÓN DEFINITIVA EN LA BD
    public void hardDelete(Long id);

}