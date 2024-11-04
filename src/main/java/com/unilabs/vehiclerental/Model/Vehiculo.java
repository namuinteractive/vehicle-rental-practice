package com.unilabs.vehiclerental.Model;

public abstract class Vehiculo {
    private String matricula;
    private String marca;
    private String modelo;
    private int anoFabricacion;

    public Vehiculo(String matricula, String marca, String modelo, int anoFabricacion) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacion = anoFabricacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnoFabricacion() {
        return anoFabricacion;
    }

    // Método abstracto para calcular el costo de la reserva
    public abstract double calcularCostoReserva(int dias);
}
