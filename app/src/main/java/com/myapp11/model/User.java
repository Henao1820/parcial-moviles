package com.myapp11.model;

public class User {
    public String idUsuario;
    public String nombre;
    public String apellido;
    public String email;
    public String telefono;

    public User() {
        // Constructor vacío requerido para Firebase
    }

    public User(String idUsuario, String nombre, String apellido, String email, String telefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
}
