package modelo;

public class Asistencia {
    private String nombre;
    private String fecha;
    private String estado; // "Asistió" o "Faltó"

    public Asistencia(String nombre, String fecha, String estado) {
        this.nombre = nombre == null ? "" : nombre.trim();
        this.fecha = fecha == null ? "" : fecha.trim();
        this.estado = estado == null ? "" : estado.trim();
    }

    public String getNombre() { return nombre; }
    public String getFecha() { return fecha; }
    public String getEstado() { return estado; }

    @Override
    public String toString() {
        return nombre + "|" + fecha + "|" + estado;
    }

    public static Asistencia fromString(String line) {
        if (line == null) return new Asistencia("", "", "");
        String[] p = line.split("\\|", -1);
        String nombre = p.length > 0 ? p[0] : "";
        String fecha = p.length > 1 ? p[1] : "";
        String estado = p.length > 2 ? p[2] : "";
        return new Asistencia(nombre, fecha, estado);
    }
}
