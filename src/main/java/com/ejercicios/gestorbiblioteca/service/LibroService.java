package com.ejercicios.gestorbiblioteca.service;
import com.ejercicios.gestorbiblioteca.entity.Libro;
import com.ejercicios.gestorbiblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Libro guardarLibro(Libro libro) throws Exception {
        if (libroRepository.existsByTitulo(libro.getTitulo())) {
            throw new Exception("El libro ya existe");
        }
        return libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) throws Exception {
        if (!libroRepository.existsById(id)) {
            throw new Exception("El libro no existe");
        }
        libroRepository.deleteById(id);
    }
}
