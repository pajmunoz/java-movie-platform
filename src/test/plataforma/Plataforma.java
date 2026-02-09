package test.plataforma;

import test.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;//relacion es AGEGACION: no dependen de la plataforma

    public Plataforma(String nombre) {
        this.contenido = new ArrayList<>();//inicializado array contenido
        this.nombre = nombre;
    }

    public void agregar(Pelicula elemento) {
        this.contenido.add(elemento);
    }

    public void mostrarTitulos() {
        System.out.println("""
                
                Peliculas en la base de datos:
                """);
        if (this.contenido.isEmpty()) {
            System.out.println("No existen peliculas en la base de datos");
        } else {
            for (Pelicula pelicula : contenido) {//reccorrer elementos de lista con FOR
                System.out.println("-"+pelicula.getTitulo());
            }
        }
    }

    public void eliminar(Pelicula pelicula) {
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarPorTitulo(String titulo) {
        for (Pelicula pelicula : contenido) {
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                return pelicula;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
