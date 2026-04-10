/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_ajbm;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import practica_ajbm.Vehiculo;
import utiles.ConexionBBDD;
import utiles.ES;
import utiles.Utilidades;

/**
 *
 * @author dam-29
 */
public class AJBM_AlquilerVehiculo {

    //ATRIBUTOS
    private static final String rutadatC = "cliente_AJBM.dat";
    private static final String rutadatV = "vehiculo_AJBM.dat";
    private static final String rutadatA = "alquilers_AJBM.dat";

    private static final String rutatxtC = "cliente_AJBM.txt";
    private static final String rutatxtV = "vehiculo_AJBM.txt";
    private static final String rutatxtA = "alquilers_AJBM.txt";

    private static int MAX_VEHICULOS = 50;
    private static int MAX_CLIENTES = 50;
    private static int MAX_ALQUILERES = 50;

    private static ArrayList<Vehiculo> vehiculos = new ArrayList();
    private static ArrayList<Cliente> clientes = new ArrayList();
    private static ArrayList<Alquiler> alquileres = new ArrayList();

    private static int numVehiculo = 0;
    private static int numCliente = 0;
    private static int numAlquiler = 0;

    private static ConexionBBDD conexionBD;

    //LEER DATOS
    private static void leerDatos() {
        File fichero;
        Scanner sc = null;
        try {
            sc = new Scanner(new File(rutadatC));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] atributos = linea.split("#");

                //leer datos clientes
                if (atributos[0].equals("Cliente")) {
                    String dni = atributos[1];
                    String nombre = atributos[2];
                    String direccion = atributos[3];
                    String localidad = atributos[4];
                    String codigoPostal = atributos[5];

                    Cliente c = new Cliente(dni, nombre, direccion, localidad, codigoPostal);
                    anadirCliente(c);
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo clientes:" + e.getMessage());
        }

        try {
            sc = new Scanner(new File(rutadatV));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] atributos = linea.split("#");

                //Leer datos de los vehiculos
                if (atributos[0].equals("Deportivo")) {
                    String matricula = atributos[1];
                    String marca = atributos[2];
                    String modelo = atributos[3];
                    int cilindrada = Integer.parseInt(atributos[4]);
                    boolean disponible = Boolean.parseBoolean(atributos[5]);
                    boolean baja = Boolean.parseBoolean(atributos[6]);

                    int nPuertas = Integer.parseInt(atributos[7]);
                    Enumerados.TipoCombustible tipoCombustible = Enumerados.TipoCombustible.valueOf(atributos[8]);

                    boolean descapotable = Boolean.parseBoolean(atributos[9]);
                    Enumerados.CajaCambio cambio = Enumerados.CajaCambio.valueOf(atributos[10]);

                    Deportivo d = new Deportivo(cambio, descapotable, nPuertas, tipoCombustible, matricula, marca, modelo, cilindrada);
                    anadirVehiculos(d);
                }
                if (atributos[0].equals("Familiar")) {
                    String matricula = atributos[1];
                    String marca = atributos[2];
                    String modelo = atributos[3];
                    int cilindrada = Integer.parseInt(atributos[4]);
                    boolean disponible = Boolean.parseBoolean(atributos[5]);
                    boolean baja = Boolean.parseBoolean(atributos[6]);

                    int nPuertas = Integer.parseInt(atributos[7]);
                    Enumerados.TipoCombustible tipoCombustible = Enumerados.TipoCombustible.valueOf(atributos[8]);

                    int nPlazas = Integer.parseInt(atributos[9]);
                    boolean sillaBebe = Boolean.parseBoolean(atributos[10]);

                    Familiar fam = new Familiar(nPlazas, sillaBebe, nPuertas, tipoCombustible, matricula, marca, modelo, cilindrada);
                    anadirVehiculos(fam);
                }
                if (atributos[0].equals("Furgoneta")) {
                    String matricula = atributos[1];
                    String marca = atributos[2];
                    String modelo = atributos[3];
                    int cilindrada = Integer.parseInt(atributos[4]);
                    boolean disponible = Boolean.parseBoolean(atributos[5]);
                    boolean baja = Boolean.parseBoolean(atributos[6]);

                    int pma = Integer.parseInt(atributos[7]);
                    int volumen = Integer.parseInt(atributos[8]);

                    boolean refrigerado = Boolean.parseBoolean(atributos[9]);
                    Enumerados.Tamano tamanio = Enumerados.Tamano.valueOf(atributos[10]);

                    Furgoneta fur = new Furgoneta(refrigerado, tamanio, pma, volumen, matricula, marca, modelo, cilindrada);
                    anadirVehiculos(fur);
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo Vehiculo:" + e.getMessage());
        }

        try {
            sc = new Scanner(new File(rutadatA));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] atributos = linea.split("#");

                //Leer datos de un alquiler
                if (atributos[0].equals("Alquiler")) {
                    String dni = atributos[1];
                    Cliente c = getCliente(dni);

                    String matricula = atributos[2];
                    Vehiculo v = getVehiculos(matricula);

                    LocalDateTime fechaInicio = LocalDateTime.parse(atributos[3]);
                    LocalDateTime fechaFinal = null;
                    if (!atributos[4].equals("Abierto")) {
                        fechaFinal = LocalDateTime.parse(atributos[4]);
                    }
                    Alquiler a = new Alquiler(c, v, fechaInicio, fechaFinal);
                    nuevoAlquiler(a);
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo Alquiler:" + e.getMessage());

        }
    }

