package app;

import vista.FrmInventario;
import controlador.ControladorInventario;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            FrmInventario frm = new FrmInventario();
            new ControladorInventario(frm, "productos.txt");
            frm.setVisible(true);
        });
    }
}
