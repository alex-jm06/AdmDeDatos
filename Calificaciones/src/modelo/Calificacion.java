package modelo;

public class Calificacion {
    private String nombre;
    private String materia;
    private double calificacion;

    public Calificacion(String nombre, String materia, double calificacion) {
        this.nombre = nombre == null ? "" : nombre.trim();
        this.materia = materia == null ? "" : materia.trim();
        this.calificacion = calificacion;
    }

    public String getNombre() { return nombre; }
    public String getMateria() { return materia; }
    public double getCalificacion() { return calificacion; }

    @Override
    public String toString() {
        return nombre + "|" + materia + "|" + calificacion;
    }

    public static Calificacion fromString(String line) {
        if (line == null) return new Calificacion("", "", 0.0);
        String[] p = line.split("\\|", -1);
        String nombre = p.length > 0 ? p[0] : "";
        String materia = p.length > 1 ? p[1] : "";
        double calif = 0.0;
        try { if (p.length > 2) calif = Double.parseDouble(p[2]); } catch (Exception e) {}
        return new Calificacion(nombre, materia, calif);
    }
}
