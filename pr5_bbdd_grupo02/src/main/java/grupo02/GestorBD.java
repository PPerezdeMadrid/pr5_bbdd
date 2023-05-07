package grupo02;
import java.sql.*;

/**
 * crearBD(usuario, password);
 * addConsultas(String);
 * cronometro(0)--> STOP
 * @author ppere
 */

public class GestorBD {
    String url = "jdbc:mysql://localhost:3306/world";  
    String usuario; 
    String password; 
    Statement statement;
    Connection conexion;
    String respuesta;
    
    public String crearDB(String usuario2, String password2) {
        usuario = usuario2;
        password = password2;
        String consulta = "CREATE DATABASE pr5;\n" +
            "USE pr5";

        try {

            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            statement.executeQuery(consulta);
            respuesta = "La Base de Datos pr5 ha sido creada";
            System.out.println(respuesta);

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return respuesta;
    }
    
    private void addConsultas(String consulta){
        CargarDatos data = new CargarDatos();
        data.obtenerInsert(); //obtiene secuencia Insert
        data.medirTiempoCarga(); //mide tiempo de carga
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
