/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

import java.io.Serializable;

/**
 *
 * @author dam-29
 */
public abstract class Vehiculo implements Serializable {

    //VARIABLES
    /*
    Matricula del vehiculo
     */
    private String matricula;
    /*
    Marca del vehiculo
     */
    private String marca;
    /*
    Modelo del vehiculo
     */
    private String modelo;
    /*
    Cilindrada del vehiculo
     */
    private int cilindrada;
    /*
    Disponibilidad de un vehiculo
     */
    private boolean disponible;
    /*
    Dato si esta de baja un vehiculo
     */
    private boolean baja;

    //CONSTRUCTORES
    /**
     * Constructor de la clase con los datos de esta
     *
     * @param matricula
     * @param marca
     * @param modelo
     * @param cilindrada
     */
    public Vehiculo(String matricula, String marca, String modelo, int cilindrada) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.disponible = true;
        this.baja = false;
    }

    /**
     * Constructor copia de la clase
     *
     * @param Vcopia
     */
    public Vehiculo(Vehiculo Vcopia) {
        this.matricula = Vcopia.matricula;
        this.marca = Vcopia.marca;
        this.modelo = Vcopia.modelo;
        this.cilindrada = Vcopia.cilindrada;
        this.disponible = Vcopia.disponible;
        this.baja = Vcopia.baja;
    }

    /**
     * Devuelve si el vehiculo esta disponible
     *
     * @return disponibilidad del vehiculo
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Modifica si esta disponible el vehiculo
     *
     * @param disponible
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Devuelve la matricula del vehiculo
     *
     * @return matricula del vehiculo
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Devuelve la marca del vehiculo
     * @return marca del vehiculo
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Devuelve el modelo de un vehiculo
     * @return 
     */
    public String getModelo() {
        return modelo;
    }
    /**
     * Devuelve la cilindrada de un vehiculo
     * @return 
     */
    public int getCilindrada() {
        return cilindrada;
    }
    /**
     * Devuelve si el vehiculo esta dado de baja
     * @return baja del vehiculo
     */
    public boolean isBaja() {
        return baja;
    }
    /**
     * Modifica la baja del vehiculo
     * @param baja 
     */
    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    /**
     * Devuelve la cadena de texto de los datos de la clase
     *
     * @return Datos de la clase en formato texto
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMatricula: ").append(matricula);
        sb.append("\nMarca: ").append(marca);
        sb.append("\nModelo: ").append(modelo);
        sb.append("\nCilindrada: ").append(cilindrada);
        sb.append("\nDisponible: ").append(disponible ? "Si" : "No");
        sb.append("\nBaja: ").append(baja ? "Si" : "No");
        return sb.toString();
    }

    /**
     * Devuelve los datos de la clase en formato fecha. Estos datos son escritos
     * en un archivo de tipo .txt o .dat
     *
     * @return Devuelve los datos de la clase para un archivo
     */
    public String toEscribir() {
        return matricula + "#" + marca + "#" + modelo + "#" + cilindrada + "#" + disponible + "#" + baja + "#";
    }

}
