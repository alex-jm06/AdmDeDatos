package bitacoraactividades;
import vista.FrmBitacora;
import controlador.ControladorBitacora;

public class BitacoraActividades {

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
