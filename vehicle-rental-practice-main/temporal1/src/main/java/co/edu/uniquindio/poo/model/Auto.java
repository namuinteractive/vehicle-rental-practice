package co.edu.uniquindio.poo.model;

public class Auto extends Vehiculo{

    private int numPuertas;

    public Auto(String matricula, String marca, String modelo, int añoFabricacion, int numPuertas) {
        super(matricula, marca, modelo, añoFabricacion);
        this.numPuertas = numPuertas;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    public double calcularCostoReserva(){
        double costo = 0.0;

        return costo;
    }

}
