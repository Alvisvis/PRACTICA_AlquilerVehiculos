/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dam-29
 */
public class Utilidades {

    //CONSTRUCTOR
    public Utilidades() {
    }

    //METODOS
    /**
     * La matricula sera la correcta si tiene 4 numero y 3 letras
     *
     * @param matricula
     * @return
     */
    public static boolean comprobarMatricula(String matricula) {
        boolean correcto = false;

        Pattern pat = Pattern.compile("^\\d{4}[^aeiouñ]{3}$");

        Matcher mat = pat.matcher(matricula);

        if (mat.find()) {
            correcto = true;
        }
        return correcto;
    }

    /**
     * Para saber si el dni es correcto se necesita 8 numero y para la letra,
     * tienes que dividir la suma de esos numero entre 23 y esa seria la letra
     *
     * @param dni
     * @return
     */
    public static boolean comprobarDni(String dni) {
        if (dni == null || dni.length() != 9) {
            return false;
        }
        boolean correcto = false;
        dni = dni.toUpperCase();

        Pattern patDNI = Pattern.compile("^\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]$");

        Matcher matDNI = patDNI.matcher(dni);

        if (matDNI.find()) {
            correcto = true;
        }

        String numeros = dni.substring(0, 8);
        char letra = dni.charAt(8);

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int num = Integer.parseInt(numeros);
        char letraCorrecta = letras.charAt(num % 23);

        return correcto & letra == letraCorrecta;

    }

    /**
     * El codigo postal es correcto si tiene 5 numero y empiece entre 01 hasta
     * 52
     *
     * @param codigoPostal
     * @return
     */
    public static boolean comprobarCodigoPostal(String codigoPostal) {
        boolean correcto = false;

        Pattern pat = Pattern.compile("^[0-5]\\d{4}$");

        Matcher mat = pat.matcher(codigoPostal);

        if (mat.find()) {
            correcto = true;
        }
        return correcto;

    }
}
