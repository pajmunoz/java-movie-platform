package test.contenido;

public class Pelicula extends Contenido {
    public Pelicula(String titulo, int duracion, Genero genero, double calificacion, Idioma idioma, Calidad calidad) {
        super(titulo, duracion, genero, calificacion, idioma, calidad);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo la pelicula " + getTitulo());
    }
}