# challenge-Literalura
Challenge Literalura / Catálogo de Libros de Consola

Descripción del Proyecto

Literalura es una aplicación que corre en consola desarrollada con Java y Spring Boot que funciona como un catálogo de libros interactivo. Permite al usuarios buscar libros a través de la API Gutendex (pública), registra los resultados en la base de datos PostgreSQL y realizar consultas sobre la información almacenada.

El mismo fue desarrollado como parte del Challenge de Programación Back-End de Alura Latam.

Funciones Principales

La aplicación posee un menú con las siguientes opciones:

Buscar libro por título: Consulta la API de Gutendex, muestra el primer resultado y lo registra en la base de datos, evitando duplicados.

Lista los libros registrados: Muestra una lista de los libros guardados en la base de datos.

Lista de autores registrados: Muestra una lista de los autores guardados, junto con sus libros.

Lista autores vivos de un determinado año: Solicita un año al usuario y se despliega una lista de los autores que vivos en la fecha solicitada.

Lista de libros por idioma: Provee al usuario la funcion de buscar y listar todos los libros almacenados en un idioma específico (Español, Inglés, Francés o Portugués).

Tecnologías Utilizadas

Java 17

Spring Boot

Spring Data JPA

Hibernate

PostgreSQL

Maven

API Gutendex

Jackson

Cómo Empezar a usar

Sigue estos pasos para configurar y ejecutar el proyecto en tu entorno local.

requisitos para poder utilizar la APP

Se debe tener instalado lo siguiente para el correcto funcionamiento de la App :

JDK 17 o superior.

Maven 3.8 o superior.

Un servidor de PostgreSQL en ejecución.

De esta manera y con estos requisitos dicha App podra ser utilizada en tu editor de codigo favorito por consola.
