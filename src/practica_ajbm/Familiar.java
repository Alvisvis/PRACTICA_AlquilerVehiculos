/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

/**
 *
 * @author dam-29
 */
public class Familiar extends Turismo {

    private int nPlazas;
    private boolean sillaBebe;

    public Familiar(int nPlazas, boolean sillaBebe, int nPuertas, Enumerados.TipoCombustible tipoCombustible, String matricula, String marca, String modelo, int cilindrada) {
        super(nPuertas, tipoCombustible, matricula, marca, modelo, cilindrada);
        this.nPlazas = nPlazas;
        this.sillaBebe = false;
    }

    public int getnPlazas() {
        return nPlazas;
    }

    public boolean isSillaBebe() {
        return sillaBebe;
    }

    public Enumerados.TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    public void setSillaBebe(boolean sillaBebe) {
        this.sillaBebe = sillaBebe;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Familiar: ");
        sb.append(super.toString());
        sb.append("\n Numero de plazas: ").append(nPlazas);
        sb.append("\n Tiene silla de bebe? ").append(sillaBebe);
        sb.append("");
        return sb.toString();
    }

}