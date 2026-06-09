package registroestudiante;
import vista.FrmRegistroEstudiante;
import controlador.ControladorRegistro;


public class RegistroEstudiante {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            FrmRegistroEstudiante frm = new FrmRegistroEstudiante();
            new ControladorRegistro(frm, "data/estudiantes.txt");
            frm.setVisible(true);
        });
    }
}

    