package com.escuela.javaescuelasena.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/db_sena_escuela";
    private static final String USER = "root";
    private static final String PASSWORD = "1632";

    public static Connection getConnection() {
        try {
            // 🔥 Forzar la carga del driver (solo si sigue fallando)
            Class.forName("org.mariadb.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ No se encontró el driver de MariaDB.");
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
