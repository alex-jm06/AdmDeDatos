/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pedidos;
import vista.FrmPedidos;
import controlador.ControladorPedidos;
/**
 *
 * @author alexj
 */
public class Pedidos {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmPedidos frm = new FrmPedidos();
                new ControladorPedidos(frm, "pedidos.txt");
                frm.setVisible(true);
            }
        });
    }
}
   