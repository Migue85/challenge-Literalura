package com.example.Alura.challengeLiteratura.Literatura.service;

// Interfaz para definir el contrato del conversor de datos
public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
