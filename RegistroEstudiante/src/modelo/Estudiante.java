package modelo;

public class Estudiante {
    private String numControl;
    private String nombre;
    private String carrera;
    private String promedio;

    public Estudiante(String numControl, String nombre, String carrera, double promedio) {
        this.numControl = safe(numControl);
        this.nombre = safe(nombre);
        this.carrera = safe(carrera);
        this.promedio = String.valueOf(promedio);
    }

    public Estudiante(String numControl, String nombre, String carrera, String promedio) {
        this.numControl = safe(numControl);
        this.nombre = safe(nombre);
        this.carrera = safe(carrera);
        this.promedio = safe(promedio);
    }

    public String getNumControl() { return numControl; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
    public String getPromedio() { return promedio; }

    @Override
    public String toString() {
        return numControl + "|" + nombre + "|" + carrera + "|" + promedio;
    }

    private String safe(String s) {
        return s == null ? "" : s.trim().replace("|", " ");
    }
}
