package test;

import test.contenido.Genero;
import test.contenido.Contenido;
import test.contenido.Documental;
import test.contenido.ResumenContenido;
import test.excepcion.PeliculaExistenteException;
import test.plataforma.Plataforma;
import test.util.FileUtils;
import test.util.ScannerUtils;
import test.contenido.Idioma;
import test.contenido.Pelicula;
import test.contenido.Calidad;
import java.util.List;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "App play";
    public static final String VERSION = "1.0.0"; // constantes deben ser MAYUSCULAS, "final" = palabra reservada para
                                                  // inmutables
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int VER_MUY_POPULARES = 6;
    public static final int VER_DURACION = 7;
    public static final int REPRODUCIR = 8;
    public static final int ELIMINAR = 9;
    public static final int SALIR = 10;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + VERSION);
        cargarPeliculas(plataforma);
        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de entretenimiento \n");

        while (true) {
            int opcionelegida = ScannerUtils.capturarNumero("""

                    Ingrese una opcion:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver Populares
                    6. Ver Muy Populares
                    7. Ver por duracion
                    8. Reproducir
                    9. Eliminar
                    10. Salir
                    """);

            System.out.println("opcion elegida: " + opcionelegida);

            switch (opcionelegida) {
                case AGREGAR -> { // -> flecha operacion lambda
                    int tipoDeContenido = ScannerUtils
                            .capturarNumero("Que tipo de contenido deseas agregar? \n 1. Pelicula \n 2. Documental");
                    String nombre = ScannerUtils.capturarTexto("Cual es el nombre del contenido?");
                    Genero genero = ScannerUtils.capturarGenero("Cual es el genero del contenido?");
                    int duracion = ScannerUtils.capturarNumero("Cual es el duracion del contenido?");
                    double calificacion = ScannerUtils.capturarDecimal("Cual es el calificacion del contenido?");
                    Idioma idioma = ScannerUtils.capturarIdioma("Cual es el idioma del contenido?");
                    Calidad calidad = ScannerUtils.capturarCalidad("Cual es la calidad del contenido?");
                    try {
                        if (tipoDeContenido == 1) {
                            plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion, idioma, calidad));
                        } else {
                            String narrador = ScannerUtils.capturarTexto("Cual es el narrador del documental?");
                            plataforma.agregar(
                                    new Documental(nombre, duracion, genero, calificacion, idioma, calidad, narrador));
                        }
                    } catch (PeliculaExistenteException e) {
                        System.out.println(e.getMessage());// controlamos si existe la pelicula y no la agregamos
                    }
                }
                case MOSTRAR_TODO -> {
                    /*
                     * List<String> titulos = plataforma.getTitulos();
                     * titulos.forEach(System.out::println); //titulo -> System.out.println(titulo)
                     * <---- lo mismo (metodo de referencia)
                     */
                    List<ResumenContenido> contenidosResumidos = plataforma.getResumenes();
                    contenidosResumidos.forEach(resumen -> System.out.println(resumen.toString()));
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Cual es el nombre del contenido?");
                    Contenido pelicula = plataforma.buscarPorTitulo(nombreBuscado);
                    if (pelicula != null) {
                        System.out.println("\n" + "Ficha tecnica de " + nombreBuscado + "\n");
                        System.out.println(pelicula.obtenerFichaTecnica());
                    } else {
                        System.out.println("No existe el contenido para " + nombreBuscado);
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Cual es el genero del contenido?");
                    List<Contenido> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);

                    if (contenidoPorGenero != null) {
                        System.out.println("\n" + "Se encontraron " + contenidoPorGenero.size()
                                + " peliculas para el genero de " + generoBuscado + "\n");
                        contenidoPorGenero
                                .forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                    } else {
                        System.out.println("No existe el genero para " + generoBuscado);
                    }
                }
                case VER_POPULARES -> {
                    List<Contenido> contenidoPopulares = plataforma.getPopulares(5);
                    System.out.println("Peliculas mas populares \n");
                    contenidoPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }

                case VER_MUY_POPULARES -> {
                    List<Contenido> muyPulares = plataforma.getMuyPopulares();
                    System.out.println("Peliculas mas muy populares \n");
                    muyPulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case VER_DURACION -> {
                    int buscarDuracion = ScannerUtils.capturarNumero("""
                            1. Ver de mayor duracion
                            2. Ver de menor duracion""");
                    switch (buscarDuracion) {
                        case 1 -> {
                            Contenido peliculaLarga = plataforma.getMasLarga();
                            System.out.println(peliculaLarga.obtenerFichaTecnica());
                        }
                        case 2 -> {
                            Contenido peliculaCorta = plataforma.getMasCorta();
                            System.out.println(peliculaCorta.obtenerFichaTecnica());
                        }

                    }
                }
                case REPRODUCIR -> {
                    String nombreReproducir = ScannerUtils.capturarTexto("Que pelicula quiere reproducir?");
                    Contenido pelicula = plataforma.buscarPorTitulo(nombreReproducir);
                    if (pelicula != null) {
                        plataforma.reproducir(pelicula);
                    } else {
                        System.out.println("No existe el contenido para " + nombreReproducir);
                    }
                }
                case ELIMINAR -> {
                    String nombreEliminar = ScannerUtils.capturarTexto("Que pelicula quiere eliminar?");
                    Contenido pelicula = plataforma.buscarPorTitulo(nombreEliminar);
                    if (pelicula != null) {
                        plataforma.eliminar(pelicula);
                        System.out.println("la pelicula " + nombreEliminar + " ha sido eliminado");
                    } else {
                        System.out.println("No existe el contenido para " + nombreEliminar);
                    }

                }
                case SALIR -> System.exit(0);// salir del ciclo

            }
        }

        /*
         * String nombre =
         * ScannerUtils.capturarTexto("Cual es el nombre del contenido?");
         * String genero =
         * ScannerUtils.capturarTexto("Cual es el genero del contenido?");
         * int duracion =
         * ScannerUtils.capturarNumero("Cual es el duracion del contenido?");
         * double calificacion =
         * ScannerUtils.capturarDecimal("Cual es el calificacion del contenido?");
         * 
         * Pelicula pelicula = new Pelicula(nombre, duracion, genero,
         * calificacion);//instanciar objeto, instancia de la clase pelicula, e
         * inicializacoin
         * Pelicula pelicula2 = new Pelicula("f1", 123, "accion");
         * 
         * pelicula.calificar(calificacion);
         * plataforma.agregar(pelicula);
         * plataforma.agregar(pelicula2);
         * 
         * 
         * System.out.println("numero de elementos en plataforma " +
         * plataforma.getContenido().size());
         * plataforma.eliminar(pelicula2);//elimina un item de la lista
         * 
         * plataforma.mostrarTitulos();
         * 
         * Usuario usuario = new Usuario("Juan", "juanPerez@gmail.com");
         * usuario.nombre = "Juan";
         * usuario.fechaRegistro = LocalDateTime.of(2025,12,24,17,15,14);
         * usuario.ver(pelicula);
         */

    }

    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.getContenido().addAll(FileUtils.leerContenido());

        /*
         * plataforma.agregar(new Pelicula("Shrek", 90, Genero.ANIMADA, 2,
         * Idioma.ESPANOL, Calidad.FULL_HD));
         * plataforma.agregar(new Pelicula("Batman", 120, Genero.ACCION, 3,
         * Idioma.INGLES, Calidad.ULTRA_HD));
         * plataforma.agregar(new Pelicula("Superman", 200, Genero.ACCION, 4,
         * Idioma.PORTUGUES, Calidad.FULL_HD));
         * plataforma.agregar(new Pelicula("Spiderman", 210, Genero.ACCION, 5,
         * Idioma.FRANCES, Calidad.ULTRA_HD));
         * plataforma.agregar(new Pelicula("Inception", 160, Genero.SCIFI, 2,
         * Idioma.ESPANOL, Calidad.FULL_HD));
         * plataforma.agregar(new Pelicula("Coco", 180, Genero.ANIMADA, 1,
         * Idioma.INGLES, Calidad.ULTRA_HD));
         * plataforma.agregar(new Pelicula("Interestellar", 200, Genero.SCIFI, 5,
         * Idioma.ESPANOL, Calidad.FULL_HD));
         * plataforma.agregar(new Pelicula("Toy Story", 140, Genero.ANIMADA, 4,
         * Idioma.INGLES, Calidad.ULTRA_HD));
         * plataforma.agregar(new Pelicula("Avengers", 130, Genero.ACCION, 3,
         * Idioma.PORTUGUES, Calidad.FULL_HD));
         * plataforma.agregar(new Pelicula("Avatar", 215, Genero.ANIMADA, 2,
         * Idioma.FRANCES, Calidad.ULTRA_HD));
         */

    }
}
