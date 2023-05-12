/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    Statement statement;
    Connection conexion;
    String respuesta;
    int contador;
    ArrayList<String> consultas = new ArrayList<>(); 

    /*
    *Este método obtiene del archivo tabulado un arraylist de "INSERT..."
    *@pppere
     */
    public int obtenerInsert() {
        try {
            
            int n=0;
            String linea;
            String[] datos;
             
            File archivo = new File("albums_tabulado700.txt");
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));
            
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println("cargando inserts" + n);
                datos = linea.split("\t");
            
                 if (datos.length >= 6) {
                String name = datos[0].replaceAll("\"", "'").replaceAll("'", "`");
                String name_1 = datos[1].replaceAll("\"", "'").replaceAll("'", "`");
                String name_2 = datos[2].replaceAll("\"", "'").replaceAll("'", "`");
                String name_3 = datos[3].replaceAll("\"", "'").replaceAll("'", "`");
                String name_4 = datos[4].replaceAll("\"", "'").replaceAll("'", "`");
                String name_5 = datos[5].replaceAll("\"", "'").replaceAll("'", "`");
                
                String consulta = "INSERT INTO ALBUMS (name, id, album_group, album_type, release_date, popularity) VALUES ('" + name + "', '" + name_1 + "', '" + name_2 + "', '" + name_3 + "'," + name_4 + "," + name_5 + ");\n";
                
                consultas.add(consulta);
                n++; //n --> NUMERO DE CONSULTAS AÑADIDAS EN EL ARRAY
                    
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
    public void cargarConsulta(String consulta, String usuario, String contraseña) {
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            statement = conexion.createStatement();
            System.out.println("1");
            int resultado = 0;
            try {
                resultado = statement.executeUpdate(consulta);
            } catch (SQLException e) {
                System.err.println("Error en una sentencia: " + e.getMessage());
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
        /**
         try {
            if (conexion != null) {
                conexion.close();
                System.out.println("EX");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
        */
                
    }
    //Problemática: Como imprimir a tiempo real el contador

    public long medirTiempoCarga(String usuario, String contraseña) {
        long tiempoInicio = System.nanoTime();
        obtenerInsert();
        consultas.forEach(consulta -> cargarConsulta(consulta, usuario, contraseña)); //ejec cada consulta y devuelve
        //consultas.forEach(consulta -> System.out.println(consulta));
        long tiempoFin = System.nanoTime();
        long tiempo = tiempoFin - tiempoInicio;
        

        return tiempo;
    }

    public int contador(String usuario, String contraseña) {
        
        obtenerInsert();
        int i = 0;
        while (i < consultas.size()) {
            cargarConsulta(consultas.get(i), usuario, contraseña);
            i++;
        }

        return contador;
    }

    
}
