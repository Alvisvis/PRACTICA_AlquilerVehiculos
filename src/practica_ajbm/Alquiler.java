/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author dam-29
 */
public class Alquiler {

    //Atributo
    private final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
    private final double PRECIO_DIA = 43.0;
    private LocalDateTime fecha;
    private int dias;
    private Cliente cliente;
    private Vehiculo turismo;
    private boolean baja;

    //Constructores
    public Alquiler(Cliente c, Vehiculo v) {
        fecha = LocalDateTime.now();
        this.turismo = v;
        this.cliente = c;
        this.dias = 0;
        turismo.setDisponible(false);
        this.baja = false;
    }

    //GETTER
    public LocalDateTime getFecha() {
        return fecha;
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
        dias = diferenciaDias(getFecha());
        if (dias <= 0) {
            dias = 1;
        }
        getTurismo().setDisponible(true);

    }

    private int diferenciaDias(LocalDateTime fecha2) {
        LocalDateTime fechaHoy = LocalDateTime.now();
        int diferencia = 0;

        while (fechaHoy != fecha2) {
            diferencia++;
        }
        return diferencia;
    }

    public double precioAlquiler() {
        return PRECIO_DIA * getDias() + getTurismo().getCilindrada() / 100;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alquiler: \n");
        sb.append("\nFecha: ").append(fecha);
        sb.append("\nDias: ").append(dias);
        sb.append("\nCliente: ").append(cliente);
        sb.append("\nTurismo: ").append(turismo);
        return sb.toString();
    }

    public String toEscribir() {
        return "Alquiler#" + fecha + "#" + dias + "#" + cliente + "#" + turismo;
    }
}
