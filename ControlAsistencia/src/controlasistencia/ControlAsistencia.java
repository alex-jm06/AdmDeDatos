package controlasistencia;

import vista.FrmAsistencia;
import controlador.ControladorAsistencia;

public class ControlAsistencia {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmAsistencia frm = new FrmAsistencia();
                new ControladorAsistencia(frm, "asistencia.txt");
                frm.setVisible(true);
            }
        });
    }
}
