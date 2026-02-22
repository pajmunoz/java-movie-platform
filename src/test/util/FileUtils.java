package test.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import test.contenido.Calidad;
import test.contenido.Genero;
import test.contenido.Idioma;
import test.contenido.Pelicula;

public class FileUtils {
    public static final String SEPARADOR = "|";
    public static final String NOMBRE_ARCHIVO = "contenido.txt";

    public static void escribirContenido(Pelicula contenido) {
        String linea = String.join(SEPARADOR, contenido.getTitulo(), String.valueOf(contenido.getDuracion()),
                contenido.getGenero().name(), String.valueOf(contenido.getCalificacion()),
                contenido.getIdioma().name(), contenido.getCalidad().name(),
                contenido.getFechaEstreno().toString());
        try {

            Files.writeString(Paths.get(NOMBRE_ARCHIVO),
                    linea + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error al escribir el contenido: " + e.getMessage());
        }

    }

    public static List<Pelicula> leerContenido() {
        List<Pelicula> contenidoDesdeArchivo = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARADOR);
                if (datos.length == 7) {
                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    Genero genero = Genero.valueOf(datos[2].toUpperCase());
                    double calificacion = datos[3].isBlank() ? 0.0 : Double.parseDouble(datos[3]);
                    Idioma idioma = Idioma.valueOf(datos[4].toUpperCase());
                    Calidad calidad = Calidad.valueOf(datos[5].toUpperCase());
                    LocalDate fechaLanzamiento = LocalDate.parse(datos[6]);

                    Pelicula pelicula = new Pelicula(titulo, duracion, genero, calificacion, idioma, calidad,
                            fechaLanzamiento);
                    pelicula.setFechaEstreno(fechaLanzamiento);

                    contenidoDesdeArchivo.add(pelicula);
                }
            });
        } catch (IOException e) {
            System.out.println("Error al cargar las peliculas: " + e.getMessage());
        }
        return contenidoDesdeArchivo;
    }
}
