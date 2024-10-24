package co.edu.uniquindio.poo.model;

import java.util.LinkedList;

public class Empresa {

    private String nombre;
    private LinkedList<Cliente> listaClientes;
    private LinkedList<Reserva> listaReservas;
    private LinkedList<Vehiculo> listaVehiculos;


    public Empresa(String nombre) {
        this.nombre = nombre;
        LinkedList<Cliente> listaClientes = new LinkedList<>();
        LinkedList<Reserva> listaReservas = new LinkedList<>();
        LinkedList<Vehiculo> listaVehiculos = new LinkedList<>();
    }



    


    //CRUD Cliente 

    public String añadirCliente(Cliente cliente) throws IllegalArgumentException {
        String mensaje = "Se añadió correctamente";
        if (cliente == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }
        listaClientes.add(cliente);
        return mensaje;
    }

    public String eliminarCliente(Cliente cliente) throws IllegalArgumentException {
        String mensaje = "Se eliminó correctamente";
        if (cliente == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }
        if (!listaClientes.contains(cliente)){
            mensaje = "No se encuentra en la lista";
        }
        return mensaje;
    }

    public Cliente buscarCliente(String cedula){
        return listaClientes.stream().filter(clienteaux -> clienteaux.getCedula().equals(cedula)).findFirst().orElse(null);
    }

    public String editarCliente(Cliente cliente, Cliente nuevoCliente) throws IllegalArgumentException{
        String mensaje = "editado correctamente";

        if (cliente == null || nuevoCliente == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }

        if (!listaClientes.contains(cliente)){
            mensaje = "No se encuentra en la lista";
        }

        listaClientes.remove(cliente);
        listaClientes.add(nuevoCliente);
        return mensaje;
    }

    //CRUD Reserva


    //CRUD Vehiculo 
    


    //Setters & Getters

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public LinkedList<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(LinkedList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    public LinkedList<Reserva> getListaReservas() {
        return listaReservas;
    }
    public void setListaReservas(LinkedList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }
    public LinkedList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }
    public void setListaVehiculos(LinkedList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

}
