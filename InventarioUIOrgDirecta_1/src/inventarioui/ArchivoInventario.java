package inventarioui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ArchivoInventario {

    private String nombreArchivo;

    public ArchivoInventario(String nombre) throws IOException {
        this.nombreArchivo = nombre;
        File file = new File(nombreArchivo);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void guardar(Producto p) throws IOException {
        List<Producto> lista = leerTodos();
        boolean encontrado = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).id == p.id) {
                lista.set(i, p);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            lista.add(p);
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Producto prod : lista) {
                pw.println(
                        prod.id + "," +
                        prod.nombre + "," +
                        prod.descripcion + "," +
                        prod.existencia + "," +
                        prod.estado
                );
            }
        }
    }

    public List<Producto> leerTodos() throws IOException {
        List<Producto> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(",");

                if (datos.length == 5) {
                    lista.add(new Producto(
                            Integer.parseInt(datos[0]),
                            datos[1],
                            datos[2],
                            Integer.parseInt(datos[3]),
                            Boolean.parseBoolean(datos[4])
                    ));
                }
            }
        }

        return lista;
    }

    public Producto buscarPorId(int idBuscar) throws IOException {
        for (Producto p : leerTodos()) {
            if (p.id == idBuscar) {
                return p;
            }
        }
        return null;
    }
}