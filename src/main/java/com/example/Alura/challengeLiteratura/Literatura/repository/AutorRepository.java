package com.example.Alura.challengeLiteratura.Literatura.repository;

import com.example.Alura.challengeLiteratura.Literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;


public interface  AutorRepository extends JpaRepository<Autor, Long> {
    // Busca un autor por su nombre para evitar duplicados
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    // Busca autores que estaban vivos en un año determinado
    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND (a.anioFallecimiento IS NULL OR a.anioFallecimiento >= :anio)")
    List<Autor> findAutoresVivosEnAnio(Integer anio);
}
