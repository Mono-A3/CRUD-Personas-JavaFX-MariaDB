package com.escuela.javaescuelasena;

import com.escuela.javaescuelasena.dao.PersonaDAO;
import com.escuela.javaescuelasena.model.Persona;
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
        } else {
            mostrarAlerta("No se pudo agregar la persona");
        }
    }

    @FXML
    private void buscarPersona() {
        int id = Integer.parseInt(txtId.getText());
        Persona persona = PersonaDAO.buscarPorId(id);

        if (persona != null) {
            txtCedula.setText(persona.getCedula());
            txtNombre.setText(persona.getNombre());
            txtDomicilio.setText(persona.getDomicilio());
            txtTelefono.setText(persona.getTelefono());
            txtCorreo.setText(persona.getCorreoElectronico());
            dpFechaNacimiento.setValue(persona.getFechaNacimiento());
            cbGenero.setValue(persona.getGenero());
        } else {
            mostrarAlerta("No se encontró ninguna persona con ese ID.");
        }
    }

    @FXML
    private void eliminarPersona() {
        int id = Integer.parseInt(txtId.getText());
        Persona persona = new Persona(id, "", "", "", "", "", null, "");

        if (PersonaDAO.eliminar(persona)) {
            mostrarAlerta("Persona eliminada correctamente.");
        } else {
            mostrarAlerta("No se pudo eliminar la persona");
        }
    }

    @FXML
    private void modificarPersona() {
        int id = Integer.parseInt(txtId.getText());
        Persona persona = new Persona(
                id,
                txtCedula.getText(),
                txtNombre.getText(),
                txtDomicilio.getText(),
                txtTelefono.getText(),
                txtCorreo.getText(),
                dpFechaNacimiento.getValue(),
                cbGenero.getValue()
        );

        if (PersonaDAO.actualizar(persona)) {
            mostrarAlerta("Persona actualizada con éxito.");
        } else {
            mostrarAlerta("No se pudo actualizar la persona.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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
    }

    @FXML
    private TableView<Persona> tablaPersonas;
    @FXML
    private TableColumn<Persona, Integer> colId;
    @FXML
    private TableColumn<Persona, String> colCedula, colNombre, colDomicilio, colTelefono, colCorreoElectronico, colGenero;
    @FXML
    private TableColumn<Persona, LocalDate> colFechaNacimiento;

    private ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();

    @FXML
    private void cargarPersonas() {
        listaPersonas.clear();
        listaPersonas.addAll(PersonaDAO.obtenerTodas());
        tablaPersonas.setItems(listaPersonas);
    }

    @FXML
    private void initialize() {
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
    }
}