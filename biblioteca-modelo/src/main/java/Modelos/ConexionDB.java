package Modelos;

import org.h2.tools.RunScript;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB { 
    private static final String URL = "jdbc:h2:file:./data/bibliotecaDB";
           // "jdbc:h2:tcp://localhost/~/data/bibliotecaDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        ejecutarScriptSiEsNecesario(conn);
        return conn;
    }

private static void ejecutarScriptSiEsNecesario(Connection conn) {
    try {
        var inputStream = ConexionDB.class.getClassLoader().getResourceAsStream("init/Datos.sql");
        if (inputStream == null) {
            System.err.println("No se encontró el archivo init/Datos.sql en resources.");
            return;
        }

        RunScript.execute(conn, new InputStreamReader(inputStream));
        System.out.println("✅Script SQL ejecutado correctamente.");
    } catch (Exception e) {
        System.err.println("❌Error ejecutando el script SQL: " + e.getMessage());
        e.printStackTrace();
    }
}}