package controlador;

import modelo.ArchivoTexto;
import modelo.Calificacion;
import vista.FrmCalificaciones;

import javax.swing.*;
import java.util.List;

public class ControladorCalificaciones {
    private final FrmCalificaciones vista;
    private final ArchivoTexto archivo;

    public ControladorCalificaciones(FrmCalificaciones vista, String nombreArchivo) {
        this.vista = vista;
        this.archivo = new ArchivoTexto(nombreArchivo);

        this.vista.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                guardarCalificacion();
            }
        });

        this.vista.getBtnConsultar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                mostrarCalificaciones();
            }
        });
    }

    private void guardarCalificacion() {
        String nombre = vista.getNombre();
        String materia = vista.getMateria();
        String sCalif = vista.getCalificacion();

        if (nombre.isEmpty() || materia.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Nombre y Materia son obligatorios.");
            return;
        }

        double calif;
        try {
            calif = Double.parseDouble(sCalif);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Calificación inválida.");
            return;
        }

        if (calif < 0.0 || calif > 100.0) {
            JOptionPane.showMessageDialog(vista, "La calificación debe estar entre 0 y 100.");
            return;
        }

        Calificacion c = new Calificacion(nombre, materia, calif);
        boolean ok = archivo.appendLinea(c.toString());
        if (ok) {
            JOptionPane.showMessageDialog(vista, "Calificación guardada.");
            vista.limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar la calificación.");
        }
    }

    private void mostrarCalificaciones() {
        List<String> lines = archivo.leerLineas();
        Object[][] datos = new Object[lines.size()][3];
        for (int i = 0; i < lines.size(); i++) {
            Calificacion c = Calificacion.fromString(lines.get(i));
            datos[i][0] = c.getNombre();
            datos[i][1] = c.getMateria();
            datos[i][2] = c.getCalificacion();
        }
        vista.setTablaDatos(datos);
    }
}
