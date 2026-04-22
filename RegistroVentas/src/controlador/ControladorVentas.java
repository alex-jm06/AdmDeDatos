package controlador;

import modelo.ArchivoTexto;
import modelo.Venta;
import vista.FrmVentas;

import javax.swing.*;
import java.util.List;

public class ControladorVentas {
    private final FrmVentas vista;
    private final ArchivoTexto archivo;

    public ControladorVentas(FrmVentas vista, String nombreArchivo) {
        System.out.println("ControladorVentas: inicio");
        this.vista = vista;
        this.archivo = new ArchivoTexto(nombreArchivo);

        // Registrar venta (no actualiza la tabla aquí)
        this.vista.getBtnRegistrar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                registrarVenta();
            }
        });

        // Ver ventas (actualiza la tabla solo cuando se pulsa este botón)
        this.vista.getBtnVer().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                mostrarVentas();
            }
        });

        System.out.println("ControladorVentas: listeners registrados");
        System.out.println("ControladorVentas: listo");
    }

    private void registrarVenta() {
        String fecha = vista.getFecha();
        String producto = vista.getProducto();
        String sCantidad = vista.getCantidad();
        String sTotal = vista.getTotal();

        if (fecha.isEmpty() || producto.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Fecha y Producto son obligatorios.");
            return;
        }

        int cantidad;
        double total;
        try {
            cantidad = Integer.parseInt(sCantidad);
            total = Double.parseDouble(sTotal);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Cantidad o Total inválidos.");
            return;
        }

        Venta v = new Venta(fecha, producto, cantidad, total);
        boolean ok = archivo.appendLinea(v.toString());
        if (ok) {
            JOptionPane.showMessageDialog(vista, "Venta registrada.");
            vista.limpiarCampos();
            // **NO** llamar mostrarVentas() aquí: la tabla se actualiza solo al pulsar VENTAS
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar la venta.");
        }
    }

    private void mostrarVentas() {
        List<String> lines = archivo.leerLineas();
        Object[][] datos = new Object[lines.size()][4];
        for (int i = 0; i < lines.size(); i++) {
            Venta v = Venta.fromString(lines.get(i));
            datos[i][0] = v.getFecha();
            datos[i][1] = v.getProducto();
            datos[i][2] = v.getCantidad();
            datos[i][3] = v.getTotal();
        }
        vista.setTablaDatos(datos);
    }
}
