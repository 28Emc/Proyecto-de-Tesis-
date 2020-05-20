package com.sise.titulacion.titulacionbackend.service;

import java.util.List;
import com.sise.titulacion.titulacionbackend.entity.Producto;

public interface IProductoService {

    public List<Producto> findAll();

}