
package inventarioui;

class Producto {
    int id;
    String nombre;
    String descripcion;
    int existencia;
    boolean estado;

    static final int TAM_NOMBRE = 20;
    static final int TAM_DESC = 30;
    static final int TAM_REG = 4 + (2 * TAM_NOMBRE) + (2 * TAM_DESC) + 4 + 1;

    public Producto(int id, String nombre, String descripcion, int existencia, boolean estado) {
        this.id = id;
        this.nombre = ajustar(nombre, TAM_NOMBRE);
        this.descripcion = ajustar(descripcion, TAM_DESC);
        this.existencia = existencia;
        this.estado = estado;
    }

    public static String ajustar(String texto, int longitud) {
        if (texto.length() > longitud)
            return texto.substring(0, longitud);
        while (texto.length() < longitud)
            texto += " ";
        return texto;
    }
}