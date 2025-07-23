package com.example.Alura.challengeLiteratura.Literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

// @JsonIgnoreProperties(ignoreUnknown = true) es CRUCIAL.
// Le dice a Jackson que ignore cualquier campo en el JSON que no reconozca
// (como "count", "next", "previous" en la respuesta principal).
@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexResponse(
        @JsonAlias("results") List<DatosLibro> resultados
) {}
