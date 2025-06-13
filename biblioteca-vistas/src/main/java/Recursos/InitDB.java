/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;

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
            stmt.execute("""
                        
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    usuario VARCHAR(50) NOT NULL,
                    contrasena VARCHAR(100) NOT NULL,
                    rol VARCHAR(20) NOT NULL
                );
                """);
            stmt.execute("""
                        MERGE INTO usuarios (usuario, contrasena, rol)
                           KEY(usuario)
                           VALUES ('admin', '1234', 'Administrador'); 
                         """);
            // Tabla autores
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS autores (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL
                );
            """);

            // Tabla libros
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS libros (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    titulo VARCHAR(150) NOT NULL,
                    autor_id INT,
                    anio_publicacion INT,
                    disponible BOOLEAN DEFAULT TRUE,
                    FOREIGN KEY (autor_id) REFERENCES autores(id)
                );
            """);

            // Tabla prestamos
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS prestamos (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    usuario_id INT,
                    libro_id INT,
                    fecha_prestamo DATE,
                    fecha_devolucion DATE,
                    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                    FOREIGN KEY (libro_id) REFERENCES libros(id)
                );
            """);


            System.out.println("Base de datos inicializada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
