/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginbasico;
import vista.FrmUsuarios;
import controlador.ControladorUsuarios;
/**
 *
 * @author alexj
 */
public class LoginBasico {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmUsuarios frm = new FrmUsuarios();
                new ControladorUsuarios(frm, "usuarios.txt");
                frm.setVisible(true);
            }
        });
    }
}