package com.alura.LiteraturaChallengeAPI.clases;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String nombreAutor,
        @JsonAlias("birth_year")LocalDate fechaNacimiento,
        @JsonAlias("death_year") LocalDate fechaMuerte
        ) {
}
