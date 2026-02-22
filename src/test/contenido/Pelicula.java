package test.contenido;

public class Pelicula extends Contenido {
    public Pelicula(String titulo, int duracion, Genero genero, Idioma idioma, Calidad calidad) {
        super(titulo, duracion, genero, idioma, calidad);
    }

    public Pelicula(String titulo, int duracion, Genero genero, double calificacion, Idioma idioma, Calidad calidad) {
        super(titulo, duracion, genero, calificacion, idioma, calidad);
    }

}
