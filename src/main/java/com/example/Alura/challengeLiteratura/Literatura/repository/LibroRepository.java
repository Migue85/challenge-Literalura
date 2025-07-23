package com.example.Alura.challengeLiteratura.Literatura.repository;

import com.example.Alura.challengeLiteratura.Literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Busca un libro por su título para evitar duplicados
    Optional<Libro> findByTituloIgnoreCase(String titulo);

    // Busca libros por el idioma especificado
    List<Libro> findByIdioma(String idioma);
}
