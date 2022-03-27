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
        
        int contadorF = 0;
        int contadorC = 0;
        int contadorDiferentesCero = 0;
        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("C:\\Users\\juans\\Documents\\NetBeansProjects\\PracticaGrafos\\src\\tiempos_1.csv"));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();
            String[] campos = linea.split(",");
            contadorC = campos.length - 1;
            /**
             * se recorre todo el archivo para conocer las 
             * dimenciones y la cantidad de datos diferentes de cero
             */
            while ((linea = bufferLectura.readLine()) != null) {
                
               contadorF++;
               // Sepapar la linea leída con el separador definido previamente
               campos = linea.split(",");
               for(int i = 1; i < campos.length; i++){
                   if(!campos[i].equals("0") && i != contadorF){
                       contadorDiferentesCero++;
                   }
               }
               //System.out.println(Arrays.toString(campos));

            }
            //System.out.println("Los datos diferentes de cero son: " +contadorDiferentesCero);
        }catch (IOException e) {
             e.printStackTrace();
        }finally {
         // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        GrafoMatrizAdyacenciaEnMatrizTripleta matriz = new GrafoMatrizAdyacenciaEnMatrizTripleta(contadorF, contadorDiferentesCero);
        bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("C:\\Users\\juans\\Documents\\NetBeansProjects\\PracticaGrafos\\src\\tiempos_1.csv"));
            // Leer una linea del archivo
            String linea = bufferLectura.readLine();
            String[] campos = linea.split(",");
            int i = 0;
            while ((linea = bufferLectura.readLine()) != null) {
                i++;
               // Sepapar la linea leída con el separador definido previamente
               campos = linea.split(",");
               for(int j = 1; j < campos.length; j++){
                   if(!campos[j].equals("0") && i != j){
                       matriz.crearAdyacencia(i, j, Integer.parseInt(campos[j]));
                   }
               }
            }
            
            //System.out.println(matriz.getMatrizTripleta().toString());
            
        }catch (IOException e) {
             e.printStackTrace();
        }
        
        matriz.tiempoYRutaDFS(5 ,4);
        System.out.println(matriz.getTiempoMenor());
        
        matriz.tiempoYRutaDFS(1 ,5);
        System.out.println(matriz.getTiempoMenor());
        matriz.tiempoYRutaDFS(3, 4);
        System.out.println(matriz.getTiempoMenor());
        
        matriz.tiempoYRutaDFS(4, 1);
        System.out.println(matriz.getTiempoMenor());
        
        matriz.tiempoYRutaDFS(2, 4);
        System.out.println(matriz.getTiempoMenor());


        //int valor = matriz.getMatrizTripleta().getCantidadAdyacencias(5);
        //System.out.println(valor);
        String sc = "";
        while (!sc.equals("6")) {            
           sc = JOptionPane.showInputDialog(null, "Ingrese la opción deseada:\n"
                + "1. Ruta más corta entre dos bases\n"
                + "2. Tiempo mínimo de enviar un mensaje entre dos bases\n"
                + "3. Validar que estaciones no pueden mandar mensajes\n"
                + "4. Validar si hay una o varias estaciones aisladas\n"
                + "4. Salir");
            try {
                switch(sc){
                    case "1":
                        
                        break;
                    case "2":
                        
                        
                        break;
                    case "3":
                        
                        
                        break;
                    case "4":
                        
                        break;
                    case "5":
                         
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
