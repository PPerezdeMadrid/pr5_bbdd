/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.ContarInsert to edit this template
 */
package grupo02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author ppere
 */
public class ContarInsert {
    public int contarInsert(){
        String rutaArchivo = "albums_tabulado.txt";
        AtomicInteger contador = new AtomicInteger(0); //Variable atómica
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().startsWith("INSERT")) {
                    contador.incrementAndGet();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
        System.out.println("Número de inserciones en el archivo: " + contador.get());
        return contador.get();
    }
    
}

   
