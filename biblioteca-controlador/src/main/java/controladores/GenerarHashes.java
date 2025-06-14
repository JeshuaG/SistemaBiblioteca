package controladores;

public class GenerarHashes {
    public static void main(String[] args) {
        generar("admin123", "Administrador");
        generar("biblio123", "Bibliotecario");
        generar("miembro123", "Miembro");
    }

    private static void generar(String password, String rol) {
        String salt = Aut.generateSalt();
        String hash = Aut.hashPassword(password, salt);
        System.out.println("Password: " + password);
        System.out.println("Rol: " + rol);
        System.out.println("Salt: '" + salt + "'");
        System.out.println("Hash: '" + hash + "'");
        System.out.println();
    }
}
