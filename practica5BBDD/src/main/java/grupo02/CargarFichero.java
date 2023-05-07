package grupo02;

import java.sql.*;

public class CargarFichero {
    String url = "jdbc:mysql://localhost:3306/sakila"; // CAMBIAR URL!!!
    String usuario; // Usuario de la base de datos
    String password; // Contraseña de la base de datos
    Statement statement;
    ResultSet resultado;
    Connection conexion;
    

    public long cargarFicheroTab(String usuario1, String password1) {
        long tiempo = 0;
        usuario = usuario1;
        password = password1;
        String consulta = "LOAD DATA INFILE '/ruta/al/archivo/artists_tabulado.txt' INTO TABLE artists FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n';";
        //IMPORTANTE: GUARDAR ARCHIVO TABULADO Y CAMBIAR DIRECC RUTA
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            long inicio = System.nanoTime();
            statement.executeQuery(consulta);
            long fin = System.nanoTime();
            tiempo = fin - inicio;
            

            System.out.println("Tiempo de ejecución de la consulta: " + tiempo + " ms");

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return tiempo;
    }
    
    public void cerrarConexion() throws SQLException{
        statement.close();
        conexion.close();
    }
}
