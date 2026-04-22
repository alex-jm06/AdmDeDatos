package app;

import vista.FrmInventario;
import controlador.ControladorInventario;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main: inicio");
        java.awt.EventQueue.invokeLater(() -> {
            System.out.println("Main: creando FrmInventario");
            FrmInventario frm = new FrmInventario();
            System.out.println("Main: creando controlador");
            new ControladorInventario(frm, "productos.txt");
            System.out.println("Main: mostrando frm");
            frm.setVisible(true);
        });
    }
}




