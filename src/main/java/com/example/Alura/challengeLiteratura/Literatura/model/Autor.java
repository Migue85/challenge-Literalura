package com.example.Alura.challengeLiteratura.Literatura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ***** INICIO DE LA CORRECCIÓN *****
    // Se aumenta la longitud máxima de la columna a 1000 caracteres
    // para admitir nombres de autores muy largos.
    @Column(unique = true, length = 1000)
    private String nombre;
    // ***** FIN DE LA CORRECCIÓN *****

    private Integer anioNacimiento;
    private Integer anioFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    // Constructor por defecto requerido por JPA
    public Autor() {}

    // Constructor para crear un autor a partir de los datos de la API
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioFallecimiento = datosAutor.anioFallecimiento();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }



    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        // Asigna este autor a cada libro de la lista
        libros.forEach(libro -> libro.setAutor(this));
        this.libros = libros;
    }

    @Override
    public String toString() {
        String librosTitulos = libros.stream()
                .map(Libro::getTitulo)
                .reduce((a, b) -> a + ", " + b)
                .orElse("N/A");

        return String.format(
                "---------- AUTOR ----------\n" +
                        "Nombre: %s\n" +
                        "Año de Nacimiento: %d\n" +
                        "Año de Fallecimiento: %d\n" +
                        "Libros: [%s]\n" +
                        "---------------------------\n",
                nombre, anioNacimiento, anioFallecimiento, librosTitulos
        );
    }
}

