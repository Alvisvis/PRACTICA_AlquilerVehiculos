/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author dam-29
 */
public class Alquiler implements Serializable {

    //VARIABLES
    /*
    Formato de las fechas
     */
    private final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /*
    Precio del dia
     */
    private final double PRECIO_DIA = 43.0;
    /*
    Fechas de inicio y fecha de cierre del alquiler
     */
    private LocalDateTime fechaInicio, fechaCierre;
    /*
    dias que tiene el alquler abierto
     */
    private int dias;
    /*
    Cliente del alquiler
     */
    private Cliente cliente;
    /*
    Vehiculo del alquiler
     */
    private Vehiculo turismo;

    //CONSTRUCTORES
    /**
     * Constructor de todos los objetos de la clase
     *
     * @param c
     * @param v
     * @param fechaInicio
     * @param fechaCierre
     */
    public Alquiler(Cliente c, Vehiculo v, LocalDateTime fechaInicio, LocalDateTime fechaCierre) {
        this.fechaInicio = (fechaInicio != null) ? fechaInicio : LocalDateTime.now();
        this.fechaCierre = fechaCierre;
        if (v != null) {
            this.turismo = v;
        }
        if (c != null) {
            this.cliente = c;
        }
        this.dias = 0;
        turismo.setDisponible(false);
    }

    //GETTER
    /**
     * Obtiene la fecha de inicio del alquiler
     *
     * @return fecha de inicio del alquiler
     */
    public LocalDateTime getFecha() {
        return fechaInicio;
    }

    /**
     * Obtienes los dias del alquiler
     *
     * @return Dias que tiene el alquiler
     */
    public int getDias() {
        return dias;
    }

    /**
     * Obtienes al cliente del alquiler
     *
     * @return clientes
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtienes al vehiculo que tiene el alquiler
     *
     * @return
     */
    public Vehiculo getTurismo() {
        return turismo;
    }

    //METODOS
    /**
     * Cierra el alquiler que este abierto. Da los numeros de dias que hay entre
     * fechaInicio y fechaCierre. Hace que el vehiculo vuelva a estar disponible
     */
    public void cerrar() {
        if (fechaCierre != null) {
            System.out.println("Ya esta cerrado");
        } else {
            fechaCierre = LocalDateTime.now();
            dias = diferenciaDias(getFecha());
            if (dias <= 0) {
                dias = 1;
            }
            getTurismo().setDisponible(true);
        }
    }

    /**
     * Devuelve la diferencia de dias que hay entre una fecha y otra
     *
     * @param fecha2
     * @return dias
     */
    private int diferenciaDias(LocalDateTime fecha2) {
        return (int) ChronoUnit.DAYS.between(fecha2, LocalDateTime.now());
    }

    /**
     * Devuelve el precio del alquiler haciendo un calculo matematico
     *
     * @return precio del alquiler
     */
    public double precioAlquiler() {
        return PRECIO_DIA * getDias() + getTurismo().getCilindrada() / 100;
    }

    /**
     * Devuelve la cadena de texto de los datos de la clase
     *
     * @return Datos de la clase en formato texto
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alquiler: \n");
        sb.append("\nCliente: ").append(cliente);
        sb.append("\nTurismo: ").append(turismo);
        sb.append("\nFecha de Inicio: ").append(fechaInicio);
        sb.append("\nFecha de Cierre: ").append(fechaCierre);
        sb.append("\nDias: ").append(dias);

        return sb.toString();
    }

    /**
     * Devuelve los datos de la clase en formato fecha. Estos datos son escritos
     * en un archivo de tipo .txt o .dat
     *
     * @return Devuelve los datos de la clase para un archivo
     */
    public String toEscribir() {
        return "Alquiler#"
                + (cliente != null ? cliente.getDni() : "SIN_CLIENTE") + "#"
                + (turismo != null ? turismo.getMatricula() : "SIN_VEHICULO") + "#"
                + fechaInicio + "#"
                + (fechaCierre == null ? "Abierto" : fechaCierre);
    }
}
