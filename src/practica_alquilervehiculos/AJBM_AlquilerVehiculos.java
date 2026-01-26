/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_alquilervehiculos;

import practica_alquilervehiculos.Cliente;
import utiles.ES;
import utiles.Utilidades;

/**
 *
 * @author dam1
 */
public class AJBM_AlquilerVehiculos {

    /**
     * @param args the command line arguments
     */
    //ATRIBUTOS 
    private final int MAX_VEHICULOS = 50;
    private final int MIN_VEHICULOS = 50;
    private final int MAX_ALQUILERES = 50;

    private Vehiculo[] vehiculos;
    private Cliente[] clientes;
    private Alquiler[] alquileres;

    public static void main(String[] args) {

    }

    //METODOS AÑADIR
    private static void anadirCliente(Cliente c) {
        String dni = ES.leerCadena("Cual es el DNI o NIE del cliente");
        while (!Utilidades.comprobarDni(dni) || !dni.equals("1")) {
            ES.escribir("Este DNI o NIE no es valido, vuelva a introducirlo");
            ES.escribir("O escribi 1 para salir");
            dni = ES.leerCadena("Cual es el DNI del cliente?");
        }
        String nombre = ES.leerCadena("Introduce el nombre del Cliente");

        String direccion = ES.leerCadena("Introduce la dirección del cliente");

        String localidad = ES.leerCadena("Introduce la localidad del cliente");

        String codigoPostal = ES.leerCadena("Introduce el codigo postal del cliente");
        while (!Utilidades.comprobarCodigoPostal(codigoPostal) || !codigoPostal.equals("1")) {
            ES.escribir("Este codigo postal no es valido, vuelva a introducirlo");
            ES.escribir("O escribi 1 para salir");
            codigoPostal = ES.leerCadena("Introduce el codigo postal del cliente");
        }

        c = new Cliente(dni, nombre, direccion, localidad, codigoPostal);

    }

    private static void anadirVehiculos(Vehiculo v) {
        String matricula = ES.leerCadena("Cual es el DNI o NIE del cliente");
        while (!Utilidades.comprobarMatricula(matricula) || !matricula.equals("1")) {
            ES.escribir("Este matricula no es valido, vuelva a introducirlo");
            ES.escribir("O escribi 1 para salir");
            matricula = ES.leerCadena("Cual es el matricula del cliente?");
        }
        String marca = ES.leerCadena("Introduce el nombre del Cliente");

        String modelo = ES.leerCadena("Introduce la dirección del cliente");

        int cilindrada = ES.leerEntero("Introduce la localidad del cliente");

        v = new Vehiculo(matricula, marca, modelo, cilindrada);

    }

    private static void anadirAlquiler(Cliente c, Vehiculo v) {

    }

    //METODOS BORRAR
    private static void borrarCliente(String dni) {

    }

    private static void borrarVehiculos(String matricula) {

    }

    private static void borrarAlquiler(Cliente c, Vehiculo v) {

    }

    //METODOS GET
    private static Cliente GetCliente(String dni) {
        return null;
    }

    private static Vehiculo anadirVehiculos(String matricula) {
        return null;

    }

}
