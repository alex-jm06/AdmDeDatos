package inventariocentrocomputogui;

import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private int existencia;
    private boolean estado; // true = activo, false = eliminado

    public Producto(int id, String nombre, String descripcion, int existencia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.estado = true;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getExistencia() { return existencia; }
    public boolean isEstado() { return estado; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setExistencia(int existencia) { this.existencia = existencia; }
    public void setEstado(boolean estado) { this.estado = estado; }
    
}