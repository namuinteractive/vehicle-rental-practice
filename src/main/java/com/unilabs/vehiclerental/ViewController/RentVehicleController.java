package com.unilabs.vehiclerental.ViewController;

import com.unilabs.vehiclerental.Model.Auto;
import com.unilabs.vehiclerental.Model.Camioneta;
import com.unilabs.vehiclerental.Model.Cliente;
import com.unilabs.vehiclerental.Model.Empresa;
import com.unilabs.vehiclerental.Model.Moto;
import com.unilabs.vehiclerental.Model.Reserva;
import com.unilabs.vehiclerental.Model.Vehiculo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.time.LocalDate;

public class RentVehicleController {

    @FXML
    private TextField txtMatricula;
    @FXML
    private ComboBox<String> comboBoxTipoVehiculo;
    @FXML
    private TextField txtDias;
    @FXML
    private Label lblCostoTotal;
    @FXML
    private ComboBox<String> comboBoxClientes; // ComboBox para seleccionar el cliente

    private Reserva reserva;
    private Empresa empresa; // Instancia de la empresa
    private ObservableList<String> clientesList; // Lista observable para el ComboBox de clientes

    @FXML
    private void initialize() {
        // Inicializar el comboBox con los tipos de vehículo
        comboBoxTipoVehiculo.setItems(FXCollections.observableArrayList("Auto", "Moto", "Camioneta"));

        // Inicializar la lista observable para el ComboBox de clientes
        clientesList = FXCollections.observableArrayList();
        comboBoxClientes.setItems(clientesList);

        // Obtener la instancia de la empresa
        empresa = Empresa.getEmpresa();

        // Actualizar la lista de clientes en el ComboBox
        actualizarListaClientes();
    }

    @FXML
    private void onCalcularCosto(ActionEvent event) {
        String matricula = txtMatricula.getText();
        String tipoVehiculo = comboBoxTipoVehiculo.getValue();
        int dias;

        try {
            dias = Integer.parseInt(txtDias.getText());
        } catch (NumberFormatException e) {
            lblCostoTotal.setText("Por favor, ingresa un número válido de días.");
            return;
        }

        Vehiculo vehiculo = null;

        // Crear el vehículo según el tipo seleccionado
        switch (tipoVehiculo) {
            case "Auto":
                vehiculo = new Auto(matricula, "Marca Auto", "Modelo Auto", 2020, 4); // Ejemplo
                break;
            case "Moto":
                vehiculo = new Moto(matricula, "Marca Moto", "Modelo Moto", 2020, true); // Ejemplo, automática
                break;
            case "Camioneta":
                vehiculo = new Camioneta(matricula, "Marca Camioneta", "Modelo Camioneta", 2020, 1.5); // Ejemplo
                break;
            default:
                lblCostoTotal.setText("Selecciona un tipo de vehículo.");
                return;
        }

        // Obtener la fecha actual como fecha de inicio de la reserva
        LocalDate fechaInicio = LocalDate.now();

        // Calcular la fecha de fin de la reserva
        LocalDate fechaFin = fechaInicio.plusDays(dias);

        // Crear una reserva temporal para calcular el costo
        reserva = new Reserva(vehiculo, null, fechaInicio, fechaFin);

        // Calcular el costo y mostrarlo
        lblCostoTotal.setText("Costo Total: $" + reserva.getCostoTotal());
    }

    @FXML
    private void onRent(ActionEvent event) {
        String clienteSeleccionado = comboBoxClientes.getValue();

        // Buscar el cliente seleccionado
        Cliente cliente = null;
        for (Cliente c : empresa.getListaClientes()) {
            if (c.getNombre().equals(clienteSeleccionado)) {
                cliente = c;
                break;
            }
        }

        // Validar si se seleccionó un cliente
        if (cliente == null) {
            lblCostoTotal.setText("Por favor, selecciona un cliente.");
            return;
        }

        // Obtener la fecha actual como fecha de inicio de la reserva
        LocalDate fechaInicio = LocalDate.now();

        // Calcular la fecha de fin de la reserva
        int dias = Integer.parseInt(txtDias.getText()); // Obtener los días del TextField
        LocalDate fechaFin = fechaInicio.plusDays(dias); // Calcular la fecha de fin

        // Validar la disponibilidad del vehículo
        if (!estaDisponible(reserva.getVehiculo(), fechaInicio, fechaFin)) {
            lblCostoTotal.setText("El vehículo no está disponible para esas fechas.");
            return;
        }

        // Crear la nueva reserva
        Reserva nuevaReserva = new Reserva(reserva.getVehiculo(), cliente, fechaInicio, fechaFin);

        try {
            // Guardar la reserva en la empresa
            String mensaje = empresa.añadirReserva(nuevaReserva);
            // Mostrar un mensaje de éxito o error
            System.out.println(mensaje);

            // Mostrar un mensaje de confirmación al usuario
            lblCostoTotal.setText("Reserva realizada con éxito.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void onVolver(ActionEvent event) {
        try {
            // Cargar la vista del menú principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unilabs/vehiclerental/MainMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) lblCostoTotal.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para validar la disponibilidad del vehículo
    private boolean estaDisponible(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        // Verificar si hay alguna reserva que coincida con las fechas de inicio y fin
        for (Reserva reserva : empresa.getListaReservas()) {
            if (reserva.getVehiculo().equals(vehiculo) &&
                    !fechaInicio.isAfter(reserva.getFechaFin()) &&
                    !fechaFin.isBefore(reserva.getFechaInicio())) {
                return false; // El vehículo no está disponible
            }
        }
        return true; // El vehículo está disponible
    }

    // Método para actualizar la lista de clientes en el ComboBox
    private void actualizarListaClientes() {
        clientesList.clear();
        for (Cliente cliente : empresa.getListaClientes()) {
            clientesList.add(cliente.getNombre());
        }
    }
}