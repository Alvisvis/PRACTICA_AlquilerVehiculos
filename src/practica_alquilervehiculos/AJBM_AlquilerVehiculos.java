/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_alquilervehiculos;

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
    private int MAX_VEHICULOS = 50;
    private int MAX_CLIENTES = 50;
    private int MAX_ALQUILERES = 50;

    private Vehiculo[] vehiculos = new Vehiculo[MAX_VEHICULOS];
    private Cliente[] clientes = new Cliente[MAX_CLIENTES];
    private Alquiler[] alquileres = new Alquiler[MAX_ALQUILERES];

    private int numVehiculo = 50;
    private int numCliente = 50;
    private int numAlquiler = 50;

    public static void main(String[] args) {

    }

    //METODOS AÑADIR
    private void anadirCliente(Cliente c) {

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

        for (int i = 0; i < numCliente; i++) {
            if (clientes[i] == null) {
                clientes[i] = c;
                ES.escribir("Cliente añadido correctamente.");
                return;
            }
        }

    }

    private void anadirVehiculos(Vehiculo v) {

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

        for (int i = 0; i < numVehiculo; i++) {
            if (vehiculos[i] == null) {
                vehiculos[i] = v;
                ES.escribir("Vehículo añadido correctamente.");
                return;
            }
        }
    }

    private void anadirAlquiler(Cliente c, Vehiculo v) {
        String dni = ES.leerCadena("DNI del cliente: ");
        String matricula = ES.leerCadena("Matrícula del vehículo: ");

        if (getCliente(c.getDni()).equals(dni) && getVehiculos(v.getMatricula()).equals(matricula)) {
            for (int i = 0; i < numAlquiler; i++) {
                if (alquileres[i] == null) {
                    alquileres[i] = new Alquiler(c, v);
                    ES.escribir("Alquiler abierto correctamente.");
                    return;
                }
            }
        }
    }

    //METODOS BORRAR
    private void borrarCliente(String dni) {
        boolean encontrado = false;
        Cliente c = null;

        for (int i = 0; i < numCliente && !encontrado; i++) {
            if (alquileres[i].getCliente().getDni().equals(dni)) {
                c = alquileres[i].getCliente();
                encontrado = true;
            }
        }
        if (encontrado) {
            System.out.println("El cliente tine un alquiler, no se puede borrar");
        } else {
            c = getCliente(dni);

            if (c != null) {
                c.setBaja(true);
            }
            System.out.println("Cliente dado de baja");
        }
    }

    private void borrarVehiculos(String matricula) {
        boolean encontrado = false;
        Vehiculo v = null;

        for (int i = 0; i < numCliente && !encontrado; i++) {
            if (alquileres[i].getTurismo().getMatricula().equals(matricula)) {
                v = alquileres[i].getTurismo();
                encontrado = true;
            }
        }
        if (encontrado) {
            System.out.println("El cliente tine un alquiler, no se puede borrar");
        } else {
            v = getVehiculos(matricula);

            if (v != null) {
                v.setBaja(true);
            }
            System.out.println("Cliente dado de baja");
        }
    }

    private void borrarAlquiler(Cliente c, Vehiculo v) {
        boolean encontrado = false;
        Alquiler a = null;
        String dni = ES.leerCadena("Introduce el dni");
        String matricula = ES.leerCadena("Introduce la matricula");
        for (int i = 0; i < numAlquiler; i++) {

            if (a != null && a.getCliente().getDni().equalsIgnoreCase(dni) && a.getTurismo().getMatricula().equalsIgnoreCase(matricula)) {

                a.cerrar();
                ES.escribir("Alquiler cerrado.");
                return;
            }
        }
    }

    //METODOS GET
    private Cliente getCliente(String dni) {
        for (Cliente c : clientes) {
            if (c != null && c.getDni().equalsIgnoreCase(dni)) {
                return c;
            }
        }
        return null;
    }

    private Vehiculo getVehiculos(String matricula) {
        for (Vehiculo v : vehiculos) {
            if (v != null && v.getMatricula().equalsIgnoreCase(matricula)) {
                return v;
            }
        }
        return null;
    }

    //METODOS PLUS
    private void quitarHueco(String dni) {
        boolean encontrado = false;
        Cliente c = null;

        for (int i = 0; i < numCliente && !encontrado; i++) {
            if (clientes[i].getDni().equals(dni)) {
                clientes[i] = null;
                clientes[numCliente - 1] = null;
                encontrado = true;
            }
        }
    }
}
