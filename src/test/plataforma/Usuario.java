package test.plataforma;

import test.contenido.Contenido;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {
    public String nombre;
    public String apellido;
    public String email;
    public String username;
    public LocalDateTime fechaRegistro;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = LocalDateTime.of(2025, 12, 24, 17, 15, 14);
    }

    public void ver(Contenido pelicula) {
        System.out.println(nombre + " esta viendo..." + pelicula.getTitulo());
        pelicula.reproducir();
    }
}
