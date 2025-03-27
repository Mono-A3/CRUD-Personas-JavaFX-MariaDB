package com.personas.javapersonassena.model;

import java.time.LocalDate;

public class Persona {
    private int id;
    private String cedula;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaNacimiento;
    private String genero;

    public Persona (int id, String cedula, String nombre, String domicilio, String telefono, String correoElectronico, LocalDate fechaNacimiento, String genero) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    @Override
    public String toString() {
        return nombre + " (" + cedula + ")";
    }
}