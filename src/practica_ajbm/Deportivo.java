/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_ajbm;

/**
 *
 * @author dam-29
 */
public class Deportivo extends Turismo {

    private boolean descapotable;
    private Enumerados.CajaCambio cambio;

    public Deportivo(Enumerados.CajaCambio cambio, boolean descapotable, int nPuertas, Enumerados.TipoCombustible tipoCombustible, String matricula, String marca, String modelo, int cilindrada) {
        super(nPuertas, tipoCombustible, matricula, marca, modelo, cilindrada);
        this.descapotable = descapotable;
        this.cambio = cambio;
    }

    public boolean isDescapotable() {
        return descapotable;
    }

    public Enumerados.CajaCambio getCambio() {
        return cambio;
    }

    public Enumerados.TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nDeportivo: ");
        sb.append("\n").append(super.toString());
        sb.append("\nDescapotable:").append(descapotable);
        sb.append("\nCambio: ").append(cambio);
        return sb.toString();
    }

}
