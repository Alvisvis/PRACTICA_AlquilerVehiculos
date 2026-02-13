/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_ajbm;

import java.util.Scanner;
import utiles.ES;
import utiles.Utilidades;

/**
 *
 * @author dam-29
 */
public class AJBM_AlquilerVehiculo {
    
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
                    dni = ES.leerCadena("Introduce el DNI del cliente");
                    BorrarCliente(dni);
                    break;
                case 3:
                    ListarCliente();
                    break;
                case 4:
                    for (int i = 0; i < numCliente; i++) {
                        if (clientes[i] != null && clientes[i].isBaja()) {
                            System.out.println(clientes[i]);
                        }
                    }
                    break;
                case 5:
                    Vehiculo v = insertarVehiculo();
                    anadirVehiculos(v);
                    break;
                case 6:
                    matricula = ES.leerCadena("Introduce la matricula del Vehiculo");
                    borrarVehiculos(matricula);
                    break;
                case 7:
                    ListarVehiculo();
                    break;
                case 8:
                    for (int i = 0; i < numVehiculo; i++) {
                        if (vehiculos[i] != null && vehiculos[i].isBaja()) {
                            System.out.println(vehiculos[i]);
                        }
                    }
                    break;
                case 9:
                    insertarAlquiler();
                    break;
                case 10:
                    eliminarAlquiler();
                    break;
                case 11:
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
        ES.escribirLn("4. Listar clientes dados de baja");

        ES.escribirLn("5. Añadir vehiculo");
        ES.escribirLn("6. Dar de baja vehiculo");
        ES.escribirLn("7. Listar vehiculos");
        ES.escribirLn("8. Listar vehiculos dados de baja");

