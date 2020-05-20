package com.sise.titulacion.titulacionbackend.service;

import java.util.List;
import com.sise.titulacion.titulacionbackend.entity.Categoria;

public interface ICategoriaService {
    
    public List<Categoria> findAll();
    
}