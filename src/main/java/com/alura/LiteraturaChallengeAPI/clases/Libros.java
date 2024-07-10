package com.alura.LiteraturaChallengeAPI.clases;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Double numeroDescargas;

    @OneToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autores> autores;

    public Libros(){}

    public Libros(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.idioma = String.valueOf(datos.idioma());
        this.numeroDescargas = datos.numeroDescargas();
    }

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

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<Autores> autores) {
        autores.forEach(a ->  a.setLibros(this));
        this.autores = autores;
    }

    @Override
    public String toString() {

        return "Libro:" + "\n" +
                  "Titulo=" + titulo + "\n" +
                  "Idioma=" + idioma + "\n" +
                  "Numero de Descargas=" + numeroDescargas +
                  "\n";
    }
}
