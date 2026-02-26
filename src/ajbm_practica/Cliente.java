/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajbm_practica;

/**
 *
 * @author alvis
 */
public class Cliente {

    //ATRIBUTO
    private String dni;
    private String nombre;
    private String direccion;
    private String localidad;
    private String codigoPostal;

    //CONSTRUCTOR
    public Cliente(String dni, String nombre, String direccion, String localidad, String codigoPostal) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
    }

    public Cliente(Cliente cCopia) {
        this.dni = cCopia.dni;
        this.nombre = cCopia.nombre;
        this.direccion = cCopia.direccion;
        this.localidad = cCopia.localidad;
        this.codigoPostal = cCopia.codigoPostal;
    }
    
    //GETTERS
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente");
        sb.append("--------------");
        sb.append("\n DNI: ").append(dni);
        sb.append("\n Nombre: ").append(nombre);
        sb.append("\n Direccion: ").append(direccion);
        sb.append("\n Localidad: ").append(localidad);
        sb.append("\n Codigo Postal:").append(codigoPostal);
        return sb.toString();
    }
    
    
}
