/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author alexj
 */
public class Producto {
    private final String id;
    private final String nombre;
    private final double precio;
    private final int cantidad;

    public Producto(String id, String nombre, double precio, int cantidad) {
        this.id = id == null ? "" : id.trim();
        this.nombre = nombre == null ? "" : nombre.trim();
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return id + "|" + nombre + "|" + precio + "|" + cantidad;
    }

    public static Producto fromString(String line) {
        if (line == null) return new Producto("", "", 0.0, 0);
        String[] p = line.split("\\|", -1);
        String id = p.length > 0 ? p[0] : "";
        String nombre = p.length > 1 ? p[1] : "";
        double precio = 0.0;
        int cantidad = 0;
        try { if (p.length > 2) precio = Double.parseDouble(p[2]); } catch (Exception e) {}
        try { if (p.length > 3) cantidad = Integer.parseInt(p[3]); } catch (Exception e) {}
        return new Producto(id, nombre, precio, cantidad);
    }
}


