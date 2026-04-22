package modelo;

public class Bitacora {
    private String fecha;
    private String actividad;

    public Bitacora(String fecha, String actividad) {
        this.fecha = fecha == null ? "" : fecha.trim();
        this.actividad = actividad == null ? "" : actividad.trim();
    }

    public String getFecha() { return fecha; }
    public String getActividad() { return actividad; }

    @Override
    public String toString() {
        return fecha + "|" + actividad;
    }

    public static Bitacora fromString(String line) {
        if (line == null) return new Bitacora("", "");
        String[] p = line.split("\\|", -1);
        String fecha = p.length > 0 ? p[0] : "";
        String actividad = p.length > 1 ? p[1] : "";
        return new Bitacora(fecha, actividad);
    }
}
