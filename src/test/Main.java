package test;

import test.contenido.Pelicula;
import test.plataforma.Plataforma;
import test.plataforma.Usuario;
import test.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "App play";
    public static final String VERSION = "1.0.0"; //constantes deben ser MAYUSCULAS, "final" = palabra reservada para inmutables
    public static final int SALIR = 5;
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int ELIMINAR = 4;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + VERSION);
        cargarPeliculas(plataforma);

        while (true) {
            int opcionelegida = ScannerUtils.capturarNumero("""
                    
                    Ingrese una opcion:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Eliminar
                    5. Salir
                    """);

            System.out.println("opcion elegida: " + opcionelegida);


            switch (opcionelegida) {
                case AGREGAR -> { //-> flecha operacion lambda
                    String nombre = ScannerUtils.capturarTexto("Cual es el nombre del contenido?");
                    String genero = ScannerUtils.capturarTexto("Cual es el genero del contenido?");
                    int duracion = ScannerUtils.capturarNumero("Cual es el duracion del contenido?");
                    double calificacion = ScannerUtils.capturarDecimal("Cual es el calificacion del contenido?");

                    plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));
                }
                case MOSTRAR_TODO -> {
                    plataforma.mostrarTitulos();
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Cual es el nombre del contenido?");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreBuscado);
                    if (pelicula != null) {
                        System.out.println("\n" + "Ficha tecnica de " + nombreBuscado + "\n");
                        System.out.println(pelicula.obtenerFichaTecnica());
                    } else {
                        System.out.println("No existe el contenido para " + nombreBuscado);
                    }
                }
                case ELIMINAR -> {
                    String nombreEliminar = ScannerUtils.capturarTexto("Que pelicula quiere eliminar?");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreEliminar);
                    if (pelicula != null) {
                        plataforma.eliminar(pelicula);
                        System.out.println("la pelicula " + nombreEliminar + " ha sido eliminado");
                    } else {
                        System.out.println("No existe el contenido para " + nombreEliminar);
                    }

                }
                case SALIR -> {
                    System.exit(0);//salir del ciclo
                }
            }
        }

        /*String nombre = ScannerUtils.capturarTexto("Cual es el nombre del contenido?");
        String genero = ScannerUtils.capturarTexto("Cual es el genero del contenido?");
        int duracion = ScannerUtils.capturarNumero("Cual es el duracion del contenido?");
        double calificacion = ScannerUtils.capturarDecimal("Cual es el calificacion del contenido?");

        Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);//instanciar objeto, instancia de la clase pelicula, e inicializacoin
        Pelicula pelicula2 = new Pelicula("f1", 123, "accion");

        pelicula.calificar(calificacion);
        plataforma.agregar(pelicula);
        plataforma.agregar(pelicula2);


        System.out.println("numero de elementos en plataforma " + plataforma.getContenido().size());
        plataforma.eliminar(pelicula2);//elimina un item de la lista

        plataforma.mostrarTitulos();

        Usuario usuario = new Usuario("Juan", "juanPerez@gmail.com");
        usuario.nombre = "Juan";
        usuario.fechaRegistro = LocalDateTime.of(2025,12,24,17,15,14);
        usuario.ver(pelicula);*/

    }

    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregar(new Pelicula("Shrek", 90, "Animacion", 2));
        plataforma.agregar(new Pelicula("Batman", 120, "Accion", 3));
        plataforma.agregar(new Pelicula("Superman", 200, "Accion", 4));
        plataforma.agregar(new Pelicula("Spiderman", 210, "Accion", 5));
        plataforma.agregar(new Pelicula("Inception", 160, "SCIFI", 2));
        plataforma.agregar(new Pelicula("Coco", 180, "Animacion", 1));
        plataforma.agregar(new Pelicula("Interestellar", 200, "SCIFI", 5));
        plataforma.agregar(new Pelicula("Toy Story", 140, "Animacion", 4));
        plataforma.agregar(new Pelicula("Avengers", 130, "Accion", 3));
        plataforma.agregar(new Pelicula("Avatar", 215, "Animacion", 2));

    }
}
