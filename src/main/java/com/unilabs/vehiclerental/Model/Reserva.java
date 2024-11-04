package com.unilabs.vehiclerental.Model;

public class Reserva {
    private Vehiculo vehiculo;
    private int dias;
    private double costoTotal;

    public Reserva(Vehiculo vehiculo, int dias) {
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.costoTotal = vehiculo.calcularCostoReserva(dias);
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getDias() {
        return dias;
    }

    public double getCostoTotal() {
        return costoTotal;
    }
}
