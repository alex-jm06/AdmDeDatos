package modelo;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn == null ? "" : isbn.trim();
        this.titulo = titulo == null ? "" : titulo.trim();
        this.autor = autor == null ? "" : autor.trim();
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }

    @Override
    public String toString() {
        return isbn + "|" + titulo + "|" + autor;
    }

    public static Libro fromString(String line) {
        if (line == null) return new Libro("", "", "");
        String[] p = line.split("\\|", -1);
        String isbn = p.length > 0 ? p[0] : "";
        String titulo = p.length > 1 ? p[1] : "";
        String autor = p.length > 2 ? p[2] : "";
        return new Libro(isbn, titulo, autor);
    }
}
