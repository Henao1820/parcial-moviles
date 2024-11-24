package com.myapp11.model;

public class Pedido {
    public String userId;
    public String producto;
    public int precio;

    public Pedido() {
        // Constructor vacío requerido para Firebase
    }

    public Pedido(String userId, String producto, int precio) {
        this.userId = userId;
        this.producto = producto;
        this.precio = precio;
    }
}
