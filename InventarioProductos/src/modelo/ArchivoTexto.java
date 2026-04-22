/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alexj
 */
public class ArchivoTexto {

    private final Path path;

    public ArchivoTexto(String filename) {
        this.path = Paths.get(filename);
    }

    public boolean appendLinea(String linea) {
        try {
            Path parent = path.getParent();
            if (parent != null) Files.createDirectories(parent);
            try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                bw.write(linea);
                bw.newLine();
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<String> leerLineas() {
        List<String> lines = new ArrayList<>();
        if (!Files.exists(path)) return lines;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String l;
            while ((l = br.readLine()) != null) lines.add(l);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    public boolean sobrescribirTodas(List<String> lineas) {
        try {
            Path parent = path.getParent();
            if (parent != null) Files.createDirectories(parent);
            try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                for (String ln : lineas) {
                    bw.write(ln);
                    bw.newLine();
                }
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}


