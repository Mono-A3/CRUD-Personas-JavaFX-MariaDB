module com.escuela.javaescuelasena {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.escuela.javaescuelasena to javafx.fxml;
    opens com.escuela.javaescuelasena.model to javafx.base;
    exports com.escuela.javaescuelasena;
}