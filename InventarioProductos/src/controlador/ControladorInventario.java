package controlador;

import modelo.ArchivoTexto;
import modelo.Producto;
import vista.FrmInventario;

import javax.swing.*;
import java.util.List;

public class ControladorInventario {
    private final FrmInventario vista;
    private final ArchivoTexto archivo;

    public ControladorInventario(FrmInventario vista, String nombreArchivo) {
        System.out.println("Controlador: inicio constructor");
        this.vista = vista;
        this.archivo = new ArchivoTexto(nombreArchivo);

        this.vista.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Controlador: btnGuardar clic");
                guardarProducto();
            }
        });

        this.vista.getBtnMostrar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Controlador: btnMostrar clic");
                mostrarInventario();
            }
        });

        System.out.println("Controlador: listeners registrados");
        mostrarInventario();
        System.out.println("Controlador: constructor terminado");
    }

    private void guardarProducto() {
        String id = vista.getId();
        String nombre = vista.getNombre();
        String sPrecio = vista.getPrecio();
        String sCantidad = vista.getCantidad();

        if (id.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "ID y Nombre son obligatorios.");
            return;
        }

        double precio;
        int cantidad;
        try {
            precio = Double.parseDouble(sPrecio);
            cantidad = Integer.parseInt(sCantidad);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Precio o Cantidad inválidos.");
            return;
        }

        List<String> lines = archivo.leerLineas();
        for (String ln : lines) {
            if (Producto.fromString(ln).getId().equals(id)) {
                JOptionPane.showMessageDialog(vista, "ID ya existe. Use otro ID.");
                return;
            }
        }

        Producto p = new Producto(id, nombre, precio, cantidad);
        boolean ok = archivo.appendLinea(p.toString());
        if (ok) {
            JOptionPane.showMessageDialog(vista, "Producto guardado.");
            vista.limpiarCampos();
            mostrarInventario();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar el producto.");
        }
    }

    private void mostrarInventario() {
        List<String> lines = archivo.leerLineas();
        Object[][] datos = new Object[lines.size()][4];
        for (int i = 0; i < lines.size(); i++) {
            Producto p = Producto.fromString(lines.get(i));
            datos[i][0] = p.getId();
            datos[i][1] = p.getNombre();
            datos[i][2] = p.getPrecio();
            datos[i][3] = p.getCantidad();
        }
        vista.setTablaDatos(datos);
    }
}
