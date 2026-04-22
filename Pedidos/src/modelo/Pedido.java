package modelo;

public class Pedido {
    private String cliente;
    private String producto;
    private int cantidad;

    public Pedido(String cliente, String producto, int cantidad) {
        this.cliente = cliente == null ? "" : cliente.trim();
        this.producto = producto == null ? "" : producto.trim();
        this.cantidad = cantidad;
    }

    public String getCliente() { return cliente; }
    public String getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return cliente + "|" + producto + "|" + cantidad;
    }

    public static Pedido fromString(String line) {
        if (line == null) return new Pedido("", "", 0);
        String[] p = line.split("\\|", -1);
        String cliente = p.length > 0 ? p[0] : "";
        String producto = p.length > 1 ? p[1] : "";
        int cantidad = 0;
        try { if (p.length > 2) cantidad = Integer.parseInt(p[2]); } catch (Exception e) {}
        return new Pedido(cliente, producto, cantidad);
    }
}

