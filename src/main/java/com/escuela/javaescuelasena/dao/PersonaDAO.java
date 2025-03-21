package com.escuela.javaescuelasena.dao;

import com.escuela.javaescuelasena.model.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    public static boolean guardar(Persona persona){
        String sql = "INSERT INTO persona (cedula, nombre, domicilio, telefono, correo_electronico, fecha_nacimiento, genero) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persona.getCedula());
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getDomicilio());
            stmt.setString(4, persona.getTelefono());
            stmt.setString(5, persona.getCorreoElectronico());
            stmt.setDate(6, java.sql.Date.valueOf(persona.getFechaNacimiento()));
            stmt.setString(7, persona.getGenero());

            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar persona: " + e.getMessage());
            return false;
        }
    }

    public static List<Persona> obtenerTodas() {
        List<Persona> listaPersonas = new ArrayList<>();
        String sql = "SELECT * FROM persona";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt("id_persona"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("domicilio"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("genero")
                );
                listaPersonas.add(persona);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener personas: " + e.getMessage());
        }
        return listaPersonas;
    }

    public static boolean eliminar(Persona persona) {
        String sql = "DELETE FROM persona WHERE id_persona = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, persona.getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("No se pudo eliminar la persona con ID " + persona.getId() + ": " + e.getMessage());
            return false;
        }
    }

    public static Persona buscarPorId(int id) {
        String sql = "SELECT * FROM persona WHERE id_persona = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Persona(
                        rs.getInt("id_persona"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("domicilio"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("genero")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar persona: " + e.getMessage());
        }
        return null;
    }

    public static boolean actualizar(Persona persona) {
        String sql = "UPDATE persona SET cedula=?, nombre=?, domicilio=?, telefono=?, correo_electronico=?, fecha_nacimiento=?, genero=?, WHERE id_persona=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persona.getCedula());
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getDomicilio());
            stmt.setString(4, persona.getTelefono());
            stmt.setString(5, persona.getCorreoElectronico());
            stmt.setDate(6, java.sql.Date.valueOf(persona.getFechaNacimiento()));
            stmt.setString(7, persona.getGenero());
            stmt.setInt(8, persona.getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar persona: " + e.getMessage());
            return false;
        }
    }
}


