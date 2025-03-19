package com.escuela.javaescuelasena;

import com.escuela.javaescuelasena.dao.DatabaseConnection;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Conexión exitosa a MariaDB!");
        } else {
            System.out.println("❌ No se pudo conectar.");
        }
    }
}
