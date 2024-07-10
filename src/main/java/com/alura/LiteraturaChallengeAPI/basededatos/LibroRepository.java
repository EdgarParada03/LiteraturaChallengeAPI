package com.alura.LiteraturaChallengeAPI.basededatos;

import com.alura.LiteraturaChallengeAPI.clases.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libros,Long> {
    //Aqui se alojan las consultas para la base de datos

    Optional<Libros> findByTituloContainsIgnoreCase(String titulo);

    //List<Libros> findByIdiomaContainsIgnoreCase(List<String> idioma);
    List<Libros> findByIdiomaContainsIgnoreCase(String idioma);


//    @Query("SELECT * FROM libros")
//    List<Libros> listarLibrosBaseDeDatos(String titulo);
}
