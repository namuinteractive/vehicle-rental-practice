package com.unilabs.vehiclerental.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private Button btnRentVehicle;

    @FXML
    private Button btnManageVehicles;

    @FXML
    private Button btnManageClients;

    @FXML
    private Button btnExit;

    // Métodos de manejo de eventos
    @FXML
    private void onRentVehicle(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RentVehicle.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnRentVehicle.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Rentar Vehículo");
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    private void onManageVehicles(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageVehicles.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnManageVehicles.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestionar Vehículos");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onManageClients(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageClients.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnManageClients.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestionar Clientes");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onExit(ActionEvent event) {
        System.out.println("Exit button clicked");
        System.exit(0);
    }
}
