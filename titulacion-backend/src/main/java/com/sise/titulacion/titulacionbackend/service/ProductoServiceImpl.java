package com.sise.titulacion.titulacionbackend.service;

import java.util.List;
import com.sise.titulacion.titulacionbackend.dao.ProductoRepository;
import com.sise.titulacion.titulacionbackend.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findOne(Long id) throws Exception {
        if (id > 0) {
            return repository.findById(id).orElseThrow();
        } else {
            throw new Exception("Error, el id es inválido");
        }
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    @Override
    @Transactional
    public void SoftDelete(Long id) throws Exception {
        Producto producto = findOne(id);
        producto.setEstadoProducto(false);
        repository.save(producto);
    }

    /*@Override
    @Transactional
    public void hardDelete(Long id) throws Exception {
        Producto producto = findOne(id);
        repository.delete(producto);
    }*/

}