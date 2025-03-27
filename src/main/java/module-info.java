module com.personas.javapersonassena {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.personas.javapersonassena to javafx.fxml;
    opens com.personas.javapersonassena.model to javafx.base;
    exports com.personas.javapersonassena;
}