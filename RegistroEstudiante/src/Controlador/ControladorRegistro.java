package controlador;

import modelo.ArchivoTexto;
import modelo.Estudiante;
import vista.FrmRegistroEstudiante;

import java.util.List;

public class ControladorRegistro {
    private final FrmRegistroEstudiante vista;
    private final ArchivoTexto archivo;

    public ControladorRegistro(FrmRegistroEstudiante vista, String nombreArchivo) {
        this.vista = vista;
        this.archivo = new ArchivoTexto(nombreArchivo);
        this.vista.getBtnGuardar().addActionListener(e -> guardarEstudiante());
        this.vista.getBtnListar().addActionListener(e -> mostrarLista());
    }

    private void guardarEstudiante() {
        Estudiante est = vista.leerEstudianteDesdeVista();
        if (est.getNumControl().isEmpty() || est.getNombre().isEmpty() || est.getCarrera().isEmpty()) {
            vista.mostrarMensaje("Completa todos los campos.");
            return;
        }
        boolean ok = archivo.appendLinea(est.toString());
        if (ok) {
            vista.mostrarMensaje("Estudiante guardado.");
            vista.limpiarCampos();
        } else {
            vista.mostrarMensaje("Error al guardar el estudiante.");
        }
    }

    private void mostrarLista() {
        List<String> lines = archivo.leerLineas();
        Object[][] datos = new Object[lines.size()][4];
        for (int i = 0; i < lines.size(); i++) {
            String[] p = lines.get(i).split("\\|", -1);
            datos[i][0] = p.length > 0 ? p[0] : "";
            datos[i][1] = p.length > 1 ? p[1] : "";
            datos[i][2] = p.length > 2 ? p[2] : "";
            datos[i][3] = p.length > 3 ? p[3] : "";
        }
        vista.setTablaDatos(datos, new String[] { "Num Control", "Nombre", "Carrera", "Promedio" });
    }
}
