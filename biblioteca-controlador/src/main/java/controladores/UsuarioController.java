package controladores;
//asdasdasd
import Modelos.DaoUsuario;
import Modelos.Usuario;
import Modelos.Usuario.Rol;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UsuarioController {
    public static void cargarUsuarios(DefaultTableModel model) {
        model.setRowCount(0); 
        List<Usuario> usuarios = DaoUsuario.listarUsuarios();
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[]{
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().toString(),
                usuario.getFechaRegistro()
            });
        }
    }
    
    public static void filtrarUsuarios(DefaultTableModel model, String busqueda, Rol rol) {
        model.setRowCount(0);
        List<Usuario> usuarios = DaoUsuario.filtrarUsuarios(busqueda, rol);
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[]{
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().toString(),
                usuario.getFechaRegistro()
            });
        }
    }
}