package com.sise.titulacion.backend.service;

import java.util.List;
import com.sise.titulacion.backend.entity.Producto;
import com.sise.titulacion.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public Producto findOne(Long id) {
        if (id > 0) {
            return repository.findById(id).orElse(null);
        } else {
            throw null;
        }
    }

    @Override
    @Transactional
    public Producto save(Producto producto) throws DataAccessException {
        producto.setEstadoProducto(true);
        return repository.save(producto);
    }

    @Override
    @Transactional
    public void softDelete(Long id) {
        Producto producto = findOne(id);
        producto.setEstadoProducto(false);
        repository.save(producto);
    }

    @Override
    @Transactional
    public void hardDelete(Long id) {
        Producto producto = findOne(id);
        repository.delete(producto);
    }

}