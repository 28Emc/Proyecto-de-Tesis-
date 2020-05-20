package com.sise.titulacion.titulacionbackend.service;

import java.util.List;
import com.sise.titulacion.titulacionbackend.entity.Producto;

public interface IProductoService {

    public List<Producto> findAll();

    public Producto findOne(Long id) throws Exception;

    public Producto save(Producto producto);

    // ELIMINACIÓN LÒGICA MEDIANTE CAMBIO DE ESTADO SOLAMENTE
    public void SoftDelete(Long id) throws Exception;

    // ELIMINACIÓN DEFINITIVA EN LA BD
    //public void hardDelete(Long id) throws Exception;

}