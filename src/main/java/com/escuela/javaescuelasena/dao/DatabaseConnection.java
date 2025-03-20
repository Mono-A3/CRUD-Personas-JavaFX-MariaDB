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
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
            return con;
        } catch (SQLException e ) {
            System.err.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}
