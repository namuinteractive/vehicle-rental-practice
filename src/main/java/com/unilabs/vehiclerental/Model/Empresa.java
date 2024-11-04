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



    


    //CRUD Cliente 

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

    //CRUD Reserva

    /**
     * Añadir reserva
     * @param reserva
     * @return
     * @throws IllegalArgumentException
     */
    public String añadirReserva(Reserva reserva) throws IllegalArgumentException {
        String mensaje = "Se añadió correctamente";
        if (reserva == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }
        listaReservas.add(reserva);
        return mensaje;
    }

    /**
     * Eliminar reserva
     * @param reserva
     * @return
     * @throws IllegalArgumentException
     */
    public String eliminarReserva(Reserva reserva) throws IllegalArgumentException {
        String mensaje = "Se eliminó correctamente";
        if (reserva == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }
        if (!listaReservas.contains(reserva)){
            mensaje = "No se encuentra en la lista";
        }
        return mensaje;
    }

    /**
     * Busca una reserva por su codigo
     * @param codigo
     * @return
     */
    public Reserva buscarReserva(String codigo){
        return listaReservas.stream().filter(reservaux -> reservaux.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    /**
     * Edita una reserva, la borra y añade una con los datos nuevos
     * @param reserva
     * @param nuevaReserva
     * @return
     * @throws IllegalArgumentException
     */
    public String editarReserva(Reserva reserva, Reserva nuevaReserva) throws IllegalArgumentException{
        String mensaje = "editado correctamente";

        if (reserva == null || nuevaReserva == null) {
            throw new IllegalArgumentException("No se aceptan objetos nulos");
        }

        if (!listaReservas.contains(reserva)){
            mensaje = "No se encuentra en la lista";
        }

        listaReservas.remove(reserva);
        listaReservas.add(nuevaReserva);
        return mensaje;
    }

    //CRUD Vehiculo 
    
    /**
     * Añade un vehiculo
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
     * Elimina un Vehiculo de la lista
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
        return mensaje;
    }

    /**
     * Busca un vehiculo por su matricula 
     * @param matricula
     * @return
     */
    public Vehiculo buscarVehiculo(String matricula){
        return listaVehiculos.stream().filter(caraux -> caraux.getMatricula().equals(matricula)).findFirst().orElse(null);
    }

    /**
     * Edita un vehiculo, lo borra y añade uno con datos nuevos
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


//Metodo Reserva 

    /**
     * Build reserva
     * @param clienteReserva
     * @param vehiculoReserva
     * @param dias
     * @return
     */
    public String hacerReserva(Cliente clienteReserva, Vehiculo vehiculoReserva, int dias){
        String mensaje = "Reserva exitosa";


        
        return mensaje;
    }

    public void buildReserva(String codigo){

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
