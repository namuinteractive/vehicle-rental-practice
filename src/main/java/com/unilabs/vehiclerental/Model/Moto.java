package com.unilabs.vehiclerental.Model;

public class Moto extends Vehiculo {
    private boolean esAutomatica;

    public Moto(String matricula, String marca, String modelo, int anoFabricacion, boolean esAutomatica) {
        super(matricula, marca, modelo, anoFabricacion);
        this.esAutomatica = esAutomatica;
    }

    @Override
    public double calcularCostoReserva(int dias) {
        double tarifaBase = 30; // Tarifa base por día
        if (esAutomatica) {
            tarifaBase += 10; // Tarifa adicional si es automática
        }
        return tarifaBase * dias;
    }
}
