package controlador;

import modelo.ArchivoTexto;
import modelo.Asistencia;
import vista.FrmAsistencia;

import javax.swing.*;
import java.util.List;

public class ControladorAsistencia {
    private final FrmAsistencia vista;
    private final ArchivoTexto archivo;

    public ControladorAsistencia(FrmAsistencia vista, String nombreArchivo) {
        this.vista = vista;
        this.archivo = new ArchivoTexto(nombreArchivo);

        // Guardar asistencia (no actualiza tabla automáticamente)
        this.vista.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                guardarAsistencia();
            }
        });

        // Ver lista (actualiza tabla solo al pulsar este botón)
        this.vista.getBtnVer().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                mostrarAsistencias();
            }
        });
    }

    private void guardarAsistencia() {
        String nombre = vista.getNombre();
        String fecha = vista.getFecha();
        String estado = vista.getEstado();

        if (nombre.isEmpty() || fecha.isEmpty() || estado.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Nombre, Fecha y Estado son obligatorios.");
            return;
        }

        Asistencia a = new Asistencia(nombre, fecha, estado);
        boolean ok = archivo.appendLinea(a.toString());
        if (ok) {
            JOptionPane.showMessageDialog(vista, "Asistencia registrada.");
            vista.limpiarCampos();
            // NO llamar mostrarAsistencias() aquí
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar la asistencia.");
        }
    }

    private void mostrarAsistencias() {
        List<String> lines = archivo.leerLineas();
        Object[][] datos = new Object[lines.size()][3];
        for (int i = 0; i < lines.size(); i++) {
            Asistencia a = Asistencia.fromString(lines.get(i));
            datos[i][0] = a.getNombre();
            datos[i][1] = a.getFecha();
            datos[i][2] = a.getEstado();
        }
        vista.setTablaDatos(datos);
    }
}

