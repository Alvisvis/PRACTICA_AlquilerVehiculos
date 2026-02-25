/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

/**
 *
 * @author dam-29
 */
public class Turismo extends Vehiculo {

    protected int nPuertas;
    protected Enumerados.TipoCombustible tipoCombustible;

    public Turismo(int nPuertas, Enumerados.TipoCombustible tipoCombustible, String matricula, String marca, String modelo, int cilindrada) {
        super(matricula, marca, modelo, cilindrada);
        this.nPuertas = nPuertas;
        this.tipoCombustible = tipoCombustible;
    }

    public int getnPuertas() {
        return nPuertas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTurismo: ");
        sb.append("\n").append(super.toString());
        sb.append("\nNumero de puertas: ").append(nPuertas);
        sb.append("\nTipo de combustible: ").append(tipoCombustible);
        sb.append("");
        return sb.toString();
    }

    public String toEscribir() {
        return "Turismo#" + super.toEscribir() + "#" + nPuertas + "#" + tipoCombustible;
    }

}
