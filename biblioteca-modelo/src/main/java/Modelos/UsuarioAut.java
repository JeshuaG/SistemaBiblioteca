package Modelos;

public class UsuarioAut {
    private String salt;
    private String hash;
    private String rol;

    public UsuarioAut(String salt, String hash, String rol) {
        this.salt = salt;
        this.hash = hash;
        this.rol = rol;
    }

    public String getSalt() { return salt; }
    public String getHash() { return hash; }
    public String getRol() { return rol; }
}
