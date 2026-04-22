/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bitacoraactividades;
import vista.FrmBitacora;
import controlador.ControladorBitacora;

public class BitacoraActividades {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Main: inicio");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Main: creando FrmBitacora");
                FrmBitacora frm = new FrmBitacora();
                System.out.println("Main: creando controlador");
                new ControladorBitacora(frm, "bitacora.txt");
                System.out.println("Main: mostrando frm");
                frm.setVisible(true);
            }
        });
    }
}
