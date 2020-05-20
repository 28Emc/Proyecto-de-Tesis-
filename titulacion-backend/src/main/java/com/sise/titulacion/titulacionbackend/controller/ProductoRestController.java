package com.sise.titulacion.titulacionbackend.controller;

import java.util.List;
import com.sise.titulacion.titulacionbackend.entity.Producto;
import com.sise.titulacion.titulacionbackend.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*", "http://localhost:4200" })
@RestController
public class ProductoRestController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoService.findAll();
    }

}