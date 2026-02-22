package test.contenido;

public class Documental extends Contenido {
    private String narrador;

    public Documental(String titulo, int duracion, Genero genero, double calificacion, Idioma idioma, Calidad calidad) {
        super(titulo, duracion, genero, calificacion, idioma, calidad);
    }

    public Documental(String titulo, int duracion, Genero genero, double calificacion, Idioma idioma, Calidad calidad,

            String narrador) {
        super(titulo, duracion, genero, calificacion, idioma, calidad);
        this.narrador = narrador;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo el documento " + getTitulo() + " narrado por " + getNarrador());
    }

    public String getNarrador() {
        return narrador;
    }
}