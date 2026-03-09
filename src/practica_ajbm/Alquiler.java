/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author dam-29
 */
public class Alquiler {

    //Atributo
    private final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
    private final double PRECIO_DIA = 43.0;
    private LocalDateTime fechaInicio, fechaCierre;
    private int dias;
    private Cliente cliente;
    private Vehiculo turismo;
    private boolean baja;

    //Constructores
    public Alquiler(Cliente c , Vehiculo v, LocalDateTime fechaInicio, LocalDateTime fechaCierre) {
        fechaInicio = LocalDateTime.now();
        fechaCierre = null;
        this.turismo = v;
        this.cliente = c;
        this.dias = 0;
        turismo.setDisponible(false);
        this.baja = false;
    }

    //GETTER
    public LocalDateTime getFecha() {
        return fechaInicio;
    }

    public int getDias() {
        return dias;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getTurismo() {
        return turismo;
    }

    //METODOS
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

    private int diferenciaDias(LocalDateTime fecha2) {
        return (int) ChronoUnit.DAYS.between(fecha2, LocalDateTime.now());
    }

    public double precioAlquiler() {
        return PRECIO_DIA * getDias() + getTurismo().getCilindrada() / 100;
    }

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

    public String toEscribir() {
        return "Alquiler#" 
                + cliente.getDni() + "#" 
                + turismo.getMatricula() + "#" 
                + fechaInicio + "#" 
                + (fechaCierre == null ? "Abierto" : fechaCierre);
    }
}
