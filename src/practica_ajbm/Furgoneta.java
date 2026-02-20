/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

/**
 *
 * @author dam-29
 */
public class Furgoneta extends Mercancias {

    private boolean refrigerado;
    private Enumerados.Tamano tamanio;

    public Furgoneta(boolean refrigerado, Enumerados.Tamano tamanio, int pma, int volumen, String matricula, String marca, String modelo, int cilindrada) {
        super(pma, volumen, matricula, marca, modelo, cilindrada);
        this.refrigerado = false;
        this.tamanio = tamanio;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public Enumerados.Tamano getTamanio() {
        return tamanio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nFurgoneta: ");
        sb.append("\n").append(super.toString());
        sb.append("\nEsta refrigerado? ").append(refrigerado);
        sb.append("\nTamaño: ").append(tamanio);
        sb.append("");
        return sb.toString();
    }

}
