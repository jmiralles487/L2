package pe.gob.mtpe.rios.infraestructura.configuraciones;

import java.security.SecureRandom;
import java.util.Random;

public class GeneradorPassword {

    private static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String LETRAS_MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMEROS = "0123456789";
    private static final String SIGNOS = "@#$%";

    public String generarContraseña(int longitud) {
        if (longitud < 8) {
            throw new IllegalArgumentException("La longitud de la contraseña debe ser de al menos 8 caracteres.");
        }

        String todasLasCaracteres = LETRAS_MINUSCULAS + LETRAS_MAYUSCULAS + NUMEROS + SIGNOS;
        char[] contraseña = new char[longitud];

        // Asegurarse de que la contraseña tenga al menos un número, una letra mayúscula, una letra minúscula y un signo.
        contraseña[0] = obtenerCaracterAleatorio(NUMEROS);
        contraseña[1] = obtenerCaracterAleatorio(LETRAS_MAYUSCULAS);
        contraseña[2] = obtenerCaracterAleatorio(LETRAS_MINUSCULAS);
        contraseña[3] = obtenerCaracterAleatorio(SIGNOS);

        // Rellenar el resto de la contraseña con caracteres aleatorios.
        for (int i = 4; i < longitud; i++) {
            contraseña[i] = obtenerCaracterAleatorio(todasLasCaracteres);
        }

        // Mezclar la contraseña para evitar que los primeros caracteres siempre sean un número, una letra mayúscula, una letra minúscula y un signo.
        mezclarContraseña(contraseña);

        return new String(contraseña);
    }

    private char obtenerCaracterAleatorio(String caracteres) {
        SecureRandom random = new SecureRandom();
        int indice = random.nextInt(caracteres.length());
        return caracteres.charAt(indice);
    }

    private void mezclarContraseña(char[] contrasena) {
        Random random = new Random();
        for (int i = contrasena.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = contrasena[i];
            contrasena[i] = contrasena[j];
            contrasena[j] = temp;
        }
    }
}