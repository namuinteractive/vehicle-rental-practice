package com.unilabs.vehiclerental.ViewController;

import com.unilabs.vehiclerental.Model.Cliente;
import com.unilabs.vehiclerental.Model.Empresa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManageClientsController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCedula;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;
    @FXML
    private ListView<String> listViewClientes;
    @FXML
    private Button btnVolver;

    private Empresa empresa; // Instancia de la empresa
    private ObservableList<String> clientesList; // Lista observable para la ListView

    @FXML
    private void initialize() {
        // Inicializar la lista observable para la ListView
        clientesList = FXCollections.observableArrayList();
        listViewClientes.setItems(clientesList);

        // Obtener la instancia de la empresa
        empresa = Empresa.getEmpresa();

        // Actualizar la lista de clientes en la ListView
        actualizarListaClientes();
    }

    @FXML
    private void onAgregar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();

        // Crear el nuevo cliente
        Cliente cliente = new Cliente(nombre, cedula);

        try {
            String mensaje = empresa.añadirCliente(cliente);
            // Mostrar un mensaje de éxito o error
            System.out.println(mensaje);
            actualizarListaClientes(); // Actualizar la lista en la ListView
        } catch (IllegalArgumentException e) {
            // Mostrar un mensaje de error si se produjo un error al agregar el cliente
            System.err.println(e.getMessage());
        }

        // Limpiar los campos de entrada
        txtNombre.clear();
        txtCedula.clear();
    }

    @FXML
    private void onEliminar(ActionEvent event) {
        String cedula = txtCedula.getText();

        // Buscar el cliente por cédula
        Cliente cliente = empresa.buscarCliente(cedula);

        if (cliente != null) {
            try {
                String mensaje = empresa.eliminarCliente(cliente);
                // Mostrar un mensaje de éxito o error
                System.out.println(mensaje);
                actualizarListaClientes(); // Actualizar la lista en la ListView
            } catch (IllegalArgumentException e) {
                // Mostrar un mensaje de error si se produjo un error al eliminar el cliente
                System.err.println(e.getMessage());
            }
        } else {
            // Mostrar un mensaje de error si el cliente no se encontró
            System.out.println("Cliente no encontrado");
        }

        // Limpiar el campo de entrada
        txtCedula.clear();
    }

    @FXML
    private void onEditar(ActionEvent event) {
        // Obtener la cédula del cliente a editar
        String cedula = txtCedula.getText();

        // Buscar el cliente por cédula
        Cliente cliente = empresa.buscarCliente(cedula);

        if (cliente != null) {
            // Obtener los nuevos datos del cliente
            String nombre = txtNombre.getText();

            // Crear el nuevo cliente con los nuevos datos
            Cliente nuevoCliente = new Cliente(nombre, cedula);

            try {
                String mensaje = empresa.editarCliente(cliente, nuevoCliente);
                // Mostrar un mensaje de éxito o error
                System.out.println(mensaje);
                actualizarListaClientes(); // Actualizar la lista en la ListView
            } catch (IllegalArgumentException e) {
                // Mostrar un mensaje de error si se produjo un error al editar el cliente
                System.err.println(e.getMessage());
            }
        } else {
            // Mostrar un mensaje de error si el cliente no se encontró
            System.out.println("Cliente no encontrado");
        }

        // Limpiar los campos de entrada
        txtNombre.clear();
        txtCedula.clear();
    }

    @FXML
    private void onVolver(ActionEvent event) {
        try {
            // Cargar la vista del menú principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unilabs/vehiclerental/MainMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarListaClientes() {
        clientesList.clear();
        for (Cliente cliente : empresa.getListaClientes()) {
            clientesList.add(cliente.getNombre() + " - " + cliente.getCedula());
        }
    }
}