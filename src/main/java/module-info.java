module com.escuela.javaescuelasena {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.escuela.javaescuelasena to javafx.fxml;
    exports com.escuela.javaescuelasena;
}