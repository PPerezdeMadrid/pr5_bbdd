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
            File archivo = new File("C:\\Users\\golro\\Documents\\Universidad\\año 2\\segundo cuatrimestre\\Bases de datos II\\practica 5\\albums_tabulado.txt");
            Scanner scanner = new Scanner(archivo);
            String[] datos;
            while (scanner.hasNextLine()) {
                datos = scanner.nextLine().split("\t");
                if (datos.length >= 6) {
                    String name = datos[0].replaceAll("\"", "'");
                    String name_1 = datos[1].replaceAll("\"", "'");
                    String name_2 = datos[2].replaceAll("\"", "'");
                    String name_3 = datos[3].replaceAll("\"", "'");
                    String name_4 = datos[4].replaceAll("\"", "'");
                    String name_5 = datos[5].replaceAll("\"", "'");
                    consultas.add("INSERT INTO ALBUMS (name, id, album_group, album_type, release_date, popularity) VALUES ('" + name + "', '" + name_1 + "', '" + name_2 + "', '" + name_3 + "', '" + name_4 + "', '" + name_5 + "');");

                }
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
    public int cargarConsulta(String consulta, String usuario, String contraseña) {
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            statement = conexion.createStatement();
            //obtenerInsert();
            System.out.println("1");
            System.out.println(consultas);
            int resultado = 0;
            try {
                resultado = statement.executeUpdate(consulta);
                System.out.println(resultado);
            } catch (SQLException e) {
                System.err.println("Error en una sentencia: " + e.getMessage());
            }

            System.out.println("2");
            if (resultado > 0) {
                contador++;
                System.out.println("Contador: " + contador + "\n");
            } else {
                System.out.println("Error al cargar una consulta de inserción \n");
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        System.out.println(contador);
        return contador;
    }
    //Problemática: Como imprimir a tiempo real el contador

    public long medirTiempoCarga(String usuario, String contraseña) {
        long tiempoInicio = System.nanoTime();
        obtenerInsert();
        consultas.forEach(consulta -> cargarConsulta(consulta, usuario, contraseña)); //ejec cada consulta y devuelve
        consultas.forEach(consulta -> System.out.println(consulta));
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
            System.out.println(i);
        }

        return contador;
    }

}
