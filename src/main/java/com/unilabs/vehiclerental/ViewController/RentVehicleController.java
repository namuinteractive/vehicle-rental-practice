package com.unilabs.vehiclerental.ViewController;

import com.unilabs.vehiclerental.Model.Auto;
import com.unilabs.vehiclerental.Model.Camioneta;
import com.unilabs.vehiclerental.Model.Moto;
import com.unilabs.vehiclerental.Model.Reserva;
import com.unilabs.vehiclerental.Model.Vehiculo;

import javafx.collections.FXCollections;
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

public class RentVehicleController {

    @FXML
    private TextField txtMatricula;
    @FXML
    private ComboBox<String> comboBoxTipoVehiculo;
    @FXML
    private TextField txtDias;
    @FXML
    private Label lblCostoTotal;

    private Reserva reserva;

    @FXML
    private void initialize() {
    // Inicializar el comboBox con los tipos de vehículo
    comboBoxTipoVehiculo.setItems(FXCollections.observableArrayList("Auto", "Moto", "Camioneta"));
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

        // Calcular el costo y mostrarlo
        reserva = new Reserva(vehiculo, dias);
        lblCostoTotal.setText("Costo Total: $" + reserva.getCostoTotal());
    }

    @FXML
    private void onRent(ActionEvent event) {
        // Aquí podríamos agregar la lógica para guardar la reserva o mostrar un mensaje de confirmación
        System.out.println("Reserva realizada: " + reserva.getVehiculo().getMatricula() + " por " + reserva.getDias() + " días.");
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

}
