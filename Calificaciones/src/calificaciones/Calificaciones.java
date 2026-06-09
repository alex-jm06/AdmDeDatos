/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calificaciones;
import vista.FrmCalificaciones;
import controlador.ControladorCalificaciones;
/**
 *
 * @author alexj
 */
public class Calificaciones {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmCalificaciones frm = new FrmCalificaciones();
                new ControladorCalificaciones(frm, "calificaciones.txt");
                frm.setVisible(true);
            }
        });
    }
}