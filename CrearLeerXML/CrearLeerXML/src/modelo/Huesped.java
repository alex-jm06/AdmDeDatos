package modelo;

public class Huesped {

    private int idHuesped;
    private String nombre;
    private String apellidos;
    private String procedencia;

    public Huesped(int idHuesped, String nombre, String apellidos, String procedencia) {
        this.idHuesped = idHuesped;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.procedencia = procedencia;
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }
}