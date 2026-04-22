package controlador;

import modelo.ArchivoTexto;
import modelo.Bitacora;
import vista.FrmBitacora;

import javax.swing.*;
import java.util.List;

public class ControladorBitacora {
    private final FrmBitacora vista;
    private final ArchivoTexto archivo;

    public ControladorBitacora(FrmBitacora vista, String nombreArchivo) {
        System.out.println("ControladorBitacora: inicio");
        this.vista = vista;
        this.archivo = new ArchivoTexto(nombreArchivo);

        this.vista.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                guardarEntrada();
            }
        });

        this.vista.getBtnVer().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                mostrarBitacora();
            }
        });

        System.out.println("ControladorBitacora: listeners registrados");
    }

    private void guardarEntrada() {
        String fecha = vista.getFecha();
        String actividad = vista.getActividad();

        if (fecha.isEmpty() || actividad.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Fecha y Actividad son obligatorios.");
            return;
        }

        Bitacora b = new Bitacora(fecha, actividad);
        boolean ok = archivo.appendLinea(b.toString());
        if (ok) {
            JOptionPane.showMessageDialog(vista, "Entrada guardada.");
            vista.limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar la entrada.");
        }
    }

    private void mostrarBitacora() {
        List<String> lines = archivo.leerLineas();
        Object[][] datos = new Object[lines.size()][2];
        for (int i = 0; i < lines.size(); i++) {
            Bitacora b = Bitacora.fromString(lines.get(i));
            datos[i][0] = b.getFecha();
            datos[i][1] = b.getActividad();
        }
        vista.setTablaDatos(datos);
    }
}

