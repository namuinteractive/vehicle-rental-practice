package com.unilabs.vehiclerental.Model;

import java.util.LinkedList;

public class Empresa {

    private String nombre;
    private LinkedList<Cliente> listaClientes;
    private LinkedList<Reserva> listaReservas;
    private LinkedList<Vehiculo> listaVehiculos;


    public Empresa(String nombre) {
        this.nombre = nombre;
        listaClientes = new LinkedList<>();
        listaReservas = new LinkedList<>();
        listaVehiculos = new LinkedList<>();
    }


    // CRUD Cliente

    /**
     * Añadir Cliente
     * @param cliente
     * @return
     * @throws IllegalArgumentException
     */
    public String añadirCliente(Cliente cliente) throws IllegalArgumentException {
        String mensaje = "Se añadió correctamente";
        if (cliente == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }
        listaClientes.add(cliente);
        return mensaje;
    }

    /**
     * Eliminar Cliente
     * @param cliente
     * @return
     * @throws IllegalArgumentException
     */
    public String eliminarCliente(Cliente cliente) throws IllegalArgumentException {
        String mensaje = "Se eliminó correctamente";
        if (cliente == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }
        if (!listaClientes.contains(cliente)){
            mensaje = "No se encuentra en la lista";
        }
        listaClientes.remove(cliente);
        return mensaje;
    }

    /**
     * Buscar Cliente por cedula
     * @param cedula
     * @return
     */
    public Cliente buscarCliente(String cedula){
        return listaClientes.stream().filter(clienteaux -> clienteaux.getCedula().equals(cedula)).findFirst().orElse(null);
    }

    /**
     * Editar cliente, borra uno y añade el nuevo
     * @param cliente
     * @param nuevoCliente
     * @return
     * @throws IllegalArgumentException
     */
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

    // CRUD Vehículo

    /**
     * Añadir Vehículo
     * @param vehiculo
     * @return
     * @throws IllegalArgumentException
     */
    public String añadirVehiculo(Vehiculo vehiculo) throws IllegalArgumentException {
        String mensaje = "Se añadió correctamente";
        if (vehiculo == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }
        listaVehiculos.add(vehiculo);
        return mensaje;
    }

    /**
     * Eliminar Vehículo
     * @param vehiculo
     * @return
     * @throws IllegalArgumentException
     */
    public String eliminarVehiculo(Vehiculo vehiculo) throws IllegalArgumentException {
        String mensaje = "Se eliminó correctamente";
        if (vehiculo == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }
        if (!listaVehiculos.contains(vehiculo)){
            mensaje = "No se encuentra en la lista";
        }
        listaVehiculos.remove(vehiculo);
        return mensaje;
    }

    /**
     * Buscar Vehículo por matrícula
     * @param matricula
     * @return
     */
    public Vehiculo buscarVehiculo(String matricula){
        return listaVehiculos.stream().filter(vehiculoaux -> vehiculoaux.getMatricula().equals(matricula)).findFirst().orElse(null);
    }

    /**
     * Editar vehículo, borra uno y añade el nuevo
     * @param vehiculo
     * @param nuevoVehiculo
     * @return
     * @throws IllegalArgumentException
     */
    public String editarVehiculo(Vehiculo vehiculo, Vehiculo nuevoVehiculo) throws IllegalArgumentException{
        String mensaje = "editado correctamente";

        if (vehiculo == null || nuevoVehiculo == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }

        if (!listaVehiculos.contains(vehiculo)){
            mensaje = "No se encuentra en la lista";
        }

        listaVehiculos.remove(vehiculo);
        listaVehiculos.add(nuevoVehiculo);
        return mensaje;
    }

    private static Empresa instance;

    public static Empresa getEmpresa() {
        if (instance == null) {
            instance = new Empresa("Empresa de Alquiler"); // Nombre de la empresa
        }
        return instance;
    }

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