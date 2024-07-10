package com.alura.LiteraturaChallengeAPI.principal;

import com.alura.LiteraturaChallengeAPI.basededatos.LibroRepository;
import com.alura.LiteraturaChallengeAPI.clases.*;
import com.alura.LiteraturaChallengeAPI.service.ConsumoAPI;
import com.alura.LiteraturaChallengeAPI.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Optional<Libros> libroBuscado;
    private LibroRepository repositorio;


    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu= """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar libros por idioma
                    
                    9 - Guardar un libro en la base de Datos
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando aplicacion");
                    break;
                case 9:
                    buscarLibroWeb();
                    break;
                default:
                    System.out.println("Opcion Invalida, seleccione una opcion correcta");
            }
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Escribe el nombre del idioma del libro (en / es): ");
        var idiomaLibro = teclado.nextLine();
        List<Libros> librosPorIdioma = repositorio.findByIdiomaContainsIgnoreCase(idiomaLibro);
        if (librosPorIdioma.isEmpty()){
            System.out.println("Escribe otro idioma");
        }else {
            System.out.println("""
                    Los libros buscado son:
                    """ +
                    librosPorIdioma + "\n");
        }
    }

    private void listarLibros() {
        List<Libros> librosListados = repositorio.findAll();
        librosListados.forEach(System.out::println);
    }

    private void buscarLibroWeb() {
        DatosLibro datos = getDatosSerie();
        Libros libros = new Libros(datos);
        repositorio.save(libros);
        System.out.println(datos);
    }

    private DatosLibro getDatosSerie() {
            System.out.println("Escribe el nombre de la serie que deseas buscar");
            var nombreLibro = teclado.nextLine();
            var json = consumoAPI.obtenerDatos( URL_BASE+ "?search=" +  nombreLibro.replace(" ", "+"));
            System.out.println(json);
            var datos = convierteDatos.obtenerDatos(json, Datos.class);
            // Obtén el primer libro (puedes ajustar esto según tus necesidades)
            DatosLibro primerLibro = datos.libros().get(0);

            // Crea un nuevo objeto DatosLibro con los datos del primer libro
            DatosLibro libroTransformado = new DatosLibro(
                    primerLibro.titulo(),
                    primerLibro.idioma(),
                    primerLibro.numeroDescargas());

            return libroTransformado;
    }



    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el titulo del libro a buscar: ");
        var tituloLibro = teclado.nextLine();

        //Proceso de busqueda
        libroBuscado = repositorio.findByTituloContainsIgnoreCase(tituloLibro);
        if (libroBuscado.isPresent()){
            System.out.println("""
                    El libro buscado es:
                    """ +
                    libroBuscado.get());
        }else {
            System.out.println("Escribe otro libro");
        }


    }
}
