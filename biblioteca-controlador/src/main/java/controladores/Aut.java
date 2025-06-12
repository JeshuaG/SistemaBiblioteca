package controladores;

import Modelos.UsuarioAut;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Aut {
    private static Map<String, UsuarioAut> usuarios = new HashMap<>();

    static {
        usuarios.put("admin", crearUsuario("admin123", "Administrador"));
        usuarios.put("bibliotecario", crearUsuario("biblio123", "Bibliotecario"));
        usuarios.put("miembro", crearUsuario("miembro123", "Miembro"));
    }

    private static UsuarioAut crearUsuario(String password, String rol) {
        String salt = generateSalt();
        String hash = hashPassword(password, salt);
        return new UsuarioAut(salt, hash, rol);
    }

    public static boolean autenticar(String usuario, String password, String rolEsperado) {
        if (!usuarios.containsKey(usuario)) return false;

        UsuarioAut datos = usuarios.get(usuario);
        String salt = datos.getSalt();
        String hashAlmacenado = datos.getHash();

        String hashIngresado = hashPassword(password, salt);

        return hashAlmacenado.equals(hashIngresado) && datos.getRol().equalsIgnoreCase(rolEsperado);
    }

    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashed = md.digest(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hashed);
        } catch (Exception e) {
            throw new RuntimeException("Error cifrando contraseña", e);
        }
    }
}
