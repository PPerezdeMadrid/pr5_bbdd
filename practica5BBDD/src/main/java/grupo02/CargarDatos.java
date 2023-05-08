/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo02;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ppere
 */
public class CargarDatos {
    String url = "jdbc:mysql://localhost:3306/pr5";  
    String usuario; 
    String password; 
    Statement statement;
    Connection conexion;
    String respuesta;
    int contador;
    ArrayList<String> consultas = new ArrayList<>();
    
    /*
    *Este método obtiene del archivo tabulado un arraylist de "INSERT..."
    *@pppere
    */
    public int obtenerInsert(){
         try {
            File archivo = new File("artists_tabulado.txt");
            Scanner scanner = new Scanner(archivo);
            String[] datos;
            while (scanner.hasNextLine()) {
                datos = scanner.nextLine().split("\t");
                //float popularity = Float.parseFloat(datos[5]);
                consultas.add("INSERT INTO ALBUMS (id, name, album_group, album_type, release_date, popularity) VALUES ('" + datos[0] + "','"+ datos[1] +", '"+datos[2] + "','"+datos[3]+"', '"+datos[4]+"','" + datos[5] + "');");
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
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
    public int cargarConsulta(String consulta){
         try {
           conexion = DriverManager.getConnection(url, usuario, password);
           statement = conexion.createStatement();
           int resultado = statement.executeUpdate(consulta);
           if(resultado>0){
                contador++;
                System.out.println("Contador: " + contador +"\n");
           }else{
               System.out.println("Error al cargar una consulta de inserción \n");
               }
           
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return contador;
    } 
    //Problemática: Como imprimir a tiempo real el contador
   
    public long medirTiempoCarga(){
     long tiempoInicio = System.nanoTime();
     consultas.forEach(consulta -> cargarConsulta(consulta)); //ejec cada consulta y devuelve
     
     long tiempoFin = System.nanoTime();
     long tiempo = tiempoFin - tiempoInicio;
     
     return tiempo; 
    }
    
   }
