package agendadecontactos;
import java.io.*;
import java.util.ArrayList;
public class ArchivoTexto {
    private BufferedReader br;
    private PrintWriter pw;

    public boolean abrirArchivoTexto(char modo, String nombreArchivo) {
        try {
            if (modo == 'r') {
                br = new BufferedReader(new FileReader(nombreArchivo));
            } else if (modo == 'w') {
                pw = new PrintWriter(new FileWriter(nombreArchivo, true));
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void crearLinea(String linea) {
        if (pw != null) {
            pw.println(linea);
            pw.flush();
        }
    }

    public void cerrarArchivo(char modo) {
        try {
            if (modo == 'r' && br != null) br.close();
            if (modo == 'w' && pw != null) pw.close();
        } catch (IOException e) {}
    }

    public Object[][] obtenerMatrizLineas(int columnas) {
        ArrayList<String[]> lista = new ArrayList<>();
        try {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                lista.add(partes);
            }
        } catch (IOException e) {}

        Object[][] datos = new Object[lista.size()][columnas];
        for (int i = 0; i < lista.size(); i++) {
            datos[i] = lista.get(i);
        }
        return datos;
    }
}


