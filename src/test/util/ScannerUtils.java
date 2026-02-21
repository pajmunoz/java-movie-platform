package test.util;

import test.contenido.Genero;
import test.contenido.Idioma;
import test.contenido.Calidad;
import java.util.Scanner;

public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in); //static permite definir metodos estaticos que son comunes y compartidos

    public static String capturarTexto(String mensaje) {
        System.out.println( mensaje + ": ");
        return SCANNER.nextLine();
    }

    public static int capturarNumero(String mensaje) {
        System.out.println( mensaje + ": ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Dato no aceptado" + mensaje + ": ");
            SCANNER.next();//descartar input no valido
        }

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double capturarDecimal(String mensaje) {
        System.out.println( mensaje + ": ");

        while (!SCANNER.hasNextDouble()) {
            System.out.println("Dato no aceptado" + mensaje + ": ");
            SCANNER.next();
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }
    public static Genero capturarGenero(String mensaje) {
        while(true){
            System.out.println(mensaje + " Opciones");
            for(Genero genero : Genero.values()){
                System.out.println("* " + genero.name());
            }
            System.out.println("Cual quieres?");
            String entrada = SCANNER.nextLine();

            try {
                return Genero.valueOf(entrada.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Dato no aceptado " + mensaje + ": ");
            }
        }
    }
    public static Idioma capturarIdioma(String mensaje) {
        while(true){
            System.out.println(mensaje + " Opciones");
            for(Idioma idioma : Idioma.values()){
                System.out.println("* " + idioma.name());
            }
            System.out.println("Cual quieres?");
            String entrada = SCANNER.nextLine();

            try {
                return Idioma.valueOf(entrada.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Dato no aceptado " + mensaje + ": ");
            }
        }
    }
    public static Calidad capturarCalidad(String mensaje) {
        while(true){
            System.out.println(mensaje + " Opciones");
            for(Calidad calidad : Calidad.values()){
                System.out.println("* " + calidad.name());
            }
            System.out.println("Cual quieres?");
            String entrada = SCANNER.nextLine();

            try {
                return Calidad.valueOf(entrada.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Dato no aceptado " + mensaje + ": ");
            }
        }
    }
}
