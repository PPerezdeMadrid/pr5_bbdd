/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo02;

/**
 *
 * @author ppere
 */
public class tiempo {
    long tiempoActual;
    long inicio;
    
    public long cronometro(int accion){ //accion, si pulsa el boton start devuelve 1 y empieza si pulsa stop devuelve 0
        inicio = System.currentTimeMillis();
        while (accion == 0) {
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
