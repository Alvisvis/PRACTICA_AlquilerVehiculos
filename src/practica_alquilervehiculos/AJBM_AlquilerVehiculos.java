/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_alquilervehiculos;

import java.util.Scanner;
import practica_alquilervehiculos.Cliente;
import practica_alquilervehiculos.Vehiculo;
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
    private static int MAX_VEHICULOS = 50;
    private static int MAX_CLIENTES = 50;
    private static int MAX_ALQUILERES = 50;

    private static Vehiculo[] vehiculos = new Vehiculo[MAX_VEHICULOS];
    private static Cliente[] clientes = new Cliente[MAX_CLIENTES];
    private static Alquiler[] alquileres = new Alquiler[MAX_ALQUILERES];

    private static int numVehiculo = 0;
    private static int numCliente = 0;
    private static int numAlquiler = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dni;
        String matricula;
        int op;
        do {
            menu();

            op = sc.nextInt();
            switch (op) {
                case 1:
                    Cliente c = insertarCliente();
                    anadirCliente(c);
                    break;
                case 2:
                    dni = ES.leerCadena("Escribir el dni");
                    borrarCliente(dni);
                    break;
                case 3:
                    ListarCliente();
                    break;
                case 4:
                    Vehiculo v = insertarVehiculo();
                    anadirVehiculos(v);
                    break;
                case 5:
                    matricula = ES.leerCadena("Escribi la matricula");
                    borrarVehiculos(matricula);
                    break;
                case 6:
                    ListarVehiculo();
                    break;
                case 7:
                    insertarAlquiler();
                    break;
                case 8:

                    break;
                case 9:
                    ListarAlquiler();
                    break;

            }
        } while (op != 0);
    }

    //MENU
    private static void menu() {
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

    //METODOS INSERTAR
    private static Cliente insertarCliente() {
        Cliente c;
        String dni = ES.leerCadena("Cual es el DNI o NIE del cliente");
        while (Utilidades.comprobarDni(dni) || dni.equals("1")) {
            ES.escribir("Este DNI o NIE no es valido, vuelva a introducirlo");
            ES.escribirLn(" o escribi 1 para salir");
            dni = ES.leerCadena("Cual es el DNI del cliente?");
        }
        String nombre = ES.leerCadena("Introduce el nombre del Cliente");

        String direccion = ES.leerCadena("Introduce la dirección del cliente");

        String localidad = ES.leerCadena("Introduce la localidad del cliente");

        String codigoPostal = ES.leerCadena("Introduce el codigo postal del cliente");
        while (Utilidades.comprobarCodigoPostal(codigoPostal) || codigoPostal.equals("1")) {
            ES.escribir("Este codigo postal no es valido, vuelva a introducirlo");
            ES.escribirLn(" o escribi 1 para salir");
            codigoPostal = ES.leerCadena("Introduce el codigo postal del cliente");
        }
        return c = new Cliente(dni, nombre, direccion, localidad, codigoPostal);
    }

    private static Vehiculo insertarVehiculo() {
        Vehiculo v;
        String matricula = ES.leerCadena("Cual es el DNI o NIE del cliente");
        while (Utilidades.comprobarMatricula(matricula) || matricula.equals("1")) {
            ES.escribir("Este matricula no es valido, vuelva a introducirlo");
            ES.escribir("O escribi 1 para salir");
            matricula = ES.leerCadena("Cual es el matricula del cliente?");
        }
        String marca = ES.leerCadena("Introduce el nombre del Cliente");

        String modelo = ES.leerCadena("Introduce la dirección del cliente");

        int cilindrada = ES.leerEntero("Introduce la localidad del cliente");

        return v = new Vehiculo(matricula, marca, modelo, cilindrada);

    }

    private static void insertarAlquiler() {
        String dni = ES.leerCadena("DNI del cliente: ");
        while (Utilidades.comprobarDni(dni)) {
            ES.escribirLn("Este DNI o NIE no es valido, vuelva a introducirlo");
            dni = ES.leerCadena("Cual es el DNI del cliente?");
        }
        String matricula = ES.leerCadena("Matrícula del vehículo: ");
        while (Utilidades.comprobarMatricula(matricula)) {
            ES.escribirLn("Este matricula no es valido, vuelva a introducirlo");
            matricula = ES.leerCadena("Cual es el matricula del cliente?");
        }
        Cliente c = getCliente(dni);
        if (c == null) {
            System.out.println("No existe ese cliente con ese dni");
        }
        Vehiculo v = getVehiculos(matricula);
        if (v == null) {
            System.out.println("No existe ese vehiculo con esa matricula");
        }
        nuevoAlquiler(c, v);
    }

    //METODOS AÑADIR
    private static void anadirCliente(Cliente c) {
        String dni = c.getDni();

        if (dni.equals(getCliente(dni))) {
            System.out.println("Este cliente ya existe");
        }
        if (numCliente < MAX_CLIENTES) {
            if (clientes[numCliente] == null) {
                clientes[numCliente] = c;
                ES.escribirLn("Cliente añadido correctamente.");
            }
            System.out.println("ERROR");
        }

    }

    private static void anadirVehiculos(Vehiculo v) {
        String matricula = v.getMatricula();

        if (matricula.equals(getVehiculos(matricula))) {
            System.out.println("Esta matricula ya existe");
        }
        if (vehiculos[numVehiculo] == null) {
            vehiculos[numVehiculo] = v;
            ES.escribir("Vehículo añadido correctamente.");
        }
        System.out.println("ERROR");
    }

    private static void nuevoAlquiler(Cliente c, Vehiculo v) {

        if (!v.isDisponible()) {
            System.out.println("");
        }
        if (alquileres[numAlquiler] == null) {
            alquileres[numAlquiler] = new Alquiler(c, v);
            ES.escribir("Alquiler abierto correctamente.");
        }
        System.out.println("ERROR");
    }

    //METODOS BORRAR
    private static void borrarCliente(String dni) {
        
        if (Utilidades.comprobarDni(dni)) {
            System.out.println("DNI incorrecto");
        }
        
        for (int i = 0; i < MAX_CLIENTES; i++) {
            if (dni.equals(clientes[i])) {
                
            }
        }
    }

    private static void borrarVehiculos(String matricula) {
        
    }

    private static void borrarAlquiler(Cliente c, Vehiculo v) {
        String matricula = v.getMatricula();
        String dni = c.getDni();
        
        
    }

    //METODOS GET
    private static Cliente getCliente(String dni) {
        for (Cliente c : clientes) {
            if (c != null && c.getDni().equals(dni)) {
                return c;
            }
        }
        return null;
    }

    private static Vehiculo getVehiculos(String matricula) {
        for (Vehiculo v : vehiculos) {
            if (v != null && v.getMatricula().equals(matricula)) {
                return v;
            }
        }
        return null;
    }

    //METODOS LISTAR
    private static void ListarCliente() {
        ES.escribirLn("------------------------------------------");
        System.out.println("\t Lista de Clientes");
        for (int i = 0; i < MAX_CLIENTES; i++) {
            if (clientes[i] != null) {
                System.out.println(clientes[i] + " ");
            }
        }
    }

    private static void ListarVehiculo() {
        ES.escribirLn("------------------------------------------");
        System.out.println("\t Lista de Vehiculos");
        for (int i = 0; i < MAX_VEHICULOS; i++) {
            if (vehiculos[i] != null) {
                System.out.println(vehiculos[i] + " ");
            }
        }
    }

    private static void ListarAlquiler() {
        ES.escribirLn("------------------------------------------");
        System.out.println("\t Lista de Alquires");
        for (int i = 0; i < MAX_ALQUILERES; i++) {
            if (alquileres[i] != null) {
                System.out.println(alquileres[i] + " ");
            }
        }
    }

    //METODOS PLUS
    private static void quitarHueco(String dni) {
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
