package test.contenido;

public record ResumenContenido(String titulo, int duracion, Genero genero) {// record es una clase inmutable
    // no se puede modificar el contenido
    // es una clase ligera para almacenar datos
    // representar elementos inmutables de manera concisa
}