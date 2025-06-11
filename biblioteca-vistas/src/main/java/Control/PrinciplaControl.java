package Control;

import componentes.Principal;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class PrinciplaControl {

    private Principal vista;

    public PrinciplaControl(String rol) {
        vista = new Principal(rol);
        cargarPanelesPorRol(rol);
        vista.setVisible(true);
    }

    private void cargarPanelesPorRol(String rol) {
        Map<String, String> paneles = new LinkedHashMap<>();

        switch (rol) {
            case "Administrador":
                paneles.put("Usuarios", "vistas.paneles.Usuario");
                paneles.put("Libros", "vistas.paneles.LibrosAdmin");
                paneles.put("Autores", "vistas.paneles.Autores");
                paneles.put("Prestamos", "vistas.paneles.Prestamos");
                paneles.put("Reseñas", "vistas.paneles.Resenas");
                break;
            case "Bibliotecario":
                paneles.put("Usuarios", "vistas.paneles.UsuarioBiblio");
                paneles.put("Libros", "vistas.paneles.LibrosAdmin");
                paneles.put("Autores", "vistas.paneles.Autores");
                paneles.put("Prestamos", "vistas.paneles.Prestamos");
                break;
            case "Miembro":
                paneles.put("Libros", "vistas.paneles.LibrosUser");
                paneles.put("Mis Prestamos", "vistas.paneles.MisPrestamos");
                paneles.put("Reseñas", "vistas.paneles.ResenasUsuario");
                break;
        }

        for (Map.Entry<String, String> entry : paneles.entrySet()) {
            try {
                Class<?> clazz = Class.forName(entry.getValue());
                JPanel panel = (JPanel) clazz.getDeclaredConstructor().newInstance();
                vista.addPanel(entry.getKey(), panel);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error al cargar panel: " + entry.getKey(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}


