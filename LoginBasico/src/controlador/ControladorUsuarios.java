package controlador;

import modelo.ArchivoTexto;
import modelo.Usuario;
import vista.FrmUsuarios;

import javax.swing.*;
import java.util.List;

public class ControladorUsuarios {
    private final FrmUsuarios vista;
    private final ArchivoTexto archivo;

    public ControladorUsuarios(FrmUsuarios vista, String nombreArchivo) {
        this.vista = vista;
        this.archivo = new ArchivoTexto(nombreArchivo);

        // Conectar botones
        this.vista.getBtnRegistrar().addActionListener(e -> registrarUsuario());
        this.vista.getBtnIngresar().addActionListener(e -> validarLogin());
    }

    private void registrarUsuario() {
        String usuario = vista.getUsuarioRegistro();
        String contrasena = vista.getContrasenaRegistro();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            vista.mostrarResultado("Usuario y contraseña son obligatorios.", false);
            return;
        }

        // Evitar duplicados
        List<String> lines = archivo.leerLineas();
        for (String ln : lines) {
            Usuario u = Usuario.fromString(ln);
            if (u.getUsuario().equals(usuario)) {
                vista.mostrarResultado("Usuario ya existe.", false);
                return;
            }
        }

        Usuario u = new Usuario(usuario, contrasena);
        boolean ok = archivo.appendLinea(u.toString());
        if (ok) {
            vista.mostrarResultadoTemporal("Usuario registrado", true, 2500, false);
            vista.limpiarRegistro();
        } else {
            vista.mostrarResultado("Error al registrar usuario.", false);
        }
    }

    private void validarLogin() {
        String usuario = vista.getUsuarioLogin();
        String contrasena = vista.getContrasenaLogin();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            vista.mostrarResultado("Usuario y contraseña son obligatorios.", false);
            return;
        }

        List<String> lines = archivo.leerLineas();
        for (String ln : lines) {
            Usuario u = Usuario.fromString(ln);
            if (u.getUsuario().equals(usuario) && u.getContrasena().equals(contrasena)) {
                vista.mostrarResultadoTemporal("Usuario válido", true, 3000, true);
                return;
            }
        }
        vista.mostrarResultadoTemporal("Usuario no válido", false, 3000, false);
    }
}
