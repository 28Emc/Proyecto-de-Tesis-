package com.sise.titulacion.titulacionbackend.controller;

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
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = { "*", "http://localhost:4200" })
@RestController
@Api(value = "categorias", description = "Operaciones referentes a las categorias")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService categoriaService;

    @ApiOperation(value = "Mostrar un listado de las categorias existentes", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "El listado de categorías ha sido cargado con éxito"),
            @ApiResponse(code = 401, message = "Lo sentimos, no estas autorizado a acceder a este recurso"),
            @ApiResponse(code = 403, message = " "),
            @ApiResponse(code = 404, message = "Lo sentimos, no hay categorias en la base de datos"),
            @ApiResponse(code = 500, message = "Lo sentimos, hubo un error a la hora de realizar la consulta a la base de datos. Inténtelo mas tarde") })
    @GetMapping(value = "/listar-categorias", produces = "application/json")
    public ResponseEntity<?> listarCategorias() {
        Map<String, Object> response = new HashMap<>();
        List<Categoria> categorias = null;
        try {
            categorias = categoriaService.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje",
                    "Lo sentimos, hubo un error a la hora de realizar la consulta a la base de datos. Inténtelo mas tarde");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (categorias.size() == 0) {
            response.put("mensaje", "Lo sentimos, no hay categorias en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("mensaje", "El listado de categorías ha sido cargado con éxito");
        response.put("categorias", categorias);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Consultar por una categoría en especifico por su id", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Categoría cargada con éxito"),
            @ApiResponse(code = 401, message = "Lo sentimos, no estas autorizado a acceder a este recurso"),
            @ApiResponse(code = 403, message = " "),
            @ApiResponse(code = 404, message = "Error, la categoría con el ID especificado no existe en la base de datos"),
            @ApiResponse(code = 500, message = "Lo sentimos, hubo un error a la hora de realizar la consulta a la base de datos. Inténtelo mas tarde") })
    @GetMapping(value = "/ver-categoria/{id}", produces = "application/json")
    public ResponseEntity<?> verCategoria(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Categoria categoria = null;

        try {
            categoria = categoriaService.findOne(id);
        } catch (DataAccessException e) {
            response.put("mensaje",
                    "Lo sentimos, hubo un error a la hora de realizar la consulta a la base de datos. Inténtelo mas tarde");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (categoria == null) {
            response.put("mensaje", "Error, la categoría con el ID " + id + " no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
    }

    @ApiOperation(value = "Crear una categoría", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = " "),
            @ApiResponse(code = 201, message = "Categoría creada con éxito"),
            @ApiResponse(code = 401, message = "Lo sentimos, no estas autorizado a acceder a este recurso"),
            @ApiResponse(code = 403, message = " "), @ApiResponse(code = 404, message = " "),
            @ApiResponse(code = 500, message = "Lo sentimos, hubo un error a la hora de realizar la consulta a la base de datos. Inténtelo mas tarde") })
    @PostMapping(value = "/crear-categoria", produces = "application/json")
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

        response.put("mensaje", "La categoría ha sido creada con éxito");
        response.put("categoria", categoriaNueva);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Modificar una categoría mediante su id", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = " "),
            @ApiResponse(code = 201, message = "Categoría actualizada con éxito"),
            @ApiResponse(code = 401, message = "Lo sentimos, no estas autorizado a acceder a este recurso"),
            @ApiResponse(code = 403, message = " "),
            @ApiResponse(code = 404, message = "Error, la categoría con el ID especificado no existe en la base de datos"),
            @ApiResponse(code = 500, message = "Lo sentimos, hubo un error a la hora de actualizar la categoría. Inténtelo mas tarde") })
    @PutMapping(value = "/modificar-categoria/{id}", produces = "application/json")
    public ResponseEntity<?> modificarCategoria(@RequestBody Categoria categoria, @PathVariable Long id) {
        Categoria categoriaActual = null;
        Categoria categoriaModificada = null;
        Map<String, Object> response = new HashMap<>();

        try {
            categoriaActual = categoriaService.findOne(id);

            if (categoriaActual == null) {
                response.put("mensaje", "Error, la categoría con el ID " + id + " no existe en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            categoriaActual.setNombreCategoria(categoria.getNombreCategoria());
            categoriaActual.setEstadoCategoria(categoria.isEstadoCategoria());
            categoriaModificada = categoriaService.save(categoriaActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de actualizar la categoría");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Categoría actualizada con éxito");
        response.put("categoria", categoriaModificada);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deshabilitar una categoría mediante su id (no borra en BBDD)", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Categoría deshabilitada"),
            @ApiResponse(code = 201, message = " "),
            @ApiResponse(code = 401, message = "Lo sentimos, no estas autorizado a acceder a este recurso"),
            @ApiResponse(code = 403, message = " "),
            @ApiResponse(code = 404, message = "Error, la categoría con el ID especificado no existe en la base de datos"),
            @ApiResponse(code = 500, message = "Lo sentimos, hubo un error a la hora de deshabilitar la cetagoría. Inténtelo mas tarde") })
    @PutMapping(value = "/deshabilitar-categoria/{id}", produces = "application/json")
    public ResponseEntity<?> deshabilitarCategoria(@PathVariable Long id) {
        Categoria categoriaActual = null;
        Map<String, Object> response = new HashMap<>();

        try {
            categoriaActual = categoriaService.findOne(id);

            if (categoriaActual == null) {
                response.put("mensaje", "Error, la categoría con el ID " + id + " no existe en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            categoriaService.softDelete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de deshabilitar la categoría");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Categoría deshabilitada");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Borrar una categoría mediante su id (borra en BBDD)", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Categoría eliminada"),
            @ApiResponse(code = 201, message = " "), @ApiResponse(code = 204, message = " "),
            @ApiResponse(code = 401, message = "Lo sentimos, no estas autorizado a acceder a este recurso"),
            @ApiResponse(code = 403, message = " "),
            @ApiResponse(code = 404, message = "Error, la categoría con el ID especificado no existe en la base de datos"),
            @ApiResponse(code = 500, message = "Lo sentimos, hubo un error a la hora de eliminar la categoría. Inténtelo mas tarde") })
    @DeleteMapping(value = "/eliminar-categoria/{id}", produces = "application/json")
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