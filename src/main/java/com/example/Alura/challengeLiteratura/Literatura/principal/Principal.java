package com.example.Alura.challengeLiteratura.Literatura.principal;


import com.example.Alura.challengeLiteratura.Literatura.model.*;
import com.example.Alura.challengeLiteratura.Literatura.repository.AutorRepository;
import com.example.Alura.challengeLiteratura.Literatura.repository.LibroRepository;
import com.example.Alura.challengeLiteratura.Literatura.service.ConsumoAPI;
import com.example.Alura.challengeLiteratura.Literatura.service.conviertedatos;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final conviertedatos conversor = new conviertedatos();
    private final String URL_BASE = "https://gutendex.com/books/";

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    *************************************************
                    Elija la opción a través de su número:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    *************************************************
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (Exception e) {
                System.out.println("Opción no válida. Por favor, ingrese un número.");
                teclado.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));

        GutendexResponse datosBusqueda = conversor.obtenerDatos(json, GutendexResponse.class);

        if (datosBusqueda != null && !datosBusqueda.resultados().isEmpty()) {
            DatosLibro primerLibro = datosBusqueda.resultados().get(0);

            // verifica si el libro encontrado tiene autores antes de procesarlo.
            if (primerLibro.autores() == null || primerLibro.autores().isEmpty()) {
                System.out.println("El libro '" + primerLibro.titulo() + "' no tiene un autor registrado y no puede ser procesado.");
                return; // detiene la ejecución del método para este libro.
            }

            Optional<Libro> libroExistente = libroRepository.findByTituloIgnoreCase(primerLibro.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("El libro '" + libroExistente.get().getTitulo() + "' ya está registrado.");
                return;
            }

            DatosAutor datosAutor = primerLibro.autores().get(0);
            Autor autor;
            Optional<Autor> autorExistente = autorRepository.findByNombreIgnoreCase(datosAutor.nombre());

            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                autor = new Autor(datosAutor);
                autorRepository.save(autor);
            }

            Libro libro = new Libro(primerLibro);
            libro.setAutor(autor);
            libroRepository.save(libro);

            System.out.println("Libro registrado exitosamente:");
            System.out.println(libro);

        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("----- Libros Registrados -----");
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("----- Autores Registrados -----");
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Ingresa el año para buscar autores vivos:");
        try {
            var anio = teclado.nextInt();
            teclado.nextLine();
            List<Autor> autoresVivos = autorRepository.findAutoresVivosEnAnio(anio);
            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anio + ".");
            } else {
                System.out.println("----- Autores Vivos en " + anio + " -----");
                autoresVivos.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Por favor, introduce un año válido.");
            teclado.nextLine();
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingresa el idioma para buscar libros (es, en, fr, pt):");
        var idioma = teclado.nextLine().toLowerCase();

        switch (idioma) {
            case "es":
            case "en":
            case "fr":
            case "pt":
                List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);
                if (librosPorIdioma.isEmpty()) {
                    System.out.println("No hay libros registrados en el idioma '" + idioma + "'.");
                } else {
                    System.out.println("----- Libros en " + idioma.toUpperCase() + " -----");
                    librosPorIdioma.forEach(System.out::println);
                }
                break;
            default:
                System.out.println("Idioma no válido. Por favor, elige entre 'es', 'en', 'fr', o 'pt'.");
        }
    }
}
