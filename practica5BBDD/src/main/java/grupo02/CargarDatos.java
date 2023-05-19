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
public class CargarDatos {

    String url = "jdbc:mysql://localhost:3306/pr5";
    //String usuario; 
    //String password; 
    long tiempo = 0;
    long tiempoO;
    Statement statement;
    Connection conexion;
    String respuesta;
    int contador = 0;
    int count1 = 0;
    int count = 0;
    ArrayList<String> consultas = new ArrayList<>(); 

    //String archivoT = "albums_tabulado.txt";
    String archivoT = "archivo_prueba.txt";
    /*
    *Este método obtiene del archivo tabulado un arraylist de "INSERT..."
    *@pppere
     */
    public int obtenerInsert() {
        try {
            int n=0;
            String linea;
            String[] datos;
             
           // File archivo = new File(archivoPrueba);
            File archivo = new File(archivoT);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));
            
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println("cargando inserts");
                datos = linea.split("\t");
            
                 if (datos.length >= 6) {
                String name = datos[0].replaceAll("\"", "``").replaceAll("'", "`");
                String name_1 = datos[1].replaceAll("\"", "``").replaceAll("'", "`");
                String name_2 = datos[2].replaceAll("\"", "``").replaceAll("'", "`");
                String name_3 = datos[3].replaceAll("\"", "``").replaceAll("'", "`");
                String name_4 = datos[4].replaceAll("\"", "``").replaceAll("'", "`");
                String name_5 = datos[5].replaceAll("\"", "``").replaceAll("'", "`");
                
                String consulta = "INSERT INTO ALBUMS (name, id, album_group, album_type, release_date, popularity) "
                        + "VALUES ('" + name + "', '" + name_1 + "', '" + name_2 + "', '" + name_3 + "'," + name_4 + "," + name_5 + ");\n";
                
                consultas.add(consulta);
                n++; //variable de comprobación por la terminal
                System.out.println(n);
                    
            }
        }
            
           bufferedReader.close();
           
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
        } catch (IOException ex) {
            Logger.getLogger(CargarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /*
    *Este método carga UNA cosulta
    *Si la carga correctamente --> +1 al contador
    *Va devolviendo el contador (para posteriormente imprimirlo por pantalla)
    ** consultas.forEach(consulta -> cargarConsulta(consulta));
    ** CargarConsulta(consulta), devuelve n si se ha ejecutado correctamente
     */
    
    public long subirConsultas(String usuario, String contraseña){
        obtenerInsert(); //se obtiene array
        int t = 0;
        try {
            
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            statement = conexion.createStatement();
            System.out.println("1");
            int resultado = 0;
            long tiempoInicio = System.nanoTime();
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
            
            while(t < consultas.size()){
                try {
                resultado = statement.executeUpdate(consultas.get(t)); 
                    if (resultado > 0) {
                        contador=contador+1;
                        System.out.println("Inserciones realizadas: " + contador + "\n");
                     } else {
                        System.out.println("Error al cargar una consulta de inserción \n");
                     }
                t++;
                
                } catch (SQLException e) {
                    System.err.println("Error en una sentencia: " + e.getMessage());
                }
                
            long tiempoFin = System.nanoTime();
            tiempo = tiempoFin - tiempoInicio;
            }

            if (resultado > 0) {
                contador=contador+1;
                System.out.println("Inserciones realizadas: " + contador + "\n");
            } else {
                System.out.println("Error al cargar una consulta de inserción \n");
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        System.out.println(contador);
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(CargarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  tiempo/1000000000; 
    }
    
    
    
    public long subirConsultasOptimizado(String usuario, String contraseña){
        obtenerInsert(); 
        int t = 0;
        int batchSize = 10000; //consultas por ronda
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            conexion.setAutoCommit(false);
            statement = conexion.createStatement();
            System.out.println("1");
            int i = 0;
            long tiempoInicio = System.nanoTime();
            statement.execute("CREATE DATABASE IF NOT EXISTS pr5;\n");
            statement.execute("DROP TABLE IF EXISTS pr5.albums;");
            String crearTabla = "CREATE TABLE albums (\n" +
                "  id VARCHAR(50),\n" +
                "  name VARCHAR(255),\n" +
                "  album_group VARCHAR(255),\n"
                    + "  album_type VARCHAR(50),\n"
                    + "  release_date BIGINT,\n"
                    + "  popularity INT\n"
                    + ")";
            statement.execute(crearTabla);

            for (String consulta : consultas) {
                statement.addBatch(consulta);
                count1++;
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                    System.out.println(i + "0 Mil inserciones realizadas");
                    i=i+1;
                }
            }
            if (consultas.size() % batchSize != 0) {
                statement.executeBatch();
            }
            long tiempoFin = System.nanoTime();
            tiempoO = tiempoFin - tiempoInicio;
            count=consultas.size();
            conexion.commit();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        System.out.println(count);
        return  tiempoO/1000000000;
        
                
    }
    
    
}
