/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_alquilervehiculos;

import java.util.Scanner;
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
        Scanner sc = new Scanner(System.in);
        AJBM_AlquilerVehiculos MiAlquiler = null;
        Cliente c = null;
        Vehiculo v = null;
        Alquiler a = null;
        String dni;
        String matricula;
        int op;
        do {
            menu();

            op = sc.nextInt();
            switch (op) {
                case 1:
                    MiAlquiler.anadirCliente(c);
                    break;
                case 2:
                    dni = ES.leerCadena("Escribir el dni");
                    MiAlquiler.borrarCliente(dni);
                    break;
                case 3:
                    
                    
                    break;
                case 4:
                    MiAlquiler.anadirVehiculos(v);
                    break;
                case 5:
                    matricula = ES.leerCadena("Escribi la matricula");
                    MiAlquiler.borrarVehiculos(matricula);
                    break;
                case 6:
                    break;
                case 7:
                    MiAlquiler.anadirAlquiler(c, v);
                    break;
                case 8:
                    MiAlquiler.borrarAlquiler(c, v);
                    break;
                case 9:
                    break;

            }
        } while (op != 0);
    }

    //MENU
    public static void menu() {
        ES.escribirLn("------------------------------------------");
        System.out.println("Alquileres de coches");
        ES.escribirLn("1. Añadir cliente");
        ES.escribirLn("2. Dar de baja cliente");
        ES.escribirLn("3. Listar clientes");
        ES.escribirLn("4. Añadir vehiculo");
        ES.escribirLn("5. Dar de baja vehiculo");
        ES.escribirLn("6. Listar vehiculos");
        ES.escribirLn("7. Abrir un alquiler");
        ES.escribirLn("8. Cerrar un alquiler");
        ES.escribirLn("9. Listar alquileres");
        ES.escribirLn("0. Cerrar");
    }

    //METODOS AÑADIR
    public void anadirCliente(Cliente c) {

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

    public void anadirVehiculos(Vehiculo v) {

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

    public void anadirAlquiler(Cliente c, Vehiculo v) {
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
    public void borrarCliente(String dni) {
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

    public void borrarVehiculos(String matricula) {
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

    public void borrarAlquiler(Cliente c, Vehiculo v) {
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

    public Vehiculo getVehiculos(String matricula) {
        for (Vehiculo v : vehiculos) {
            if (v != null && v.getMatricula().equalsIgnoreCase(matricula)) {
                return v;
            }
        }
        return null;
    }

    //METODOS PLUS
    public void quitarHueco(String dni) {
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
