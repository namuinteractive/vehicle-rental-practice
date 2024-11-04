package com.unilabs.vehiclerental.Model;

public class Moto extends Vehiculo{

    private boolean cajaAutomatica;
    private double tarifaBase;
    private double tarifaAdicional;

    public Moto(String matricula, String marca, String modelo, int añoFabricacion, boolean cajaAutomatica) {
        super(matricula, marca, modelo, añoFabricacion);
        this.cajaAutomatica = cajaAutomatica;
    }

    public boolean isCajaAutomatica() {
        return cajaAutomatica;
    }

    public void setCajaAutomatica(boolean cajaAutomatica) {
        this.cajaAutomatica = cajaAutomatica;
    }

    public double calcularCostoReserva(){
        double costo= 0.0;
        if (cajaAutomatica) {
            costo = tarifaBase + tarifaAdicional;
        }
        return costo;
    }
    
}
