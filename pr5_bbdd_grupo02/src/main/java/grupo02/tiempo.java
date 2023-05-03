/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo02;

/**
 * cronometro(1) --> empieza a contar
 * cronometro(0)--> STOP
 * @author ppere
 */
public class Tiempo {
    long tiempoActual;
    long inicio;
    
    public long cronometro(int accion){ 
        inicio = System.currentTimeMillis();
        while (accion == 1) {
            tiempoActual = System.currentTimeMillis() - inicio;
            System.out.println("Tiempo transcurrido: " + tiempoActual + " ms");
            
            try {
                Thread.sleep(5000); // Esperar medio segundo antes de imprimir de nuevo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    return tiempoActual;
    }
}
