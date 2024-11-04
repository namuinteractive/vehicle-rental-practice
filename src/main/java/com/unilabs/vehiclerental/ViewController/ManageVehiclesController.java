package com.unilabs.vehiclerental.ViewController;

import com.unilabs.vehiclerental.Model.Auto;
import com.unilabs.vehiclerental.Model.Camioneta;
import com.unilabs.vehiclerental.Model.Empresa;
import com.unilabs.vehiclerental.Model.Moto;
import com.unilabs.vehiclerental.Model.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManageVehiclesController {

    @FXML
    private TextField txtMatricula;
    @FXML
    private ComboBox<String> comboBoxTipoVehiculo;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtAno;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;
    @FXML
    private ListView<String> listViewVehiculos;
    @FXML
    private Button btnVolver;

    private Empresa empresa; // Instancia de la empresa
    private ObservableList<String> vehiculosList; // Lista observable para la ListView

    @FXML
    private void initialize() {
        // Inicializar el comboBox con los tipos de vehículo
        comboBoxTipoVehiculo.setItems(FXCollections.observableArrayList("Auto", "Moto", "Camioneta"));

        // Inicializar la lista observable para la ListView
        vehiculosList = FXCollections.observableArrayList();
        listViewVehiculos.setItems(vehiculosList);

        // Obtener la instancia de la empresa
        empresa = Empresa.getEmpresa();

        // Actualizar la lista de vehículos en la ListView
        actualizarListaVehiculos();
    }

    @FXML
    private void onAgregar(ActionEvent event) {
        String matricula = txtMatricula.getText();
        String tipoVehiculo = comboBoxTipoVehiculo.getValue();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        int ano;
        try {
            ano = Integer.parseInt(txtAno.getText());
        } catch (NumberFormatException e) {
            // Mostrar un mensaje de error si el año no es válido
            return;
        }

        Vehiculo vehiculo = null;
        // Crear el vehículo según el tipo seleccionado
        switch (tipoVehiculo) {
            case "Auto":
                vehiculo = new Auto(matricula, marca, modelo, ano, 4); // Ejemplo, 4 puertas
                break;
            case "Moto":
                vehiculo = new Moto(matricula, marca, modelo, ano, true); // Ejemplo, automática
                break;
            case "Camioneta":
                vehiculo = new Camioneta(matricula, marca, modelo, ano, 1.5); // Ejemplo, 1.5 toneladas
                break;
            default:
                // Mostrar un mensaje de error si el tipo de vehículo no es válido
                return;
        }

        try {
            String mensaje = empresa.añadirVehiculo(vehiculo);
            // Mostrar un mensaje de éxito o error
            System.out.println(mensaje);
            actualizarListaVehiculos(); // Actualizar la lista en la ListView
        } catch (IllegalArgumentException e) {
            // Mostrar un mensaje de error si se produjo un error al agregar el vehículo
            System.err.println(e.getMessage());
        }

        // Limpiar los campos de entrada
        txtMatricula.clear();
        comboBoxTipoVehiculo.getSelectionModel().clearSelection();
        txtMarca.clear();
        txtModelo.clear();
        txtAno.clear();
    }

    @FXML
    private void onEliminar(ActionEvent event) {
        String matricula = txtMatricula.getText();

        // Buscar el vehículo por matrícula
        Vehiculo vehiculo = empresa.buscarVehiculo(matricula);

        if (vehiculo != null) {
            try {
                String mensaje = empresa.eliminarVehiculo(vehiculo);
                // Mostrar un mensaje de éxito o error
                System.out.println(mensaje);
                actualizarListaVehiculos(); // Actualizar la lista en la ListView
            } catch (IllegalArgumentException e) {
                // Mostrar un mensaje de error si se produjo un error al eliminar el vehículo
                System.err.println(e.getMessage());
            }
        } else {
            // Mostrar un mensaje de error si el vehículo no se encontró
            System.out.println("Vehículo no encontrado");
        }

        // Limpiar el campo de entrada
        txtMatricula.clear();
    }

    @FXML
    private void onEditar(ActionEvent event) {
        // Obtener la matrícula del vehículo a editar
        String matricula = txtMatricula.getText();

        // Buscar el vehículo por matrícula
        Vehiculo vehiculo = empresa.buscarVehiculo(matricula);

        if (vehiculo != null) {
            // Obtener los nuevos datos del vehículo
            String tipoVehiculo = comboBoxTipoVehiculo.getValue();
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano;
            try {
                ano = Integer.parseInt(txtAno.getText());
            } catch (NumberFormatException e) {
                // Mostrar un mensaje de error si el año no es válido
                return;
            }

            // Crear el nuevo vehículo con los nuevos datos
            Vehiculo nuevoVehiculo = null;
            switch (tipoVehiculo) {
                case "Auto":
                    nuevoVehiculo = new Auto(matricula, marca, modelo, ano, 4); // Ejemplo, 4 puertas
                    break;
                case "Moto":
                    nuevoVehiculo = new Moto(matricula, marca, modelo, ano, true); // Ejemplo, automática
                    break;
                case "Camioneta":
                    nuevoVehiculo = new Camioneta(matricula, marca, modelo, ano, 1.5); // Ejemplo, 1.5 toneladas
                    break;
                default:
                    // Mostrar un mensaje de error si el tipo de vehículo no es válido
                    return;
            }

            try {
                String mensaje = empresa.editarVehiculo(vehiculo, nuevoVehiculo);
                // Mostrar un mensaje de éxito o error
                System.out.println(mensaje);
                actualizarListaVehiculos(); // Actualizar la lista en la ListView
            } catch (IllegalArgumentException e) {
                // Mostrar un mensaje de error si se produjo un error al editar el vehículo
                System.err.println(e.getMessage());
            }
        } else {
            // Mostrar un mensaje de error si el vehículo no se encontró
            System.out.println("Vehículo no encontrado");
        }

        // Limpiar los campos de entrada
        txtMatricula.clear();
        comboBoxTipoVehiculo.getSelectionModel().clearSelection();
        txtMarca.clear();
        txtModelo.clear();
        txtAno.clear();
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

    private void actualizarListaVehiculos() {
        vehiculosList.clear();
        for (Vehiculo vehiculo : empresa.getListaVehiculos()) {
            vehiculosList.add(vehiculo.getMatricula() + " - " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        }
    }
}