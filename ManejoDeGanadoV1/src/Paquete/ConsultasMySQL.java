/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paquete;
import java.sql.*;

public class ConsultasMySQL {

    private Connection conexion;

    public ConsultasMySQL() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/AdminGanadera";
            String usuario = "root";
            String contrasena = "1234";
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el controlador JDBC");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
        }
    }

    public boolean insertarUsuario(String usuario, String contrasena) {
        try {
            Statement stmt = conexion.createStatement();
            String consulta = "INSERT INTO usuarios (nombre_usuario, contrasena) VALUES ('" + usuario + "', '" + contrasena + "')";
            int filasAfectadas = stmt.executeUpdate(consulta);
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario en la base de datos"+ e.getMessage());
           return false;
        }
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión con la base de datos");
        }
    }

}
