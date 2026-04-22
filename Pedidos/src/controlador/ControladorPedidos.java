package controlador;

import modelo.ArchivoTexto;
import modelo.Pedido;
import vista.FrmPedidos;

import javax.swing.*;
import java.util.List;

public class ControladorPedidos {
    private final FrmPedidos vista;
    private final ArchivoTexto archivo;

    public ControladorPedidos(FrmPedidos vista, String nombreArchivo) {
        this.vista = vista;
        this.archivo = new ArchivoTexto(nombreArchivo);

        // Guardar pedido (no actualiza la tabla automáticamente)
        this.vista.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                guardarPedido();
            }
        });

        // Ver pedidos (actualiza la tabla solo al pulsar este botón)
        this.vista.getBtnVer().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                mostrarPedidos();
            }
        });
    }

    private void guardarPedido() {
        String cliente = vista.getCliente();
        String producto = vista.getProducto();
        String sCantidad = vista.getCantidad();

        if (cliente.isEmpty() || producto.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Cliente y Producto son obligatorios.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(sCantidad);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(vista, "Cantidad debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Cantidad inválida.");
            return;
        }

        Pedido p = new Pedido(cliente, producto, cantidad);
        boolean ok = archivo.appendLinea(p.toString());
        if (ok) {
            JOptionPane.showMessageDialog(vista, "Pedido guardado.");
            vista.limpiarCampos();
            // NO llamar mostrarPedidos() aquí
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar el pedido.");
        }
    }

    private void mostrarPedidos() {
        List<String> lines = archivo.leerLineas();
        Object[][] datos = new Object[lines.size()][3];
        for (int i = 0; i < lines.size(); i++) {
            Pedido p = Pedido.fromString(lines.get(i));
            datos[i][0] = p.getCliente();
            datos[i][1] = p.getProducto();
            datos[i][2] = p.getCantidad();
        }
        vista.setTablaDatos(datos);
    }
}