    public static void main(String[] args) {
        leerDatos();
        Scanner sc = new Scanner(System.in);
        String dni;
        String matricula;
        conexionBD = conexionBD.getInstance("172.26.101.103", "bd_Cliente", "abrizida", "Abri-2005#");
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
                    for (Cliente cliente : clientes) {
                        if (cliente.isBaja()) {
                            System.out.println(cliente.toString() + " ");
                        } else {
                            System.out.println("No hay clientes de baja");
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
                    for (Vehiculo vehiculo : vehiculos) {
                        if (vehiculo.isBaja()) {
                            System.out.println(vehiculo.toString() + " ");
                        } else {
                            System.out.println("No hay vehiculo de baja");
                        }
                    }

                    break;
                case 9:
                    Alquiler a = insertarAlquler();
                    nuevoAlquiler(a);
                    break;
                case 10:
                    eliminarAlquiler();
                    break;
                case 11:
                    ListarAlquiler();
                    break;
                case 12:
                    int elegir = ES.leerEntero("Elige como quieres guardar los datos:"
                            + "\n1- Guardar datos .txt"
                            + "\n2- Guardar datos .dat");
                    if (elegir == 1) {
                        guardarDatosTXT();
                    } else if (elegir == 2) {
                        guardarDatosDAT();
                    }
                    break;
                case 13:
                    int tipo = ES.leerEntero("¿Cual archivo quieres leer?"
                            + "\n 1. Archivo Clientes"
                            + "\n 2. Archivo Vehiculos"
                            + "\n 3. Archivo Alquleres");
                    if (tipo == 1) {
                        elegir = ES.leerEntero("Elige como quieres guardar los datos:"
                                + "\n1- Leer datos .txt"
                                + "\n2- Leer datos .dat");
                        if (elegir == 1) {
                            System.out.println(ES.leerArchivo(rutatxtC));

                        } else if (elegir == 2) {
                            System.out.println(ES.leerArchivo(rutadatC));
                        }
                    } else if (tipo == 2) {
                        elegir = ES.leerEntero("Elige como quieres guardar los datos:"
                                + "\n1- Leer datos .txt"
                                + "\n2- Leer datos .dat");
                        if (elegir == 1) {
                            ES.leerArchivo(rutatxtV);

                        } else if (elegir == 2) {
                            ES.leerArchivo(rutadatV);
                        }
                    } else if (tipo == 3) {
                        elegir = ES.leerEntero("Elige como quieres guardar los datos:"
                                + "\n1- Leer datos .txt"
                                + "\n2- Leer datos .dat");
                        if (elegir == 1) {
                            ES.leerArchivo(rutatxtA);

                        } else if (elegir == 2) {
                            ES.leerArchivo(rutadatA);
                        }
                    } else {
                        System.out.println("Esa opcion no es correcta");
                    }
                    break;
                case 14:
                    int opcion;
                    do {
                        menuBD();
                        opcion = sc.nextInt();
                        switch (opcion) {
                            case 1:
                                insertarClienteBD();
                                break;
                            case 2:
                                eliminarClienteBD();
                                break;
                            case 3:
                                listarClienteBD();
                                break;
                        }
                    } while (opcion != 0);
            }
        } while (op != 0);
    }
//MENU

