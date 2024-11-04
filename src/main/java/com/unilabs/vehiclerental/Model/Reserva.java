package com.unilabs.vehiclerental.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private int id;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double costoTotal;
    private static int nextId = 1;

    public Reserva(Vehiculo vehiculo, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = nextId++;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costoTotal = vehiculo.calcularCostoReserva((int) ChronoUnit.DAYS.between(fechaInicio, fechaFin));
    }

    // Getters

    public int getId() {
        return id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double getCostoTotal() {
        return costoTotal;
    }
}