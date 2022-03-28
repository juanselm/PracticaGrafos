/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicagrafos;

import grafos.matrizadyacencia.tripleta.GrafoMatrizAdyacenciaEnMatrizTripleta;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author juans
 */
public class PracticaGrafos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        GrafoMatrizAdyacenciaEnMatrizTripleta matriz = new GrafoMatrizAdyacenciaEnMatrizTripleta(0,0);
        matriz = matriz.load();
        
        //matriz.tiempoYRutaDFS(2 ,4);
        //System.out.println(matriz.getTiempoMenor());
        //System.out.println(matriz.getPilaRutaMenor().toString());
        
        int baseA, baseB;
        String sc = "";
        while (!sc.equals("6")) {            
           sc = JOptionPane.showInputDialog(null, "Ingrese la opción deseada:\n"
                + "1. Ruta más corta entre dos bases\n"
                + "2. Tiempo mínimo para enviar un mensaje entre dos bases\n"
                + "3. Validar que estaciones no pueden mandar mensajes\n"
                + "4. Validar si hay una o varias estaciones aisladas\n"
                + "5. Salir");
            try {
                switch(sc){
                    case "1":
                            baseA = Integer.parseInt(JOptionPane.showInputDialog(null , "Ingrese el número de la base inicial:"));
                            baseB = Integer.parseInt(JOptionPane.showInputDialog(null , "Ingrese el número de la base destino:"));
                            matriz.tiempoYRutaDFS(baseA ,baseB);
                            if(matriz.getPilaRutaMenor().size() != 0){
                                JOptionPane.showMessageDialog(null, "La ruta más corta desde la base " + baseA + " hasta la base "+ baseB + " es: "+ matriz.getPilaRutaMenor().toString());
                            }else{
                                JOptionPane.showMessageDialog(null,"No existe ninguna ruta para llegar desde la base " + baseA + " hasta la base "+ baseB);
                            }
                        break;
                    case "2":
                            baseA = Integer.parseInt(JOptionPane.showInputDialog(null , "Ingrese el número de la base inicial:"));
                            baseB = Integer.parseInt(JOptionPane.showInputDialog(null , "Ingrese el número de la base destino:"));
                            matriz.tiempoYRutaDFS(baseA ,baseB);
                            if(matriz.getTiempoMenor() != 0){
                                JOptionPane.showMessageDialog(null, "El menor tiempo desde la base " + baseA + " hasta la base "+ baseB + " es: "+ matriz.getTiempoMenor() + "segudos");
                            } else{
                                JOptionPane.showMessageDialog(null,"No se pudo mandar el mensaje ya que no existe una ruta entre las dos bases");
                            }
                        break;
                    case "3":
                            
                        break;
                    case "4":
                            
                        break;
                    case "5":
                            System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Ha ingresado una opción incorrecta");
                        
                }
            } catch (Exception e) {
                if(sc == null){
                  JOptionPane.showMessageDialog(null, "Programa finalizado");
                  System.exit(0);
                };
            }
        }
    }
}
