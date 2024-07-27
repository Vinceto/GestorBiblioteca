package com.ejercicios.gestorbiblioteca.controller;
import com.ejercicios.gestorbiblioteca.entity.Libro;
import com.ejercicios.gestorbiblioteca.service.LibroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private static final Logger logger = LoggerFactory.getLogger(LibroController.class);

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();
        logger.info("Obteniendo todos los libros");
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> guardarLibro(@RequestBody Libro libro) {
        Map<String, Object> response = new HashMap<>();
        try {
            Libro nuevoLibro = libroService.guardarLibro(libro);
            response.put("mensaje", "Libro guardado con éxito");
            response.put("libro", nuevoLibro);
            logger.info("Guardando libro: " + libro.getTitulo());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", e.getMessage());
            logger.error("Error al guardar el libro: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarLibro(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            libroService.eliminarLibro(id);
            response.put("mensaje", "Libro eliminado con éxito");
            logger.info("Eliminando libro con ID: " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", e.getMessage());
            logger.error("Error al eliminar el libro: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}