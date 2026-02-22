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
import test.contenido.Contenido;
import test.contenido.Documental;

public class FileUtils {
    public static final String SEPARADOR = "|";
    public static final String NOMBRE_ARCHIVO = "contenido.txt";

    public static void escribirContenido(Contenido contenido) {
        String linea = String.join(SEPARADOR, contenido.getTitulo(), String.valueOf(contenido.getDuracion()),
                contenido.getGenero().name(), String.valueOf(contenido.getCalificacion()),
                contenido.getIdioma().name(), contenido.getCalidad().name(),
                contenido.getFechaEstreno().toString());
        String lineaFinal;

        if (contenido instanceof Documental documental) { // instanceof = pregunta si es de tipo documental
            lineaFinal = "DOCUMENTAL" + SEPARADOR + linea + SEPARADOR + documental.getNarrador();
        } else {
            lineaFinal = "PELICULA" + SEPARADOR + linea;
        }
        try {

            Files.writeString(Paths.get(NOMBRE_ARCHIVO),
                    lineaFinal + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error al escribir el contenido: " + e.getMessage());
        }

    }

    public static List<Contenido> leerContenido() {
        List<Contenido> contenidoDesdeArchivo = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARADOR);
                String tipoContenido = datos[0];
                if (("PELICULA".equals(tipoContenido) && datos.length == 8)
                        || ("DOCUMENTAL".equals(tipoContenido) && datos.length == 9)) {
                    String titulo = datos[1];
                    int duracion = Integer.parseInt(datos[2]);
                    Genero genero = Genero.valueOf(datos[3].toUpperCase());
                    double calificacion = datos[4].isBlank() ? 0.0 : Double.parseDouble(datos[4]);
                    Idioma idioma = Idioma.valueOf(datos[5].toUpperCase());
                    Calidad calidad = Calidad.valueOf(datos[6].toUpperCase());
                    LocalDate fechaLanzamiento = LocalDate.parse(datos[7]);

                    Contenido contenido;
                    if ("PELICULA".equals(tipoContenido)) {
                        contenido = new Pelicula(titulo, duracion, genero, calificacion, idioma, calidad);
                    } else {
                        String narrador = datos[8]; // Narrator is now at index 8
                        contenido = new Documental(titulo, duracion, genero, calificacion, idioma, calidad, narrador);
                    }
                    contenido.setFechaEstreno(fechaLanzamiento);
                    contenidoDesdeArchivo.add(contenido);

                }
            });
        } catch (IOException e) {
            System.out.println("Error al cargar las peliculas: " + e.getMessage());
        }
        return contenidoDesdeArchivo;
    }
}
