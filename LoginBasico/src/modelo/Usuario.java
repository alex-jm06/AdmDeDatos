package modelo;

public class Usuario {
    private String usuario;
    private String contrasena;

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario == null ? "" : usuario.trim();
        this.contrasena = contrasena == null ? "" : contrasena.trim();
    }

    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }

    @Override
    public String toString() {
        return usuario + "|" + contrasena;
    }

    public static Usuario fromString(String line) {
        if (line == null) return new Usuario("", "");
        String[] p = line.split("\\|", -1);
        String usuario = p.length > 0 ? p[0] : "";
        String contrasena = p.length > 1 ? p[1] : "";
        return new Usuario(usuario, contrasena);
    }
}
