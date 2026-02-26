/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajbm_practica;

/**
 *
 * @author alvis
 */
public class Vehiculo {

    //ATRIBUTOS
    private String matricula;
    private String marca;
    private String modelo;
    private int cilindrada;
    private boolean disponible;

    //CONSTRUCTORES
    public Vehiculo(String matricula, String marca, String modelo, int cilindrada) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.disponible = false;
    }

    public Vehiculo(Vehiculo vCopia) {
        this.matricula = vCopia.matricula;
        this.marca = vCopia.marca;
        this.modelo = vCopia.modelo;
        this.cilindrada = vCopia.cilindrada;
        this.disponible = vCopia.disponible;
    }

    //SETTER&GETTERS

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehiculo");
        sb.append("--------------");
        sb.append("\n Matricula: ").append(matricula);
        sb.append("\n Marca: ").append(marca);
        sb.append("\n Modelo: ").append(modelo);
        sb.append("\n Cilindrada: ").append(cilindrada);
        sb.append("\n ¿Esta disponible?").append(disponible ? "Si" : "No");
        return sb.toString();
    }
    
    
}
