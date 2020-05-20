package com.sise.titulacion.titulacionbackend.controller;

import java.util.List;
import com.sise.titulacion.titulacionbackend.entity.Categoria;
import com.sise.titulacion.titulacionbackend.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*", "http://localhost:4200" })
@RestController
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria> listarCategorias() {
        return categoriaService.findAll();
    }

}