package com.unilabs.vehiclerental.Model;

public class Camioneta extends Vehiculo {
    private double capacidadCarga;

    public Camioneta(String matricula, String marca, String modelo, int anoFabricacion, double capacidadCarga) {
        super(matricula, marca, modelo, anoFabricacion);
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public double calcularCostoReserva(int dias) {
        double tarifaBase = 80; // Tarifa base por día
        double extraPorTonelada = 0.1; // Porcentaje adicional por tonelada de capacidad
        return tarifaBase * dias * (1 + capacidadCarga * extraPorTonelada);
    }
}
