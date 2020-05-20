package com.sise.titulacion.titulacionbackend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sise.titulacion.titulacionbackend.entity.Categoria;
import com.sise.titulacion.titulacionbackend.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*", "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/listar-categorias")
    public List<Categoria> listarCategorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/ver-categoria/{id}")
    public ResponseEntity<?> verCategoria(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Categoria categoria = null;

        try {
            categoria = categoriaService.findOne(id);
        } catch (DataAccessException e) {
            response.put("mensaje",
                    "Lo sentimos, hubo un error a la hora de realizar la consulta a la base de datos. Inténtelo mas tarde!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (categoria == null) {
            response.put("mensaje", "Error, la categoría con el ID " + id + " no existe en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
    }

    @PostMapping("/crear-categoria")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaNueva = null;
        Map<String, Object> response = new HashMap<>();

        try {
            categoriaNueva = categoriaService.save(categoria);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de registrar la categoría");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La categoría ha sido creada con éxito!");
        response.put("categoria", categoriaNueva);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/modificar-categoria/{id}")
    public ResponseEntity<?> modificarCategoria(@RequestBody Categoria categoria, @PathVariable Long id) {
        Categoria categoriaActual = null;
        Categoria categoriaModificada = null;
        Map<String, Object> response = new HashMap<>();

        try {
            categoriaActual = categoriaService.findOne(id);

            if (categoriaActual == null) {
                response.put("mensaje", "Error, la categoría con el ID " + id + " no existe en la base de datos!");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            categoriaActual.setNombreCategoria(categoria.getNombreCategoria());
            categoriaActual.setEstadoCategoria(categoria.isEstadoCategoria());
            categoriaActual.setDateUpdated(new Date());
            categoriaModificada = categoriaService.save(categoriaActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de actualizar la categoría");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La categoría ha sido actualizada con éxito!");
        response.put("categoria", categoriaModificada);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/deshabilitar-categoria/{id}")
    public ResponseEntity<?> deshabilitarCategoria(@PathVariable Long id) {
        Categoria categoriaActual = null;
        Map<String, Object> response = new HashMap<>();

        try {
            categoriaActual = categoriaService.findOne(id);

            if (categoriaActual == null) {
                response.put("mensaje", "Error, la categoría con el ID " + id + " no existe en la base de datos!");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            categoriaService.softDelete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de actualizar la categoría");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La categoría ha sido deshabilitada!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar-categoria/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            categoriaService.hardDelete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de eliminar la categoría");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La categoría ha sido eliminada de la base de datos");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}