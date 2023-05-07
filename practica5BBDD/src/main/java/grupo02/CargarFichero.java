package grupo02;

import java.sql.*;

public class CargarFichero {
    String url = "jdbc:mysql://localhost:3306/pr5"; // CAMBIAR URL!!!
    String usuario; // Usuario de la base de datos
    String password; // Contraseña de la base de datos
    Statement statement;
    ResultSet resultado;
    Connection conexion;
    

    public long cargarFicheroTab(String usuario1, String password1) throws SQLException {
        long tiempo = 0;
        usuario = usuario1;
        password = password1;
        String consulta = "LOAD DATA INFILE 'artists_tabulado.txt' INTO TABLE artists FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n';";
        
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            long inicio = System.nanoTime();
            statement.executeUpdate(consulta);      //el metodo executeQuiery no deja si no hay una vuelta de informacion
            long fin = System.nanoTime();
            tiempo = fin - inicio;
            

            System.out.println("Tiempo de ejecución de la consulta: " + tiempo + " ms");
            
        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
        }
        cerrarConexion();
        return tiempo;
    }
    public boolean logIn(String nombre, String contraseña){     //si inicia sesion devuelve true, sino, false
        usuario = nombre;
        password = contraseña;
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            conexion.close();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }

    }
    public void cerrarConexion() throws SQLException{
        statement.close();
        conexion.close();
    }
}
