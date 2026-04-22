package modelo;

public class Venta {
    private String fecha;
    private String producto;
    private int cantidad;
    private double total;

    public Venta(String fecha, String producto, int cantidad, double total) {
        this.fecha = fecha == null ? "" : fecha.trim();
        this.producto = producto == null ? "" : producto.trim();
        this.cantidad = cantidad;
        this.total = total;
    }

    public String getFecha() { return fecha; }
    public String getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return fecha + "|" + producto + "|" + cantidad + "|" + total;
    }

    public static Venta fromString(String line) {
        if (line == null) return new Venta("", "", 0, 0.0);
        String[] p = line.split("\\|", -1);
        String fecha = p.length > 0 ? p[0] : "";
        String producto = p.length > 1 ? p[1] : "";
        int cantidad = 0;
        double total = 0.0;
        try { if (p.length > 2) cantidad = Integer.parseInt(p[2]); } catch (Exception e) {}
        try { if (p.length > 3) total = Double.parseDouble(p[3]); } catch (Exception e) {}
        return new Venta(fecha, producto, cantidad, total);
    }
}
