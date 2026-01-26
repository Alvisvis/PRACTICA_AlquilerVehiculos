/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_alquilervehiculos;

/**
 *
 * @author dam1
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
        this.disponible = true;
    }

    public Vehiculo(Vehiculo Vcopia) {
        this.matricula = Vcopia.matricula;
        this.marca = Vcopia.marca;
        this.modelo = Vcopia.modelo;
        this.cilindrada = Vcopia.cilindrada;
        this.disponible = Vcopia.disponible;
    }

    //GETTER AND SETTER
    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

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

    //TOSTRING
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehiculo:\n");
        sb.append("\nMatricula: ").append(matricula);
        sb.append("\nMarca: ").append(marca);
        sb.append("\nModelo: ").append(modelo);
        sb.append("\nCilindrada: ").append(cilindrada);
        sb.append("\nDisponible: ").append(disponible);
        return sb.toString();
    }

}
