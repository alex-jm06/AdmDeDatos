package inventarioui;

class Producto {

    int id;
    String nombre;
    String descripcion;
    int existencia;
    boolean estado;

    public Producto(int id, String nombre, String descripcion, int existencia, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.estado = estado;
    }
}