        ES.escribirLn("9. Nuevo alquiler");
        ES.escribirLn("10. Cerrar un alquiler");
        ES.escribirLn("11. Listar alquileres");
        ES.escribirLn("0. Cerrar");
    }

    //METODOS CLIENTES
    private static Cliente getCliente(String dni) {
        for (Cliente c : clientes) {
            if (c != null && c.getDni().equals(dni)) {
                return c;
            }
        }
        return null;
    }

    private static Cliente insertarCliente() {
        Cliente c;
        String dni = ES.leerCadena("Cual es el DNI o NIE del cliente");
        while (!Utilidades.comprobarDni(dni)) {
            ES.escribir("Este DNI o NIE no es valido, vuelva a introducirlo");
            dni = ES.leerCadena("Cual es el DNI del cliente?");
        }
        String nombre = ES.leerCadena("Introduce el nombre del Cliente");

        String direccion = ES.leerCadena("Introduce la dirección del cliente");

        String localidad = ES.leerCadena("Introduce la localidad del cliente");

        String codigoPostal = ES.leerCadena("Introduce el codigo postal del cliente");
        while (!Utilidades.comprobarCodigoPostal(codigoPostal)) {
            ES.escribir("Este codigo postal no es valido, vuelva a introducirlo");
            codigoPostal = ES.leerCadena("Introduce el codigo postal del cliente");
        }
        return c = new Cliente(dni, nombre, direccion, localidad, codigoPostal);
    }

    private static void anadirCliente(Cliente c) {
        String dni = c.getDni();

        if (getCliente(dni) != null) {
            System.out.println("Este cliente ya existe");
        }
        if (numCliente < MAX_CLIENTES) {
            if (getCliente(c.getDni()) == null) {
                clientes[numCliente] = c;
                numCliente++;
                ES.escribirLn("Cliente añadido correctamente.");
            } else {
                System.out.println("ERROR");

            }
        } else {
            System.out.println("No hay espacio para mas clientes");
        }
    }

    private static void BorrarCliente(String dni) {
        while (Utilidades.comprobarDni(dni)) {
            ES.escribirLn("Este DNI o NIE no es valido, vuelva a introducirlo");
            dni = ES.leerCadena("Cual es el DNI del cliente?");
        }

        boolean encontrado = false;
        Cliente c = null;

        for (int i = 0; i < numAlquiler && !encontrado; i++) {
            if (alquileres[i].getCliente().getDni().equals(dni)) {
                c = alquileres[i].getCliente();
                encontrado = true;
            }
        }

    }

    private static void ListarCliente() {
        ES.escribirLn("------------------------------------------");
        System.out.println("\t Lista de Clientes");
        for (int i = 0; i < MAX_CLIENTES; i++) {
            if (clientes[i] != null) {
                System.out.println(clientes[i] + " ");
            }
        }
    }

    //METODOS VEHICULOS
    private static Vehiculo getVehiculos(String matricula) {
        for (Vehiculo v : vehiculos) {
            if (v != null && v.getMatricula().equals(matricula)) {
                return v;
            }
        }
        return null;
    }

    private static Vehiculo insertarVehiculo() {
        Vehiculo v = null;
        boolean correcto = false;

        String matricula = ES.leerCadena("Introduce la matricula del vehiculo");
        while (!Utilidades.comprobarMatricula(matricula)) {
            ES.escribir("Este matricula no es valido, vuelva a introducirlo");
            matricula = ES.leerCadena("Cual es el matricula del vehiculo?");
        }
        String marca = ES.leerCadena("Introduce la marca del vehiculo");

        String modelo = ES.leerCadena("Introduce el modelo del vehiculo");

        int cilindrada = ES.leerEntero("Introduce el numero de cilindrada del vehiculo");

        int tipo = ES.leerEntero("¿Que vehiculos vas a introducir? "
                + "\n 1- Turismo"
                + "\n 2- Mercancia");
        if (tipo == 1) {

            int nPuertas = ES.leerEntero("Cuantas puertas tiene el coche?");
            if (nPuertas > 4 || nPuertas < 2) {
                while (nPuertas > 4 || nPuertas < 2) {
                    System.out.println("Un Turismo no puede tener ese numero de puertas, introduce un dato correcto");
                    nPuertas = ES.leerEntero("Cuantas puertas tiene el coche?");
                }
            }

            Enumerados.TipoCombustible tipoCombustible = null;
            int op = ES.leerEntero("Introduce uno de estos numeros para saber el tipo de combustible: "
                    + "1 - Gasolina"
                    + "2 - Diesel"
                    + "3 - Hibrido"
                    + "4- Eletrico");
            switch (op) {
                case 1:
                    tipoCombustible = Enumerados.TipoCombustible.GASOLINA;
                    break;
                case 2:
                    tipoCombustible = Enumerados.TipoCombustible.DIESEL;
                    break;
                case 3:
                    tipoCombustible = Enumerados.TipoCombustible.HIBRIDO;
                    break;
                case 4:
                    tipoCombustible = Enumerados.TipoCombustible.ELETRICO;
                    break;
            }
            int subTipo = ES.leerEntero("¿Que tipo de Turismo es?"
                    + "\n 1- Deportivo"
                    + "\n 2- Familiar");
            if (subTipo == 1) {
                boolean descapotable = ES.leerBoolean("Introduce S o Si, si el vehiculo es descapotable"
                        + "al contrario, introduce N o no, si el vehiculo no lo es");
                Enumerados.CajaCambio cambio = null;
                int po = ES.leerEntero("Introduce uno de estos numeros para saber su caja de cambio: "
                        + "1 - Automatico"
                        + "2 - Manual");

                switch (po) {
                    case 1:
                        cambio = Enumerados.CajaCambio.AUTOMATICA;
                        break;
                    case 2:
                        cambio = Enumerados.CajaCambio.MANUAL;
                        break;
                }
                return v = new Deportivo(cambio, descapotable, nPuertas, tipoCombustible, matricula, marca, modelo, cilindrada);
            } else if (subTipo == 2) {
                int nPlazas = ES.leerEntero("¿Cuantes plazas tiene el Vehiculo?");
                boolean sillita = ES.leerBoolean("Introduce S o Si, si el vehiculo tiene silla de bebe"
                        + "al contrario, introduce N o no, si el vehiculo no lo tiene");
                return v = new Familiar(nPlazas, sillita, nPuertas, tipoCombustible, matricula, marca, modelo, cilindrada);
            }
        } else if (tipo == 2) {
            int pma = ES.leerEntero("¿Cual es el peso del vehiculo?");
            int volumen = ES.leerEntero("¿Cual es el volumen del vehiculo?");
            boolean refrigerado = ES.leerBoolean("Introduce S o Si, si el vehiculo es refrigerado"
                    + "al contrario, introduce N o no, si el vehiculo no lo es");
            Enumerados.Tamano tamanio = null;
            int opt = ES.leerEntero("Introduce uno de estos numeros para saber el tipo de combustible: "
                    + "1 - Grande"
                    + "2 - Mediano"
                    + "3 - Pequeño");
            switch (opt) {
                case 1:
                    tamanio = Enumerados.Tamano.GRANDE;
                    break;
                case 2:
                    tamanio = Enumerados.Tamano.MEDIANA;
                    break;
                case 3:
                    tamanio = Enumerados.Tamano.PEQUENA;
                    break;
            }
            return v = new Furgoneta(refrigerado, tamanio, pma, volumen, matricula, marca, modelo, cilindrada);
        }

        return v;
    }

    private static void anadirVehiculos(Vehiculo v) {
        String matricula = v.getMatricula();

        if (getVehiculos(matricula) != null) {
            System.out.println("Esta matricula ya existe");
        }
        if (numVehiculo < MAX_VEHICULOS) {
            if (getVehiculos(v.getMatricula()) == null) {
                vehiculos[numVehiculo] = v;
                numVehiculo++;
                ES.escribirLn("Cliente añadido correctamente.");
            } else {
                System.out.println("ERROR");
            }
        } else {
            System.out.println("No hay espacio para mas clientes");
        }
        System.out.println("");
    }

    private static void borrarVehiculos(String matricula) {

        while (Utilidades.comprobarMatricula(matricula)) {
            ES.escribir("Este matricula no es valido, vuelva a introducirlo");
            matricula = ES.leerCadena("Cual es el matricula del cliente?");
        }

        boolean encontrado = false;
        Vehiculo v = null;

        for (int i = 0; i < numAlquiler && !encontrado; i++) {
            if (alquileres[i].getTurismo().getMatricula().equals(matricula)) {
                v = alquileres[i].getTurismo();
                encontrado = true;
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

    //METODOS ALQUILER
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

    private static void borrarAlquiler(Cliente c, Vehiculo v) {
        int i = 0;
        while (i < MAX_ALQUILERES) {
            if (alquileres[i] != null && alquileres[i].getCliente() == c && alquileres[i].getTurismo() == v) {
                alquileres[i] = null;
                numAlquiler--;
            }
            i++;
        }
    }

    private static void eliminarAlquiler() {
        String matricula = ES.leerCadena("Cual es la matricula del coche?");
        String dni = ES.leerCadena("Cual es el dni del coche?");

        while (Utilidades.comprobarDni(dni)) {
            ES.escribirLn("Este DNI o NIE no es valido, vuelva a introducirlo");
            dni = ES.leerCadena("Cual es el DNI del cliente?");
        }
        while (Utilidades.comprobarMatricula(matricula)) {
            ES.escribir("Este matricula no es valido, vuelva a introducirlo");
            matricula = ES.leerCadena("Cual es el matricula del cliente?");
        }

        Cliente c = getCliente(dni);
        Vehiculo m = getVehiculos(matricula);

        if (c == null) {
            System.out.println("No existe ese cliente con ese dni");
        }
        Vehiculo v = getVehiculos(matricula);
        if (v == null) {
            System.out.println("No existe ese vehiculo con esa matricula");
        }

        borrarAlquiler(c, v);
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

}
