/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

/**
 *
 * @author dam-29
 */
public class Mercancias extends Vehiculo {

    private int pma;
    private int volumen;

    public Mercancias(int pma, int volumen, String matricula, String marca, String modelo, int cilindrada) {
        super(matricula, marca, modelo, cilindrada);
        this.pma = pma;
        this.volumen = volumen;
    }

    public Mercancias(int pma, int volumen, Vehiculo Vcopia) {
        super(Vcopia);
        this.pma = pma;
        this.volumen = volumen;
    }

    public int getPma() {
        return pma;
    }

    public int getVolumen() {
        return volumen;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tMercancias");
        sb.append("-----------------------");
        sb.append("\n Peso Maximo Autorizado: ").append(pma);
        sb.append("\n Volumen de mercancia: ").append(volumen);
        sb.append("");
        return sb.toString();
    }

}