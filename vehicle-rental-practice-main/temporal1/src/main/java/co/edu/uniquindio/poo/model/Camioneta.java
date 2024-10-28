 package co.edu.uniquindio.poo.model;

public class Camioneta extends Vehiculo{

    private double capacidadCarga;

    public Camioneta(String matricula, String marca, String modelo, int añoFabricacion, double capacidadCarga) {
        super(matricula, marca, modelo, añoFabricacion);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public double calcularCostoReserva(){
        double costo = 0.0;

        return costo;
    }
}
