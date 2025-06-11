package controladores;


import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Aut {

    private static Map<String, String[]> usuarios = new HashMap<>();

    static {
        String salt = generateSalt();
        String hash = hashPassword("admin123", salt);
        usuarios.put("admin", new String[]{salt, hash});
    }

    public static boolean autenticar(String usuario, String password) {
        if (!usuarios.containsKey(usuario)) return false;

        String[] datos = usuarios.get(usuario);
        String salt = datos[0];
        String hashAlmacenado = datos[1];

        String hashIngresado = hashPassword(password, salt);

        return hashAlmacenado.equals(hashIngresado);
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
    
