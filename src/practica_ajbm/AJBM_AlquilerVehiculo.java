/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_ajbm;

import java.util.Scanner;
import practica_ajbm.Vehiculo;
import utiles.ES;
import utiles.Utilidades;

/**
 *
 * @author dam-29
 */
public class AJBM_AlquilerVehiculo {

    //ATRIBUTOS
    private static final String rutadatC = "C:\\Users\\dam1\\Documents\\NetBeansProjects\\Practica_AJBM\\cliente_AJBM.dat";
    private static final String rutadatV = "C:\\Users\\dam1\\Documents\\NetBeansProjects\\Practica_AJBM\\vehiculo_AJBM.dat";
    private static final String rutadatA = "C:\\Users\\dam1\\Documents\\NetBeansProjects\\Practica_AJBM\\alquilers_AJBM.dat";

    private static final String rutatxtC = "C:\\Users\\dam1\\Documents\\NetBeansProjects\\Practica_AJBM\\cliente_AJBM.txt";
    private static final String rutatxtV = "C:\\Users\\dam1\\Documents\\NetBeansProjects\\Practica_AJBM\\vehiculo_AJBM.txt";
    private static final String rutatxtA = "C:\\Users\\dam1\\Documents\\NetBeansProjects\\Practica_AJBM\\alquilers_AJBM.txt";

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
                            System.out.println(clientes[i].toString());
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
                            System.out.println(vehiculos[i].toString());
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
                case 12:
                    guardarDatosTXT();
                    guardarDatosDAT();
                    break;
                case 13:
                    int tipo = ES.leerEntero("¿Cual archivo quieres leer?"
                            + "\n 1. Archivo Clientes"
                            + "\n 2. Archivo Vehiculos"
                            + "\n 3. Archivo Alquleres");
                    if (tipo == 1) {
                        ES.leerArchivo(rutadatC);
                        ES.leerArchivo(rutatxtC);
                    } else if (tipo == 2) {
                        ES.leerArchivo(rutadatV);
                        ES.leerArchivo(rutatxtV);
                    } else if (tipo == 3) {
                        ES.leerArchivo(rutadatA);
                        ES.leerArchivo(rutatxtA);
                    } else {
                        System.out.println("Esa opcion no es correcta");
                    }
                    break;
            }
        } while (op != 0);
    }

    //ARCHIVOS
    public static void guardarDatosTXT() {

        for (int i = 0; i < numCliente; i++) {
            if (i == 0) {
                ES.escribirArchivo(rutatxtC, clientes[i].toString(), true);
            } else {
                ES.escribirArchivo(rutatxtC, clientes[i].toString(), false);
            }
        }

        for (int i = 0; i < numVehiculo; i++) {
            if (i == 0) {
                ES.escribirArchivo(rutatxtV, vehiculos[i].toString(), true);
            } else {
                ES.escribirArchivo(rutatxtV, vehiculos[i].toString(), false);
            }
        }

        for (int i = 0; i < numAlquiler; i++) {
            if (i == 0) {
                ES.escribirArchivo(rutatxtA, alquileres[i].toString(), true);
            } else {
                ES.escribirArchivo(rutatxtA, alquileres[i].toString(), false);
            }
        }

    }

    public static void guardarDatosDAT() {

        for (int i = 0; i < numCliente; i++) {
            if (i == 0) {
                ES.escribirArchivo(rutadatC, clientes[i].toString(), true);
            } else {
                ES.escribirArchivo(rutadatC, clientes[i].toString(), false);
            }
        }

        for (int i = 0; i < numVehiculo; i++) {
            if (i == 0) {
                ES.escribirArchivo(rutadatV, vehiculos[i].toString(), true);
            } else {
                ES.escribirArchivo(rutadatV, vehiculos[i].toString(), false);
            }
        }

        for (int i = 0; i < numAlquiler; i++) {
            if (i == 0) {
                ES.escribirArchivo(rutadatA, alquileres[i].toString(), true);
            } else {
                ES.escribirArchivo(rutadatA, alquileres[i].toString(), false);
            }
        }

    }

    //MENU
    /**
     * Metodo menu que se mostrara al principio para indicar que numero hay que
     * introducir para hacer una funcion
     */
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

        ES.escribirLn("12. Guardar datos");
        ES.escribirLn("13. Leer archivos guardados");
        ES.escribirLn("0. Cerrar");
    }

    //METODOS CLIENTES
    /**
     * Metodo que busca un cliente con un DNI introducido
     *
     * @param dni
     * @return objeto cliente o null
     */
    private static Cliente getCliente(String dni) {
        for (Cliente c : clientes) {
            if (c != null && c.getDni().equals(dni)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Metodo para insertar datos de un cliente y crear un objeto cliente
     *
     * @return objeto cliente
     */
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

    /**
     * Metodo que recibe un objeto Cliente, recorre el array para verificar si
     * el DNI existe en la array, verifica si hay espacio en la array clientes y
     * si queda espacio en el array
     *
     * @param c
     */
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
            }
        } else {
            System.out.println("No hay espacio para mas clientes");
        }
    }

    /**
     * Metodo para dar de baja a un cliente de la array, sin elimanarlo de la
     * array
     *
     * @param dni
     */
    private static void BorrarCliente(String dni) {
        while (!Utilidades.comprobarDni(dni)) {
            ES.escribirLn("Este DNI o NIE no es valido, vuelva a introducirlo");
            dni = ES.leerCadena("Cual es el DNI del cliente?");
        }

        boolean encontrado = false;
        Cliente c;

        for (int i = 0; i < numCliente && !encontrado; i++) {
            if (clientes[i].getDni().equals(dni)) {
                c = getCliente(dni);
                c.setBaja(true);
                encontrado = true;
                ES.escribir("Cliente borrado existosamente");
            } else {
                System.out.println("Error. Cliente no ha sido borrado, no existe ningun cliente con ese DNI");
            }
        }
    }

    /**
     * Metodo para listar los clientes pertenecientes de la array clientes
     */
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
    /**
     * Metodo que buscar un vehiculo en la array de vehiculos, a base de la
     * matricula
     *
     * @param matricula
     * @return objeto vehiculo o null
     */
    private static Vehiculo getVehiculos(String matricula) {
        for (Vehiculo v : vehiculos) {
            if (v != null && v.getMatricula().equals(matricula)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Metodo para insertar datos de un vehiculo dependiendo de su tipo y crea
     * un objeto de relativo tipo de vehiculo
     *
     * @return objeto de tipo de vehiculo
     */
    private static Vehiculo insertarVehiculo() {
        Vehiculo v = null;
        boolean correcto = false;

        String matricula = ES.leerCadena("Introduce la matricula del vehiculo");
        while (!Utilidades.comprobarMatricula(matricula)) {
            ES.escribirLn("Este matricula no es valido, vuelva a introducirlo");
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
                    + "\n1 - Gasolina"
                    + "\n2 - Diesel"
                    + "\n3 - Hibrido"
                    + "\n4- Eletrico");
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
                boolean descapotable = ES.leerBoolean("Introduce S o Si, si el vehiculo es descapotable,"
                        + " al contrario, introduce N o no, si el vehiculo no lo es");
                Enumerados.CajaCambio cambio = null;
                int po = ES.leerEntero("Introduce uno de estos numeros para saber su caja de cambio: "
                        + "\n1 - Automatico"
                        + "\n2 - Manual");

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
                        + " al contrario, introduce N o no, si el vehiculo no lo tiene");
                return v = new Familiar(nPlazas, sillita, nPuertas, tipoCombustible, matricula, marca, modelo, cilindrada);
            }
        } else if (tipo == 2) {
            int pma = ES.leerEntero("¿Cual es el peso del vehiculo?");
            int volumen = ES.leerEntero("¿Cual es el volumen del vehiculo?");
            boolean refrigerado = ES.leerBoolean("Introduce S o Si, si el vehiculo es refrigerado"
                    + " al contrario, introduce N o no, si el vehiculo no lo es");
            Enumerados.Tamano tamanio = null;
            int opt = ES.leerEntero("Introduce uno de estos numeros para saber el tipo de combustible: "
                    + "\n1 - Grande"
                    + "\n2 - Mediano"
                    + "\n3 - Pequeño");
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

    /**
     * Metodo para añadir un vehiculo, verificando si el vehiculo exites ya( con
     * su matricula), verifica si tiene espacio la array vehiculo y lo añade
     *
     * @param v
     */
    private static void anadirVehiculos(Vehiculo v) {
        String matricula = v.getMatricula();

        if (getVehiculos(matricula) != null) {
            System.out.println("Esta matricula ya existe");
        }
        if (numVehiculo < MAX_VEHICULOS) {
            if (getVehiculos(v.getMatricula()) == null) {
                vehiculos[numVehiculo] = v;
                numVehiculo++;
                ES.escribirLn("Vehiculo añadido correctamente.");
            }
        } else {
            System.out.println("No hay espacio para mas vehiculos");
        }
        System.out.println("");
    }

    private static void borrarVehiculos(String matricula) {

        while (!Utilidades.comprobarMatricula(matricula)) {
            ES.escribir("Este matricula no es valido, vuelva a introducirlo");
            matricula = ES.leerCadena("Cual es el matricula del cliente?");
        }

        boolean encontrado = false;
        Vehiculo v = null;

        for (int i = 0; i < numVehiculo && !encontrado; i++) {
            if (vehiculos[i].getMatricula().equals(matricula)) {
                v.setBaja(true);
                encontrado = true;
                System.out.println("Vehiculo dado de baja existosamente");
            }
        }
        System.out.println("Error. Vehiculo no dado de baja");
    }

    /**
     * Metodo para listar los vehiculos que estan en la array de vehiculos
     */
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
    /**
     * Metodo para insertar datos en la array de alquileres, crea 1 objeto
     * vehiculo y otro cliente para luego usar el metodo nuevoAlquiler
     */
    private static void insertarAlquiler() {
        String dni = ES.leerCadena("DNI del cliente: ");
        while (!Utilidades.comprobarDni(dni)) {
            ES.escribirLn("Este DNI o NIE no es valido, vuelva a introducirlo");
            dni = ES.leerCadena("Cual es el DNI del cliente?");
        }
        String matricula = ES.leerCadena("Matrícula del vehículo: ");
        while (!Utilidades.comprobarMatricula(matricula)) {
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

    /**
     * Metodo para añadir un alquiler
     *
     * @param c
     * @param v
     */
    private static void nuevoAlquiler(Cliente c, Vehiculo v) {

        if (!v.isDisponible()) {
            System.out.println("");
        }
        if (alquileres[numAlquiler] == null) {
            alquileres[numAlquiler] = new Alquiler(c, v);
            numAlquiler++;
            ES.escribir("Alquiler abierto correctamente.");
        } else {
            System.out.println("ERROR");

        }
    }

    /**
     * Pide los datos para poder eliminar un alquiler, creando 2 objetos que son
     * de Vehiculo y Cliente y luego utilizar el metodo borrarAlquiler
     */
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

    /**
     * Metodo para borrar un alquiler, recibiendo 2 objetos y elimina la
     * posicion y el array al completo
     *
     * @param c
     * @param v
     */
    private static void borrarAlquiler(Cliente c, Vehiculo v) {
        int posicion = -1;

        // Buscar el alquiler
        for (int i = 0; i < numAlquiler; i++) {
            if (alquileres[i] != null
                    && alquileres[i].getCliente().getDni().equals(c.getDni())
                    && alquileres[i].getTurismo().getMatricula().equals(v.getMatricula())) {

                posicion = i;
                break;
            }
        }

        // Si no se encontró
        if (posicion == -1) {
            System.out.println("No se encontró el alquiler.");
            return;
        }

        // Desplazar elementos hacia la izquierda
        for (int i = posicion; i < numAlquiler - 1; i++) {
            alquileres[i] = alquileres[i + 1];
        }

        // Limpiar última posición
        alquileres[numAlquiler - 1] = null;

        numAlquiler--;

        System.out.println("Alquiler eliminado correctamente.");
    }

    /**
     * Metodo para listar los alquiler que existen en la array alquiler
     */
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
