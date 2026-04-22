/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblioteca;

import vista.FrmBiblioteca;
import controlador.ControladorBiblioteca;
/**
 *
 * @author alexj
 */
public class Biblioteca {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmBiblioteca frm = new FrmBiblioteca();
                new ControladorBiblioteca(frm, "libros.txt");
                frm.setVisible(true);
            }
        });
    }
}

   