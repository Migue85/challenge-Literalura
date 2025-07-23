package com.example.Alura.challengeLiteratura.Literatura.model;

import jakarta.persistence.*;

@Entity

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Se aumenta longitud máxima de la columna a 1000 caracteres
    // para admitir títulos de libros muy largos.
    @Column(unique = true, length = 1000)
    private String titulo;

    private String idioma;
    private Double numeroDeDescargas;

    @ManyToOne
    private Autor autor;

    // Constructor requerido por JPA
    public Libro() {}

    // Constructor para crear un libro a partir de los datos de la API
    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idiomas().isEmpty() ? "N/A" : datosLibro.idiomas().get(0);
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        String nombreAutor = (autor != null) ? autor.getNombre() : "Autor Desconocido";
        return String.format(
                "---------- LIBRO ----------\n" +
                        "Título: %s\n" +
                        "Autor: %s\n" +
                        "Idioma: %s\n" +
                        "Número de Descargas: %.0f\n" +
                        "---------------------------\n",
                titulo, nombreAutor, idioma, numeroDeDescargas
        );
    }
}