    /**
     * Metodo menu que se mostrara al principio para indicar que numero hay que
     * introducir para hacer una funcion
     */
    private static void menu() {
        ES.escribirLn("------------------------------------------");
        System.out.println("\tAlquileres de coches");
        ES.escribirLn("1. Añadir cliente");
        ES.escribirLn("2. Dar de baja cliente");
        ES.escribirLn("3. Listar clientes");
        ES.escribirLn("4. Listar clientes dados de baja");
        ES.escribirLn("------------------------------------------");
        ES.escribirLn("5. Añadir vehiculo");
        ES.escribirLn("6. Dar de baja vehiculo");
        ES.escribirLn("7. Listar vehiculos");
        ES.escribirLn("8. Listar vehiculos dados de baja");
        ES.escribirLn("------------------------------------------");
        ES.escribirLn("9. Nuevo alquiler");
        ES.escribirLn("10. Cerrar un alquiler");
        ES.escribirLn("11. Listar alquileres");
        ES.escribirLn("------------------------------------------");
        ES.escribirLn("12. Guardar datos");
        ES.escribirLn("13. Leer archivos guardados");
        ES.escribirLn("------------------------------------------");
        ES.escribirLn("14.Menu Base de Datos");

        ES.escribirLn("0. Cerrar");
    }

