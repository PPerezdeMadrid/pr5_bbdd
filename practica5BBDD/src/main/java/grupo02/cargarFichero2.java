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
public class cargarFichero2 {
     String url = "jdbc:mysql://localhost:3306/pr5";  
    String usuario; 
    String password; 
    Statement statement;
    Connection conexion;
    String respuesta;
    int contador;
    
    public int obtenerInsert(){
        ArrayList<String> consultas = new ArrayList<>();
         try {
            File archivo = new File("/ruta/al/archivo/artists_tabulado.txt");
            Scanner scanner = new Scanner(archivo);
            String[] datos;
            while (scanner.hasNextLine()) {
                datos = scanner.nextLine().split("\t");
                //float popularity = Float.parseFloat(datos[5]);
                consultas.add("INSERT INTO ALBUMS (id, name, album_group, album_type, release_date, popularity) VALUES ('" + datos[0] + "','"+ datos[1] +", '"+datos[2] + "','"+datos[3]+"', '"+datos[4]+"','" + datos[5] + "');");
            }
            
            //metodo consultar: etodo consultar: ejecutar consulta, devuelve true, n+1 (imprimir n)
            //¿cuando mide tiempo? --> empeza ejecutar
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
        }
         return 0;
    }
    
    private void cargarDatos(String usuario, String password){
    
        try {

            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            //statement.executeQuery(obtenerInsert());
            contador =+1 ;
            

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

    }

    
}
