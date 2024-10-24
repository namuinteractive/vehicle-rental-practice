package co.edu.uniquindio.poo.model;

public class Reserva {

    private String codigo;
    private double costo;


    public Reserva(String codigo, double costo) {
        this.codigo = codigo;
        this.costo = costo;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public double getCosto() {
        return costo;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }

    
}
