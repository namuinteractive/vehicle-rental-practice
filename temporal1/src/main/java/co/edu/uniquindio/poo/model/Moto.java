package co.edu.uniquindio.poo.model;

public class Moto extends Vehiculo{

    private boolean cajaAutomatica;

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

        return costo;
    }
    
}
