package controlador;

import modelo.ArchivoTexto;
import modelo.Libro;
import vista.FrmBiblioteca;

import javax.swing.*;
import java.util.List;

public class ControladorBiblioteca {
    private final FrmBiblioteca vista;
    private final ArchivoTexto archivo;

    public ControladorBiblioteca(FrmBiblioteca vista, String nombreArchivo) {
        this.vista = vista;
        this.archivo = new ArchivoTexto(nombreArchivo);

        // Agregar libro (no actualiza la tabla automáticamente)
        this.vista.getBtnAgregar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                agregarLibro();
            }
        });

        // Mostrar libros (actualiza la tabla solo al pulsar este botón)
        this.vista.getBtnMostrar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                mostrarLibros();
            }
        });
    }

    private void agregarLibro() {
        String isbn = vista.getISBN();
        String titulo = vista.getTitulo();
        String autor = vista.getAutor();

        if (isbn.isEmpty() || titulo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "ISBN y Título son obligatorios.");
            return;
        }

        // Evitar ISBN duplicados
        List<String> lines = archivo.leerLineas();
        for (String ln : lines) {
            Libro pExist = Libro.fromString(ln);
            if (pExist.getIsbn().equals(isbn)) {
                JOptionPane.showMessageDialog(vista, "ISBN ya existe. Use otro ISBN.");
                return;
            }
        }

        Libro l = new Libro(isbn, titulo, autor);
        boolean ok = archivo.appendLinea(l.toString());
        if (ok) {
            JOptionPane.showMessageDialog(vista, "Libro agregado.");
            vista.limpiarCampos();
            // NO llamar mostrarLibros() aquí: la tabla se actualiza solo al pulsar MOSTRAR
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar el libro.");
        }
    }

    private void mostrarLibros() {
        List<String> lines = archivo.leerLineas();
        Object[][] datos = new Object[lines.size()][3];
        for (int i = 0; i < lines.size(); i++) {
            Libro l = Libro.fromString(lines.get(i));
            datos[i][0] = l.getIsbn();
            datos[i][1] = l.getTitulo();
            datos[i][2] = l.getAutor();
        }
        vista.setTablaDatos(datos);
    }
}
