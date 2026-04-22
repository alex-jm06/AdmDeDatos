package modelo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoTexto {
    private final Path path;

    public ArchivoTexto(String filename) {
        this.path = Paths.get(filename);
    }

    public boolean appendLinea(String linea) {
        try {
            Path parent = path.getParent();
            if (parent != null) Files.createDirectories(parent);
            try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
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
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String l;
            while ((l = br.readLine()) != null) lines.add(l);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    // Crea archivo con ejemplos si no existe (opcional)
    public void crearEjemploSiNoExiste(List<String> lines) {
        Path p = path;
        try {
            if (Files.exists(p)) return;
            Path parent = p.getParent();
            if (parent != null) Files.createDirectories(parent);
            Files.write(p, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
