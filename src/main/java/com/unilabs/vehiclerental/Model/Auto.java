package com.unilabs.vehiclerental.Model;

public class Auto extends Vehiculo {
    private int numPuertas;

    public Auto(String matricula, String marca, String modelo, int anoFabricacion, int numPuertas) {
        super(matricula, marca, modelo, anoFabricacion);
        this.numPuertas = numPuertas;
    }

    @Override
    public double calcularCostoReserva(int dias) {
        double tarifaBase = 50; // Tarifa base por día
        return tarifaBase * dias;
    }
}
