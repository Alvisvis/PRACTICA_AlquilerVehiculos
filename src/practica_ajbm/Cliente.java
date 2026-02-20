/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

/**
 *
 * @author dam-29
 */
public class Cliente {

    //Atributos
    private String dni;
    private String nombre;
    private String direccion;
    private String localidad;
    private String codigoPostal;
    private boolean baja;

    //Constructores
    public Cliente(String dni, String nombre, String direccion, String localidad, String codigoPostal) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.baja = false;

    }

    public Cliente(Cliente Ccopia) {
        this.dni = Ccopia.dni;
        this.nombre = Ccopia.nombre;
        this.direccion = Ccopia.direccion;
        this.localidad = Ccopia.localidad;
        this.codigoPostal = Ccopia.codigoPostal;
        this.baja = Ccopia.baja;

    }

    //Getter
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    //ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente:\n");
        sb.append("DNI: ").append(dni);
        sb.append("\nNombre: ").append(nombre);
        sb.append("\nDireccion: ").append(direccion);
        sb.append("\nLocalidad: ").append(localidad);
        sb.append("\nCodigoPostal: ").append(codigoPostal);
        sb.append("\nBaja: ").append(baja ? "Si" : "No");
        return sb.toString();
    }

    public String toEscribir() {
        return "Cliente: " + dni + nombre + direccion + localidad + codigoPostal + baja;
    }

    
}
