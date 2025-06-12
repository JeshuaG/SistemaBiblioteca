/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author henry-torres
 */
public class InitDB {
    public static void main(String[] args) {
        try (Connection con = ConexionDB.conectar();
             Statement stmt = con.createStatement()) {

            // Crear tabla usuarios si no existe
            String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    usuario VARCHAR(50) NOT NULL,
                    contrasena VARCHAR(100) NOT NULL,
                    rol VARCHAR(20) NOT NULL
                );
                """;
            stmt.execute(sql);

            // Insertar usuario por defecto si no existe
            String insert = """
                MERGE INTO usuarios (usuario, contrasena, rol)
                KEY(usuario)
                VALUES ('admin', '1234', 'Administrador');
                """;
            stmt.execute(insert);

            System.out.println("Base de datos inicializada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
