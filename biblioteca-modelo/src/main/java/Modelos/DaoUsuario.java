package Modelos;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Modelos.Usuario;
import Modelos.Usuario.Rol;

public class DaoUsuario {
    private static final String SQL_SELECT = "SELECT id, nombre, email, rol, fecha_registro FROM usuarios";
    
    public static List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    "", 
                    Rol.valueOf(rs.getString("rol").toUpperCase()),
                    rs.getDate("fecha_registro").toLocalDate()
                );
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }
    
    public static List<Usuario> filtrarUsuarios(String busqueda, Rol rol) {
        List<Usuario> usuarios = new ArrayList<>();
        StringBuilder sql = new StringBuilder(SQL_SELECT);
        List<Object> parametros = new ArrayList<>();
        boolean whereAdded = false;
        
        if (busqueda != null && !busqueda.isEmpty()) {
            sql.append(" WHERE (nombre LIKE ? OR email LIKE ?");
            parametros.add("%" + busqueda + "%");
            parametros.add("%" + busqueda + "%");
            
            try {
                int id = Integer.parseInt(busqueda);
                sql.append(" OR id = ?");
                parametros.add(id);
            } catch (NumberFormatException e) {
                // No es un número, ignoramos la búsqueda por ID
            }
            sql.append(")");
            whereAdded = true;
        }
        
        if (rol != null) {
            if (whereAdded) {
                sql.append(" AND ");
            } else {
                sql.append(" WHERE ");
            }
            sql.append("rol = ?");
            parametros.add(rol.toString());
        }
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            for (int i = 0; i < parametros.size(); i++) {
                if (parametros.get(i) instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) parametros.get(i));
                } else {
                    stmt.setString(i + 1, parametros.get(i).toString());
                }
            }
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        "", // Password no necesario
                        Rol.valueOf(rs.getString("rol").toUpperCase()),
                        rs.getDate("fecha_registro").toLocalDate()
                    );
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }
}