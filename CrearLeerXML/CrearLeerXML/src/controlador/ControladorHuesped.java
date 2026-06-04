package controlador;

import modelo.ArchivoXML;
import modelo.Huesped;
import vista.FormularioHuesped;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorHuesped implements ActionListener {

    private FormularioHuesped vista;

    public ControladorHuesped(FormularioHuesped vista) {
        this.vista = vista;
        this.vista.conectarControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getBtnRegistrar()) {

            try {

                if (vista.getIdInput().isEmpty()
                        || vista.getNombreInput().isEmpty()
                        || vista.getApellidosInput().isEmpty()
                        || vista.getProcedenciaInput().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            vista,
                            "Campos vacíos"
                    );

                    return;
                }

                int id = Integer.parseInt(
                        vista.getIdInput()
                );

                String nombre =
                        vista.getNombreInput();

                String apellidos =
                        vista.getApellidosInput();

                String procedencia =
                        vista.getProcedenciaInput();

                List<Huesped> listaHuespedes =
                        ArchivoXML.leerXML();

                listaHuespedes.add(
                        new Huesped(
                                id,
                                nombre,
                                apellidos,
                                procedencia
                        )
                );

                ArchivoXML.crearXML(
                        "huespedes",
                        listaHuespedes
                );

                JOptionPane.showMessageDialog(
                        vista,
                        "Registro guardado en XML"
                );

                vista.limpiarCampos();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        vista,
                        "El ID debe ser numérico"
                );

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }

        if (e.getSource() == vista.getBtnListar()) {

            List<Huesped> listaHuespedes =
                    ArchivoXML.leerXML();

            vista.cargarTabla(listaHuespedes);
        }

        if (e.getSource() == vista.getBtnEliminar()) {

            try {

                int id =
                        Integer.parseInt(
                                vista.getIdInput()
                        );

                List<Huesped> lista =
                        ArchivoXML.leerXML();

                for (int i = 0; i < lista.size(); i++) {

                    if (lista.get(i).getIdHuesped() == id) {

                        lista.remove(i);
                        break;
                    }
                }

                ArchivoXML.crearXML(
                        "huespedes",
                        lista
                );

                JOptionPane.showMessageDialog(
                        vista,
                        "Registro eliminado"
                );

                vista.cargarTabla(lista);

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }

        if (e.getSource() == vista.getBtnActualizar()) {

            try {

                int id =
                        Integer.parseInt(
                                vista.getIdInput()
                        );

                List<Huesped> lista =
                        ArchivoXML.leerXML();

                for (Huesped h : lista) {

                    if (h.getIdHuesped() == id) {

                        h.setNombre(
                                vista.getNombreInput()
                        );

                        h.setApellidos(
                                vista.getApellidosInput()
                        );

                        h.setProcedencia(
                                vista.getProcedenciaInput()
                        );

                        break;
                    }
                }

                ArchivoXML.crearXML(
                        "huespedes",
                        lista
                );

                JOptionPane.showMessageDialog(
                        vista,
                        "Registro actualizado"
                );

                vista.cargarTabla(lista);

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }
}