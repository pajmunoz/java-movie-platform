package test.plataforma;

import test.contenido.Genero;
import test.contenido.Pelicula;
import test.contenido.ResumenContenido;
import test.excepcion.PeliculaExistenteException;
import test.util.FileUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;// relacion es AGEGACION: no dependen de la plataforma
    private Map<Pelicula, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();// inicializado array contenido
        this.visualizaciones = new HashMap<>();
    }

    public void agregar(Pelicula elemento) {
        Pelicula contenido = this.buscarPorTitulo(elemento.getTitulo());
        if (contenido != null) {
            throw new PeliculaExistenteException(elemento.getTitulo()); // throw lanza la excepcion
        }
        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }

    public void reproducir(Pelicula contenido) {
        int conteo = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " se esta reproduciendo " + conteo + " veces");
        this.contarVisualizaciones(contenido);
        contenido.reproducir();
    }

    private void contarVisualizaciones(Pelicula contenido) {
        int conteo = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteo + 1);
    }

    public List<String> getTitulos() {
        return contenido.stream().map(Pelicula::getTitulo) // contenido -> contenido.getTitulo() <---lo mismo
                .toList();

        /*
         * System.out.println("""
         * 
         * Peliculas en la base de datos:
         * """);
         * if (this.contenido.isEmpty()) {
         * System.out.println("No existen peliculas en la base de datos");
         * } else {
         * for (Pelicula pelicula : contenido) {//reccorrer elementos de lista con FOR
         * System.out.println("-"+pelicula.getTitulo());
         * }
         * contenido.forEach(pelicula -> System.out.println("-" +
         * pelicula.getTitulo())); //usando forEach y lambda (->)
         * }
         */

    }

    public List<ResumenContenido> getResumenes() {
        return contenido.stream()
                .map(contenido -> new ResumenContenido(contenido.getTitulo(), contenido.getDuracion(),
                        contenido.getGenero()))
                .toList();
    }

    public void eliminar(Pelicula pelicula) {
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarPorTitulo(String titulo) {
        /*
         * for (Pelicula pelicula : contenido) {
         * if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
         * return pelicula;
         * }
         * }
         */

        return contenido.stream(). // Stream permite tomar elementos y aplicar condicionales a cada uno
                filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))// si el valor ingresado corresponde
                                                                                   // a uno de la lista
                .findFirst()// tomamos el primer valor de la lista
                .orElse(null);// si no coincide retornar null

        /* return null; */
    }

    public List<Pelicula> getMuyPopulares() {
        return contenido.stream().filter(pelicula -> pelicula.getCalificacion() > 4).toList();
    }

    public Pelicula getMasLarga() {
        return contenido.stream().max(Comparator.comparingInt(Pelicula::getDuracion))
                .orElse(null);
    }

    public Pelicula getMasCorta() {
        return contenido.stream().min(Comparator.comparingInt(Pelicula::getDuracion))
                .orElse(null);
    }

    public List<Pelicula> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero()
                        .equals(genero))
                .toList();
    }

    public List<Pelicula> getPopulares(int cantidad) {
        return contenido.stream().sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                .limit(cantidad).toList();
    }

    public int getDuracionTotal() {
        return contenido.stream().mapToInt(Pelicula::getDuracion).sum();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
