/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo02;
import java.sql.*;

/**
 *
 * @author Tomas Machin
 * @author Juan Federico García
 * @author Paloma Perez
 */

public class GestorBD {
    String url = "jdbc:mysql://localhost:3306/world"; //cambiar  
    String usuario; 
    String password; 
    Statement statement;
    Connection conexion;
    String respuesta;
    
    /*
    * Método que devuelve 'true' si se inicia sesión correctamente
    */
    public boolean logIn(String nombre, String contraseña){     
        usuario = nombre;
        password = contraseña;
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            cerrarConexion();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
    }
    
    public void cerrarConexion() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar el objeto Statement: " + e.getMessage());
        }

        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
