package com.sise.titulacion.titulacionbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sise.titulacion.titulacionbackend.entity.Producto;
import com.sise.titulacion.titulacionbackend.service.IProductoService;
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

@CrossOrigin(origins = { "*", "http://localhost:4200" })
@RestController
public class ProductoRestController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/listar-productos")
    public ResponseEntity<?> listarProductos() {
        Map<String, Object> response = new HashMap<>();
        List<Producto> productos = null;
        try {
            productos = productoService.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje",
                    "Lo sentimos, hubo un error a la hora de realizar la consulta a la base de datos. Inténtelo mas tarde!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (productos.size() == 0) {
            response.put("mensaje", "Lo sentimos, no hay productos en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("mensaje", "El listado de productos ha sido cargado con éxito!");
        response.put("productos", productos);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/ver-producto/{id}")
    public ResponseEntity<?> verProducto(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Producto producto = null;

        try {
            producto = productoService.findOne(id);
        } catch (DataAccessException e) {
            response.put("mensaje",
                    "Lo sentimos, hubo un error a la hora de realizar la consulta a la base de datos. Inténtelo mas tarde!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (producto == null) {
            response.put("mensaje", "Error, el producto con el ID " + id + " no existe en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

    @PostMapping("/crear-producto")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        Producto productoNuevo = null;
        Map<String, Object> response = new HashMap<>();

        try {
            productoNuevo = productoService.save(producto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de registrar la categoría");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto ha sido creado con éxito!");
        response.put("producto", productoNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/modificar-producto/{id}")
    public ResponseEntity<?> modificarProducto(@RequestBody Producto producto, @PathVariable Long id) {
        Producto productoActual = null;
        Producto productoModificado = null;
        Map<String, Object> response = new HashMap<>();

        try {
            productoActual = productoService.findOne(id);

            if (productoActual == null) {
                response.put("mensaje", "Error, el producto con el ID " + id + " no existe en la base de datos!");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            productoActual.setNombreProducto(producto.getNombreProducto());
            productoActual.setDescripcionProducto(producto.getDescripcionProducto());
            productoActual.setPresentacionProducto(producto.getPresentacionProducto());
            productoActual.setStockProducto(producto.getStockProducto());
            productoActual.setPrecioProducto(producto.getPrecioProducto());
            productoActual.setFotoProducto(producto.getFotoProducto());
            productoActual.setEstadoProducto(producto.isEstadoProducto());
            productoActual.setCategoria(producto.getCategoria());
            productoModificado = productoService.save(productoActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de actualizar la categoría");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto ha sido actualizado con éxito!");
        response.put("producto", productoModificado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/deshabilitar-producto/{id}")
    public ResponseEntity<?> deshabilitarProducto(@PathVariable Long id) {
        Producto productoActual = null;
        Map<String, Object> response = new HashMap<>();

        try {
            productoActual = productoService.findOne(id);

            if (productoActual == null) {
                response.put("mensaje", "Error, el producto con el ID " + id + " no existe en la base de datos!");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            productoService.softDelete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de deshabilitar el producto");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto ha sido deshabilitado!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar-producto/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            productoService.hardDelete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Lo sentimos, hubo un error a la hora de eliminar la categoría");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto ha sido eliminado de la base de datos");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}