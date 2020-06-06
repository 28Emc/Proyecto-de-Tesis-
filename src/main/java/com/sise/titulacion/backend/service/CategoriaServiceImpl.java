package com.sise.titulacion.backend.service;

import java.util.List;
import com.sise.titulacion.backend.entity.Categoria;
import com.sise.titulacion.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return (List<Categoria>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findOne(Long id) {
        if (id > 0) {
            return repository.findById(id).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Categoria save(Categoria categoria) throws DataAccessException {
        categoria.setEstadoCategoria(true);
        return repository.save(categoria);
    }

    @Override
    @Transactional
    public void softDelete(Long id) {
        Categoria categoria = findOne(id);
        categoria.setEstadoCategoria(false);
        repository.save(categoria);
    }

    @Override
    @Transactional
    public void hardDelete(Long id) {
        Categoria categoria = findOne(id);
        repository.delete(categoria);
    }

}