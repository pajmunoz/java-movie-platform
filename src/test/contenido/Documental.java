package test.contenido;

public class Documental extends Contenido {

    private String narrador;

    public Documental(String titulo, int duracion, Genero genero, Idioma idioma, Calidad calidad, String narrador) {
        super(titulo, duracion, genero, idioma, calidad);
        this.narrador = narrador;
    }

    public Documental(String titulo, int duracion, Genero genero, double calificacion, Idioma idioma, Calidad calidad,
            String narrador) {
        super(titulo, duracion, genero, calificacion, idioma, calidad);
        this.narrador = narrador;
    }

    public String getNarrador() {
        return narrador;
    }
}
