package Modelos;

import org.h2.tools.RunScript;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:h2:~/data/bibliotecaDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        ejecutarScriptSiEsNecesario(conn);
        return conn;
    }

    private static void ejecutarScriptSiEsNecesario(Connection conn) {
        try {
            // Carga el archivo desde resources/init/Datos.sql
            RunScript.execute(conn, new InputStreamReader(
                ConexionDB.class.getClassLoader().getResourceAsStream("init/Datos.sql")
            ));
        } catch (Exception e) {
            System.err.println("❌ Error ejecutando el script SQL: " + e.getMessage());
            // Puedes comentar el siguiente `e.printStackTrace();` si ya no deseas ver todo el stacktrace
            e.printStackTrace();
        }
    }
}
