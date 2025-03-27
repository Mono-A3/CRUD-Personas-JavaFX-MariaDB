package com.personas.javapersonassena;

import com.personas.javapersonassena.dao.PersonaDAO;
import com.personas.javapersonassena.model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class PersonaController {
    @FXML private TextField txtCedula, txtNombre, txtDomicilio, txtTelefono, txtCorreo;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private ComboBox<String> cbGenero;
    @FXML private TextField txtId;
    @FXML private Button btnAgregar, btnBuscar, btnEliminar, btnModificar;
    @FXML private TableView<Persona> tablaPersonas;
    @FXML private TableColumn<Persona, Integer> colId;
    @FXML private TableColumn<Persona, String> colCedula, colNombre, colDomicilio, colTelefono, colCorreoElectronico, colGenero;
    @FXML private TableColumn<Persona, LocalDate> colFechaNacimiento;

    private ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        cbGenero.setItems(FXCollections.observableArrayList("Masculino", "Femenino"));

        tablaPersonas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreoElectronico.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        cargarPersonas();

        tablaPersonas.setOnMouseClicked(event -> {
            Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();

            if (personaSeleccionada != null) {
                txtId.setText(String.valueOf(personaSeleccionada.getId()));
                txtCedula.setText(personaSeleccionada.getCedula());
                txtNombre.setText(personaSeleccionada.getNombre());
                txtDomicilio.setText(personaSeleccionada.getDomicilio());
                txtTelefono.setText(personaSeleccionada.getTelefono());
                txtCorreo.setText(personaSeleccionada.getCorreoElectronico());
                dpFechaNacimiento.setValue(personaSeleccionada.getFechaNacimiento());
                cbGenero.setValue(personaSeleccionada.getGenero());
            }
        });

    }

    @FXML
    private void cargarPersonas() {
        listaPersonas.clear();
        listaPersonas.addAll(PersonaDAO.obtenerTodas());
        tablaPersonas.setItems(listaPersonas);
    }

    @FXML
    private void agregarPersona() {
        Persona persona = new Persona(
                0,
                txtCedula.getText(),
                txtNombre.getText(),
                txtDomicilio.getText(),
                txtTelefono.getText(),
                txtCorreo.getText(),
                dpFechaNacimiento.getValue(),
                cbGenero.getValue()
        );

        if (PersonaDAO.guardar(persona)) {
            mostrarAlerta("Persona agregada con éxito");
            limpiarCampos();
            cargarPersonas();
        } else {
            mostrarError("No se pudo agregar la persona");
        }
    }

    @FXML
    private void buscarPersona() {
        if (txtId.getText().isEmpty()) {
            mostrarAlerta("Por favor, ingrese un ID para buscar.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        Persona persona = PersonaDAO.buscarPorId(id);

        listaPersonas.clear();

        if (persona != null) {
            txtCedula.setText(persona.getCedula());
            txtNombre.setText(persona.getNombre());
            txtDomicilio.setText(persona.getDomicilio());
            txtTelefono.setText(persona.getTelefono());
            txtCorreo.setText(persona.getCorreoElectronico());
            dpFechaNacimiento.setValue(persona.getFechaNacimiento());
            cbGenero.setValue(persona.getGenero());

            listaPersonas.add(persona);
        } else {
            mostrarAlerta("No se encontró ninguna persona con ese ID.");
        }

        tablaPersonas.setItems(listaPersonas);
    }

    @FXML
    private void eliminarPersona() {
        if (txtId.getText().isEmpty()) {
            mostrarAlerta("Por favor, ingrese un ID antes de eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación de eliminación");
        confirmacion.setHeaderText("¿Estás seguro?");
        confirmacion.setContentText("Esta acción eliminará a la persona permanentemente.");

        ButtonType botonSi = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmacion.getButtonTypes().setAll(botonSi, botonNo);

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == botonSi) {
                int id = Integer.parseInt(txtId.getText());
                Persona persona = new Persona (id, "", "", "", "", "",null, "");

                if (PersonaDAO.eliminar(persona)) {
                    mostrarAlerta("Persona eliminada correctamente.");
                    limpiarCampos();
                    cargarPersonas();
                } else {
                    mostrarError("No se pudo eliminar la persona");
                }
            }
        });
    }

    @FXML
    private void modificarPersona() {
        if (txtId.getText().isEmpty()) {
            mostrarAlerta("Por favor, ingrese un Id antes de modificar.");
            return;
        }

        if (txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() || txtDomicilio.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtCorreo.getText().isEmpty() || dpFechaNacimiento.getValue() == null || cbGenero.getValue() == null) {
            mostrarAlerta("Por favor, complete todos los campos antes de modificar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación de modificación");
        confirmacion.setHeaderText("¿Deseas modificar los datos?");
        confirmacion.setContentText("Se actualizarán los datos de la persona en la base de datos.");

        ButtonType botonSi = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmacion.getButtonTypes().setAll(botonSi, botonNo);

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == botonSi) {
                int id = Integer.parseInt(txtId.getText());
                LocalDate fechaNacimiento = dpFechaNacimiento.getValue();

                Persona persona = new Persona(
                        id,
                        txtCedula.getText(),
                        txtNombre.getText(),
                        txtDomicilio.getText(),
                        txtTelefono.getText(),
                        txtCorreo.getText(),
                        fechaNacimiento != null ? fechaNacimiento : LocalDate.now(),
                        cbGenero.getValue()
                );

                if (PersonaDAO.actualizar(persona)) {
                    mostrarAlerta("Persona actualizada con éxito.");
                    cargarPersonas();
                } else {
                    mostrarError("No se pudo actualizar la persona.");
                }
            }
        });
    }

    @FXML
    private void limpiarCampos() {
        txtId.clear();
        txtCedula.clear();
        txtNombre.clear();
        txtDomicilio.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        dpFechaNacimiento.setValue(null);
        cbGenero.setValue(null);

        cargarPersonas();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Ocurrio un problema");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}