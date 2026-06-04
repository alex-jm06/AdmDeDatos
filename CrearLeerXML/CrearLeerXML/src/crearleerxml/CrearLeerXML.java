package crearleerxml;

import controlador.ControladorHuesped;
import vista.FormularioHuesped;

public class CrearLeerXML {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormularioHuesped vista = new FormularioHuesped();
                new ControladorHuesped(vista);
                vista.setVisible(true);
            }
        });
    }
}