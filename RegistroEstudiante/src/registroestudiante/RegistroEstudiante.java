/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registroestudiante;
import vista.FrmRegistroEstudiante;
import controlador.ControladorRegistro;
/**
 *
 * @author alexj
 */
public class RegistroEstudiante {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            FrmRegistroEstudiante frm = new FrmRegistroEstudiante();
            new ControladorRegistro(frm, "data/estudiantes.txt");
            frm.setVisible(true);
        });
    }
}

    