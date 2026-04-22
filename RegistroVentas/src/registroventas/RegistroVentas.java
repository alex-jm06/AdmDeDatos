package registroventas;
import vista.FrmVentas;
import controlador.ControladorVentas;
/**
 *
 * @author alexj
 */
public class RegistroVentas {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmVentas frm = new FrmVentas();
                new ControladorVentas(frm, "ventas.txt");
                frm.setVisible(true);
            }
        });
    }
} 
    

