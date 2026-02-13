/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author dam-29
 */
public class ES {

    //LEER ENTEROS
    /**
     * Devuelve el numero introducido por teclado
     *
     * @return Entero valor
     */
    public static int leerEntero() {
        int valor;
        boolean correcto = false;
        do {
            try {
                valor = Integer.parseInt("Escribe un enetero");
                correcto = true;
            } catch (NumberFormatException e) {
                System.out.println("Error entrada: formato int");
                valor = 0;
            }
        } while (!correcto);
        return valor;
    }

    /**
     * Recibe una cadena y devuelve el numero introducido por teclado
     *
     * @param cadena
     * @return Entero valor
     */
    public static int leerEntero(String cadena) {
        int valor;
        boolean correcto = false;
        do {
            try {
                valor = Integer.parseInt(leerCadena(cadena));
                correcto = true;
            } catch (NumberFormatException e) {
                System.out.println("Error entrada: formato int");
                valor = 0;
            }
        } while (!correcto);
        return valor;
    }

    /**
     * Recibe una cadena y un numero entero minimo y lee el numero introducido
     * si es mayor al numero recibido
     *
     * @param cadena
     * @param num
     * @return
     */
    public static int leerEntero(String cadena, int min) {
        int valor;
        boolean correcto = false;
        do {
            try {
                valor = Integer.parseInt(leerCadena(cadena));
                if (valor < min) {
                    correcto = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error entrada: formato int");
                valor = 0;
            }
        } while (!correcto);
        return valor;
    }

    //LEER CADENA
    /**
     * lee una cadena y devuelve lo que se introduce por teclado
     *
     * @param cadena
     * @return
     */
    public static String leerCadena(String cadena) {
        Scanner teclado = new Scanner(System.in);
        System.out.println(cadena);
        return teclado.nextLine();
    }

    //LEER REAL
    /**
     * Recibe una cadena y un numero float minimo y lee el numero introducido si
     * es mayor al numero recibido
     *
     * @param cadena
     * @param num
     * @return
     */
    public static float leerReal(String cadena, float min) {
        float valor;
        boolean correcto = false;
        do {
            try {

                valor = Float.parseFloat(leerCadena(cadena));
                if (valor < min) {
                    correcto = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error entrada: formato float");
                valor = 0;
            }
        } while (!correcto);
        return valor;
    }

    //ESCRIBIR
    /**
     * Muestra en pantalla la cadena sin salto de linea
     *
     * @param cadena
     */
    public static void escribir(String cadena) {
        System.out.print(cadena);
    }

    /**
     * Muestra en pantalla la cadena con salto de linea
     *
     * @param cadena
     */
    public static void escribirLn(String cadena) {
        System.out.println(cadena);
    }

    //METODOS SOBRANTES
    public static boolean leerBoolean(String cadena) {
        boolean respuesta = false;
        Scanner sc = new Scanner(System.in);
        boolean correcto = false;
        do {
            try {
                System.out.println(cadena);
                cadena = sc.nextLine();

                if (cadena.toUpperCase().equals("Si") || cadena.toUpperCase().equals("S")) {
                    correcto = true;
                    respuesta = true;
                } else if (cadena.toUpperCase().equals("No") || cadena.toUpperCase().equals("N")) {
                    correcto = false;
                    respuesta = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato introducido no es corrcto");
                System.out.println("Introduce un Si, si o No n");
            }
        } while (!correcto);
        return respuesta;
    }

    public static byte leerByte(String cadena) {
        byte valor;
        boolean correcto = false;
        do {
            try {
                valor = Byte.parseByte(leerCadena(cadena));
                correcto = true;
            } catch (NumberFormatException e) {
                System.out.println("Error entrada: formato int");
                valor = 0;
            }
        } while (!correcto);
        return valor;
    }

    public static boolean escribirFichero(String ruta, String datos, boolean _sobreEscribir) {
        boolean correcto = false;

        File archivo = new File(ruta);
        FileWriter fichero = null;

        try {
            fichero = new FileWriter(archivo, !_sobreEscribir);

            System.out.println("Guardando información............");

            fichero.write(datos);
            fichero.close();

            System.out.println("Información guardada");
            correcto = true;

        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");

        } catch (IOException e) {
            System.out.println("Mensaje:  " + e.getMessage());
        }

        return correcto;
    }

    public static boolean leerFichero(String ruta) {
        File fichero = new File(ruta);
        Scanner sc = null;
        boolean correcto = false;

        try {
            System.out.println("Leyendo el contenido del fichero..........\n\n");
            sc = new Scanner(fichero);

            // leer línea a linea el fichero
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                System.out.println(linea);
            }

            System.out.println("\n --->>   Lectura completada");
            correcto = true;

        } catch (Exception e) {
            System.out.println("Mensaje:  " + e.getMessage());
        } finally {
            try {
                if (sc != null) {
                    sc.close();
                }
            } catch (Exception e2) {
                System.out.println("Mensaje fichero:   " + e2.getMessage());
            }
        }
        return correcto;
    }
}
