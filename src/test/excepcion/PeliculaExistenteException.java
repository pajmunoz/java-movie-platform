package test.excepcion;

public class PeliculaExistenteException extends RuntimeException {
    // crear constructor
    public PeliculaExistenteException(String mensaje) {
        super("El contenido " + mensaje + " ya existe");// super llama al constructor de la clase padre RuntimeException
    }
}
