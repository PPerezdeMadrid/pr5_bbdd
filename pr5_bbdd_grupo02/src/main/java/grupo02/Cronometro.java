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
public class Cronometro {
    long tiempoActual;
    long inicio;
    
    public long cronometro(int accion){ 
        inicio = System.currentTimeMillis();
        while (accion == 1) {
            tiempoActual = System.currentTimeMillis() - inicio;
            System.out.println("Tiempo transcurrido: " + tiempoActual + " ms");
        }
        return tiempoActual;
    }
}

/*
* accion = 1;
* Cronometro crono = new Cronometro();
* while(accion=1){
*   crono.cronometro()
*    try {
        Thread.sleep(1000); // Esperar un segundo
    } catch (InterruptedException e) {
        e.printStackTrace();
    } 
* 
*/
