package com.unilabs.vehiclerental.ViewController;

import com.unilabs.vehiclerental.Model.Cliente;
import com.unilabs.vehiclerental.Model.Empresa;
import com.unilabs.vehiclerental.Model.Reserva;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ManageReservationsController {

    @FXML
    private ComboBox<String> comboBoxClientes;
    @FXML
    private ComboBox<String> comboBoxVehiculos;
    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private DatePicker datePickerFin;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;
    @FXML
    private ListView<String> listViewReservas;
    @FXML
    private Button btnVolver;

    private Empresa empresa; // Instancia de la empresa
    private ObservableList<String> reservasList; // Lista observable para la ListView
    private ObservableList<String> clientesList; // Lista observable para el ComboBox de clientes
    private ObservableList<String> vehiculosList; // Lista observable para el ComboBox de vehículos

    @FXML
    private void initialize() {
        // Inicializar la lista observable para la ListView
        reservasList = FXCollections.observableArrayList();
        listViewReservas.setItems(reservasList);

        // Inicializar las listas observables para los ComboBox
        clientesList = FXCollections.observableArrayList();
        comboBoxClientes.setItems(clientesList);

        vehiculosList = FXCollections.observableArrayList();
        comboBoxVehiculos.setItems(vehiculosList);

        // Obtener la instancia de la empresa
        empresa = Empresa.getEmpresa();

        // Actualizar las listas en los ComboBox y la ListView
        actualizarListas();
    }

    @FXML
    private void onAgregar(ActionEvent event) {
        // Obtener los datos de la reserva
        String clienteSeleccionado = comboBoxClientes.getValue();
        String vehiculoSeleccionado = comboBoxVehiculos.getValue();
        LocalDate fechaInicio = datePickerInicio.getValue();
        LocalDate fechaFin = datePickerFin.getValue();

        // Buscar el cliente y el vehículo seleccionados
        Cliente cliente = null;
        for (Cliente c : empresa.getListaClientes()) {
            if (c.getNombre().equals(clienteSeleccionado)) {
                cliente = c;
                break;
            }
        }

        Vehiculo vehiculo = null;
        for (Vehiculo v : empresa.getListaVehiculos()) {
            if (v.getMatricula().equals(vehiculoSeleccionado)) {
                vehiculo = v;
                break;
            }
        }

        // Validar la disponibilidad del vehículo
        if (vehiculo != null && !estaDisponible(vehiculo, fechaInicio, fechaFin)) {
            // Mostrar un mensaje de error si el vehículo no está disponible
            System.out.println("El vehículo no está disponible para esas fechas.");
            return;
        }

        // Crear la nueva reserva
        Reserva reserva = new Reserva(vehiculo, cliente, fechaInicio, fechaFin);

        try {
            String mensaje = empresa.añadirReserva(reserva);
            // Mostrar un mensaje de éxito o error
            System.out.println(mensaje);
            actualizarListas(); // Actualizar las listas en los ComboBox y la ListView
        } catch (IllegalArgumentException e) {
            // Mostrar un mensaje de error si se produjo un error al agregar la reserva
            System.err.println(e.getMessage());
        }

        // Limpiar los campos de entrada
        comboBoxClientes.getSelectionModel().clearSelection();
        comboBoxVehiculos.getSelectionModel().clearSelection();
        datePickerInicio.setValue(null);
        datePickerFin.setValue(null);
    }

    @FXML
    private void onEliminar(ActionEvent event) {
        // Obtener el ID de la reserva a eliminar
        String reservaSeleccionada = listViewReservas.getSelectionModel().getSelectedItem();
        int idReserva = obtenerIdReserva(reservaSeleccionada);

        // Buscar la reserva por ID
        Reserva reserva = empresa.buscarReserva(idReserva);

        if (reserva != null) {
            try {
                String mensaje = empresa.eliminarReserva(reserva);
                // Mostrar un mensaje de éxito o error
                System.out.println(mensaje);
                actualizarListas(); // Actualizar las listas en los ComboBox y la ListView
            } catch (IllegalArgumentException e) {
                // Mostrar un mensaje de error si se produjo un error al eliminar la reserva
                System.err.println(e.getMessage());
            }
        } else {
            // Mostrar un mensaje de error si la reserva no se encontró
            System.out.println("Reserva no encontrada");
        }

        // Limpiar el campo de entrada
        listViewReservas.getSelectionModel().clearSelection();
    }

    @FXML
    private void onEditar(ActionEvent event) {
        // Obtener la información de la reserva seleccionada
        String reservaSeleccionada = listViewReservas.getSelectionModel().getSelectedItem();

        // Buscar la reserva por ID
        Reserva reserva = empresa.buscarReserva(obtenerIdReserva(reservaSeleccionada));

        if (reserva != null) {
            // Obtener los nuevos datos de la reserva
            String clienteSeleccionado = comboBoxClientes.getValue();
            String vehiculoSeleccionado = comboBoxVehiculos.getValue();
            LocalDate fechaInicio = datePickerInicio.getValue();
            LocalDate fechaFin = datePickerFin.getValue();

            // Buscar el cliente y el vehículo seleccionados
            Cliente cliente = null;
            for (Cliente c : empresa.getListaClientes()) {
                if (c.getNombre().equals(clienteSeleccionado)) {
                    cliente = c;
                    break;
                }
            }

            Vehiculo vehiculo = null;
            for (Vehiculo v : empresa.getListaVehiculos()) {
                if (v.getMatricula().equals(vehiculoSeleccionado)) {
                    vehiculo = v;
                    break;
                }
            }

            // Validar la disponibilidad del vehículo
            if (vehiculo != null && !estaDisponible(vehiculo, fechaInicio, fechaFin)) {
                // Mostrar un mensaje de error si el vehículo no está disponible
                System.out.println("El vehículo no está disponible para esas fechas.");
                return;
            }

            // Crear la nueva reserva con los nuevos datos
            Reserva nuevaReserva = new Reserva(vehiculo, cliente, fechaInicio, fechaFin);

            try {
                String mensaje = empresa.editarReserva(reserva, nuevaReserva);
                // Mostrar un mensaje de éxito o error
                System.out.println(mensaje);
                actualizarListas(); // Actualizar las listas en los ComboBox y la ListView
            } catch (IllegalArgumentException e) {
                // Mostrar un mensaje de error si se produjo un error al editar la reserva
                System.err.println(e.getMessage());
            }
        } else {
            // Mostrar un mensaje de error si la reserva no se encontró
            System.out.println("Reserva no encontrada");
        }

        // Limpiar los campos de entrada
        comboBoxClientes.getSelectionModel().clearSelection();
        comboBoxVehiculos.getSelectionModel().clearSelection();
        datePickerInicio.setValue(null);
        datePickerFin.setValue(null);
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

    // Método para obtener el ID de la reserva a partir del elemento seleccionado en la ListView
    private int obtenerIdReserva(String reservaSeleccionada) {
        // HACK: Falta añadir la lógica para obtener el ID de la reserva a partir del texto de la reservaSeleccionada
        return 0; // Reemplazar 0 con el ID correcto
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

    // Método para actualizar las listas en los ComboBox y la ListView
    private void actualizarListas() {
        // Actualizar la lista de clientes
        clientesList.clear();
        for (Cliente cliente : empresa.getListaClientes()) {
            clientesList.add(cliente.getNombre());
        }

        // Actualizar la lista de vehículos
        vehiculosList.clear();
        for (Vehiculo vehiculo : empresa.getListaVehiculos()) {
            vehiculosList.add(vehiculo.getMatricula() + " - " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        }

        // Actualizar la lista de reservas
        reservasList.clear();
        for (Reserva reserva : empresa.getListaReservas()) {
            reservasList.add("Reserva " + reserva.getId() + " - " + reserva.getCliente().getNombre() + " - " + reserva.getVehiculo().getMatricula() + " - " + reserva.getFechaInicio() + " - " + reserva.getFechaFin());
        }
    }
}