package com.sise.titulacion.titulacionbackend.service;

import java.util.Date;
import java.util.List;
import com.sise.titulacion.titulacionbackend.dao.CategoriaRepository;
import com.sise.titulacion.titulacionbackend.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Categoria save(Categoria categoria) {
        categoria.setEstadoCategoria(true);
        categoria.setDateCreated(new Date());
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