    public static void menuBD() {
        System.out.println("\n\t     BBDD Usuario");
        System.out.println("\t-------------------------");
        System.out.println("\t1. Insertar usuario (BBDD)");
        System.out.println("\t2. Eliminar usuario (BBDD)");

        System.out.println("\t3. Listar usuarios");

        System.out.println("\t0. Salir");
        System.out.print("\nSelecciona opcion: ");
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
                clientes.add(c);
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

        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                cliente.setBaja(true);
                ES.escribir("Cliente dado de baja existosamente");
                break;
            } else {
                System.out.println("Error. Cliente no ha sido dado de baja, no existe ningun cliente con ese DNI");
            }
        }
    }

    /**
     * Metodo para listarClienteBD los clientes pertenecientes de la array
     * clientes
     */
    private static void ListarCliente() {
        ES.escribirLn("------------------------------------------");
        System.out.println("\t Lista de Clientes");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString() + " ");
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
                vehiculos.add(v);
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

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                vehiculo.setBaja(true);
                System.out.println("Vehiculo dado de baja existosamente");
            }
        }
        System.out.println("Error. Vehiculo no dado de baja");
    }

    /**
     * Metodo para listarClienteBD los vehiculos que estan en la array de
     * vehiculos
     */
    private static void ListarVehiculo() {
        ES.escribirLn("------------------------------------------");
        System.out.println("\t Lista de Vehiculos");
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo.toString() + " ");
        }
    }

    //METODOS ALQUILER
    /**
     * Metodo para añadir un alquiler
     *
     * @param c
     * @param v
     */
    private static Alquiler insertarAlquler() {
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
            return null;
        }

        Vehiculo v = getVehiculos(matricula);
        if (v == null) {
            System.out.println("No existe ese vehiculo con esa matricula");
            return null;

        }

        if (!v.isDisponible()) {
            System.out.println("Este vehiculo no esta disponible");
        }
        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaCierre = null;

        Alquiler a = new Alquiler(c, v, fechaInicio, fechaCierre);
        return a;

    }

    /**
     * Nuevo alquiler agrega al array alquileres un objeto alquiler
     *
     * @param a
     */
    private static void nuevoAlquiler(Alquiler a) {
        alquileres.add(a);
        numAlquiler++;
        ES.escribir("Alquiler abierto correctamente.");

    }

    /**
     * Pide los datos para poder eliminar un alquiler, creando 2 objetos que son
     * de Vehiculo y Cliente y luego utilizar el metodo borrarAlquiler
     */
    private static void eliminarAlquiler() {
        String matricula = ES.leerCadena("Cual es la matricula del coche?");
        String dni = ES.leerCadena("Cual es el dni del ?");

        while (!Utilidades.comprobarDni(dni)) {
            ES.escribirLn("Este DNI o NIE no es valido, vuelva a introducirlo");
            dni = ES.leerCadena("Cual es el DNI del cliente?");
        }
        while (!Utilidades.comprobarMatricula(matricula)) {
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
        for (Alquiler alquilere : alquileres) {
            if (alquilere.getCliente() != null && alquilere.getTurismo() != null) {
                alquilere.cerrar();
                System.out.println("Alquiler cerrado");
            } else {
                System.out.println("No se ha encontrado el alquiler");
            }
        }
    }

    /**
     * Metodo para listarClienteBD los alquiler que existen en la array alquiler
     */
    private static void ListarAlquiler() {
        ES.escribirLn("------------------------------------------");
        System.out.println("\t Lista de Alquires");
        for (Alquiler alquilere : alquileres) {
            System.out.println(alquilere.toString() + " ");
        }
    }

    //ARCHIVOS
    public static void guardarDatosTXT() {

        for (Cliente cliente : clientes) {
            if (cliente == null) {
                ES.escribirArchivo(rutatxtC, cliente.toEscribir(), true);
            } else {
                ES.escribirArchivo(rutatxtC, cliente.toEscribir(), false);
            }
        }

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo == null) {
                ES.escribirArchivo(rutatxtV, vehiculo.toEscribir(), true);
            } else {
                ES.escribirArchivo(rutatxtV, vehiculo.toEscribir(), false);
            }
        }

        for (Alquiler alquilere : alquileres) {
            if (alquilere == null) {
                ES.escribirArchivo(rutatxtA, alquilere.toEscribir(), true);
            } else {
                ES.escribirArchivo(rutatxtA, alquilere.toEscribir(), false);
            }
        }
    }

    public static void guardarDatosDAT() {

        for (Cliente cliente : clientes) {
            if (cliente == null) {
                ES.escribirArchivo(rutadatC, cliente.toEscribir(), true);
            } else {
                ES.escribirArchivo(rutadatC, cliente.toEscribir(), false);
            }
        }

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo == null) {
                ES.escribirArchivo(rutadatV, vehiculo.toEscribir(), true);
            } else {
                ES.escribirArchivo(rutadatV, vehiculo.toEscribir(), false);
            }
        }

        for (Alquiler alquilere : alquileres) {
            if (alquilere == null) {
                ES.escribirArchivo(rutadatA, alquilere.toEscribir(), true);
            } else {
                ES.escribirArchivo(rutadatA, alquilere.toEscribir(), false);
            }
        }
    }

    //BASE DE DATOS
    /**
     * Insertar usuario a la BBDD
     *
     */
    public static void insertarClienteBD() {
        ArrayList tuplaUsuario = new ArrayList<String>();
        String dni;
        String nombre;
        String direccion;
        String localidad;
        String codigoPostal;
        boolean baja;
        Scanner sc = new Scanner(System.in);

        System.out.println(" INSERTAR CLIENTE A LA BBDD");
        dni = ES.leerCadena("\nIntroduzca DNI: ");
        while (!Utilidades.comprobarDni(dni)) {
            ES.escribir("Este DNI o NIE no es valido, vuelva a introducirlo");
            dni = ES.leerCadena("\nIntroduzca DNI: ");
        }
        tuplaUsuario.add(dni);

        nombre = ES.leerCadena("Introduce el nombre del Cliente");
        tuplaUsuario.add(nombre);

        direccion = ES.leerCadena("Introduce la dirección del cliente");
        tuplaUsuario.add(direccion);

        localidad = ES.leerCadena("Introduce la localidad del cliente");
        tuplaUsuario.add(localidad);

        codigoPostal = ES.leerCadena("Introduce el codigo postal del cliente");
        while (!Utilidades.comprobarCodigoPostal(codigoPostal)) {
            ES.escribir("Este codigo postal no es valido, vuelva a introducirlo");
            codigoPostal = ES.leerCadena("Introduce el codigo postal del cliente");
        }
        tuplaUsuario.add(codigoPostal);

        baja = false;
        tuplaUsuario.add(String.valueOf(baja));

        conexionBD.insertarDatos("Cliente", tuplaUsuario);

    }
    //-----------------------------------------------------------------

    /**
     * Eliminar Usuario a la BBDD
     *
     */
    public static void eliminarClienteBD() {
        String dni;

        Scanner sc = new Scanner(System.in);

        System.out.println(" INSERTAR Cliente A LA BBDD");
        System.out.print("\nIntroduzca DNI: ");
        dni = sc.nextLine();

        conexionBD.eliminarDatos("Cliente", dni);
    }

    //-------------------------------------------------------------------------
    private static void listarClienteBD() {
        String sql;

        try {
            Statement sentencia = (Statement) conexionBD.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Cliente ;");

            mostrarDatosTabla(resultado);

            resultado.close();
            sentencia.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    private static void mostrarDatosTabla(ResultSet _resultado) throws SQLException {
        java.sql.ResultSetMetaData metaDatos = _resultado.getMetaData();
        int nColumnas = metaDatos.getColumnCount();
        String[] formato
                = {
                    "%10s", "%-15s", "%-20s", "%-10s", "%-10s", "%-10s"
                };

        for (int i = 1; i >= nColumnas; i++) {
            // Obtenemos el nombre de la columna y su valor
            System.out.printf(formato[i - 1] + ((i < nColumnas) ? " | " : " "), metaDatos.getColumnName(i));
        }
        System.out.println("");

        System.out.println("-----------------------------------------------------------");

        while (_resultado.next()) {
            System.out.printf("%10s | %-15s | %-20s | %-15s | %-15s | %-15s\n ", _resultado.getString(1),
                    _resultado.getString(2), _resultado.getString(3), _resultado.getString(4), _resultado.getString(5), _resultado.getBoolean(6));

        }
    }
}
