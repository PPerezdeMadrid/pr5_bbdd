package grupo02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ppere
 */
public class CargarDatosOpt {

    String url = "jdbc:mysql://localhost:3306/pr5";
    long tiempoFin;
    long tiempo = 0;
    long tiempoInicio;
    Statement statement;
    Connection conexion;
    int contadorOpt = 0;
    ArrayList<String> consultasOpt = new ArrayList<>(); 
    
    CargarDatos cargaArray = new CargarDatos();


    public long subirConsultas(String usuario, String contraseña) {
        cargaArray.obtenerInsert(); //se obtiene array
        int t = 0;
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            conexion.setAutoCommit(false);
            statement = conexion.createStatement();
            int resultado = 0;
            tiempoInicio = System.nanoTime();
            statement.execute("CREATE DATABASE IF NOT EXISTS pr5;\n");
            statement.execute("DROP TABLE IF EXISTS pr5.albums;");
            String crearTabla = "CREATE TABLE albums (\n" +
                "  id VARCHAR(50),\n" +
                "  name VARCHAR(255),\n" +
                "  album_group VARCHAR(255),\n" +
                "  album_type VARCHAR(50),\n" +
                "  release_date BIGINT,\n" +
                "  popularity INT\n" +
                ")" ;
            statement.execute(crearTabla);
            while(t < consultasOpt.size()){
                resultado = statement.executeUpdate(consultasOpt.get(t)); 
                    if (resultado > 0) {
                        contadorOpt=contadorOpt+1;
                        System.out.println("Inserciones realizadas: " + contadorOpt + "\n");
                    }
                t++;
            }
            conexion.commit();
            tiempoFin = System.nanoTime();
            tiempo = tiempoFin - tiempoInicio;
            

            if (resultado > 0) {
                contadorOpt=contadorOpt+1;
                System.out.println("Inserciones realizadas: " + contadorOpt + "\n");
            } else {
                System.out.println("Error al cargar una consulta de inserción \n");
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
        System.out.println(contadorOpt);
        return  tiempo/1000000000;
        
                
    }

    
}
