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
            /*for (Pelicula pelicula : contenido) {//reccorrer elementos de lista con FOR
                System.out.println("-"+pelicula.getTitulo());
            }*/
            contenido.forEach(pelicula -> System.out.println("-" + pelicula.getTitulo())); //usando forEach y lambda (->)
        }
    }

    public void eliminar(Pelicula pelicula) {
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarPorTitulo(String titulo) {
        /*for (Pelicula pelicula : contenido) {
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                return pelicula;
            }
        }*/

        return contenido.stream(). //Stream permite tomar elementos y aplicar condicionales a cada uno
                filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))//si el valor ingresado corresponde a uno de la lista
                .findFirst()//tomamos el primer valor de la lista
                .orElse(null);//si no coincide retornar null

        /*return null;*/
    }
    public List<Pelicula> buscarPorGenero(String genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero()
                .equalsIgnoreCase(genero))
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
