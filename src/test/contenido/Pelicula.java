package test.contenido;

import java.time.LocalDate;

public class Pelicula {
    private String titulo;
    private String descripcion;
    private int duracion;
    private Genero genero;
    private LocalDate fechaEstreno;
    private double calificacion;
    private Idioma idioma;
    private Calidad calidad;
    private boolean disponible;

    // constructor, this hacer referencia a los parametros de mi CLase
    public Pelicula(String titulo, int duracion, Genero genero, Idioma idioma, Calidad calidad) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.idioma = idioma;
        this.calidad = calidad;
        this.fechaEstreno = LocalDate.now();
        this.disponible = true;

    }

    public Pelicula(String titulo, int duracion, Genero genero, double calificacion, Idioma idioma, Calidad calidad) {
        this(titulo, duracion, genero, idioma, calidad);
        this.calificacion = calificacion;
        this.idioma = idioma;
        this.calidad = calidad;
    }

    public Pelicula(String titulo, int duracion, Genero genero, double calificacion, Idioma idioma, Calidad calidad,
            LocalDate fechaEstreno) {
        this(titulo, duracion, genero, calificacion, idioma, calidad);
        this.fechaEstreno = fechaEstreno;
    }

    public Pelicula(String titulo, int duracion, Genero genero, double calificacion, LocalDate fechaEstreno) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.calificacion = calificacion;
        this.fechaEstreno = fechaEstreno;
        this.disponible = true;
    }

    public void reproducir() {
        System.out.println("reproduciendo " + titulo);
    }

    public String obtenerFichaTecnica() {
        return titulo + " (" + fechaEstreno.getYear() + ")\n" +
                "Género: " + genero + "\n" +
                "Calificación: " + calificacion + "/5\n" +
                "Idioma: " + idioma + "\n" +
                "Calidad: " + calidad + "\n";
    }

    public void calificar(double calificacion) {
        if (calificacion >= 0 && calificacion <= 5) {
            this.calificacion = calificacion;
        }

    }

    public boolean esPopular() {
        return calificacion >= 4;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Genero getGenero() {
        return genero;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public Calidad getCalidad() {
        return calidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
