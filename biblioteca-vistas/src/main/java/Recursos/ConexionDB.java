package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author henry-torres
 */
public class ConexionDB {

    private static final String URL = "jdbc:h2:./basedatos/biblioteca";
    private static final String USER = "sa";
    private static final String PASSWORD = ""; 

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos H2: " + e.getMessage());
            return null;
        }
    }
}
