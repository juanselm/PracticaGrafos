/*
 * Copyright 2019 Carlos Alejandro Escobar Marulanda ealejandro101@gmail.com
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the 
 * Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package grafos.matrizadyacencia.tripleta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import matriz.tripleta.MatrizEnTripleta;
import matriz.util.Tripleta;

/**
 *
 * @author alejandroescobar
 */
public class GrafoMatrizAdyacenciaEnMatrizTripleta {

    private MatrizEnTripleta matrizAdyacencia;
    private boolean[] visitados;
    private int tiempoNuevo, tiempoMenor;
    private Stack<Integer> pilaRutaMenor = new Stack(), pilaRutaNueva = new Stack();
    private boolean hayadoMenor = false;

    public GrafoMatrizAdyacenciaEnMatrizTripleta(int cantidadVertices) {
        matrizAdyacencia = new MatrizEnTripleta(cantidadVertices, cantidadVertices);
        visitados = new boolean[matrizAdyacencia.getTripletas()[0].getF()];
    }
    
    public GrafoMatrizAdyacenciaEnMatrizTripleta(int cantidadVertices, int diferentesDeCero) {
        matrizAdyacencia = new MatrizEnTripleta(cantidadVertices, cantidadVertices, diferentesDeCero);
        visitados = new boolean[matrizAdyacencia.getTripletas()[0].getF()];
    }
    
    public MatrizEnTripleta getMatrizTripleta (){
        return matrizAdyacencia;
    }
    
    public void crearAdyacencia(int i, int j) throws Exception {
        matrizAdyacencia.setCelda(i, j, 1);
    }
    
    public void crearAdyacencia(int i, int j,int tiempo) throws Exception {
        matrizAdyacencia.ingresarCelda(i, j, tiempo);
    }
    
    public int getTiempoMenor() {
        return tiempoMenor;
    }

    public Stack<Integer> getPilaRutaMenor() {
        return pilaRutaMenor;
    }

    public int grado(int vertice) {
        int cv;
        cv = matrizAdyacencia.getCantidadValores();
        int grado = 0;
        Tripleta[] tripletas = matrizAdyacencia.getTripletas();
        for (int i = 1; i <= cv; i++) {
            int fila = tripletas[i].getF();
            if (fila < vertice) {
                continue;
            }
            if (fila == vertice) {
                grado++;
            } else {
                break;
            }
        }
        return grado;
    }
    
    private void comparacionesDFS(int r, int b ) {
        visitados[r-1] = true;
        pilaRutaNueva.add(r);
        if(r == b){
            if(!hayadoMenor){
                tiempoMenor = tiempoNuevo;
                pilaRutaMenor = (Stack<Integer>) pilaRutaNueva.clone();
                hayadoMenor = true;
            }else if(tiempoNuevo < tiempoMenor ){
                tiempoMenor = tiempoNuevo;
                pilaRutaMenor = (Stack<Integer>) pilaRutaNueva.clone();              
            }
            return;
        }
        ArrayList<Integer> basesAdyacentes = matrizAdyacencia.getAdyacencias(r);
        for (int w = 0; w < basesAdyacentes.size(); w++) {
            if ( !visitados[basesAdyacentes.get(w)-1]){
                int tiempo = (int)matrizAdyacencia.getTripleta(r, basesAdyacentes.get(w)).getV();
                tiempoNuevo += tiempo;
                comparacionesDFS(basesAdyacentes.get(w), b);
                visitados[basesAdyacentes.get(w)-1] = false;
                pilaRutaNueva.pop();
                tiempoNuevo -= tiempo;
            }
        }
    }
    
    public void tiempoYRutaDFS(int b, int r ){
        hayadoMenor = false;
        tiempoMenor = 0;
        tiempoNuevo = 0;
        pilaRutaNueva.removeAllElements();
        pilaRutaMenor.removeAllElements();
        for(int i =0;i<visitados.length;i++){
            visitados[i] = false;
        }
        comparacionesDFS(b, r);
    }
    
    public String noSalen(){
        int i = 0;
        String aisladas = "";
        boolean primeraVez = true;
        int totalBases = matrizAdyacencia.getTripletas()[0].getC();
        while(i < totalBases){
            if(matrizAdyacencia.getAdyacencias(i+1).isEmpty()){
                if(primeraVez){
                    aisladas = "Estas son las bases desde las que no se pueden enviar mensajes: \n";
                    aisladas = aisladas + (i + 1);
                    primeraVez = false;
                }else{
                    aisladas = aisladas + "," + (i + 1);
                }
            }
            i++;
        }
        if(aisladas.equals("")){
            aisladas = "Todas las bases pueden enviar mensajes";
        }
        return aisladas;
    }
    
    public String aisladas(){
        int i = 0;
        String aisladas = "";
        boolean primeraVez = true;
        int totalBases = matrizAdyacencia.getTripletas()[0].getC();
        while(i < totalBases){
            if(matrizAdyacencia.getAdyacencias(i+1).isEmpty()){
                if(matrizAdyacencia.getAdyacenciasColumnas(i+1).isEmpty()){
                   if(primeraVez){
                        aisladas = "Estas son las bases aisladas: \n";
                        aisladas = aisladas + (i + 1);
                        primeraVez = false;
                    }else{
                        aisladas = aisladas + "," + (i + 1);
                    } 
                }                
            }
            i++;
        }
        if(aisladas.equals("")){
            aisladas = "No hay bases aisladas";
        }
        return aisladas;
    }
    
    public GrafoMatrizAdyacenciaEnMatrizTripleta load() throws Exception{
        int contadorF = 0;
        int contadorC = 0;
        int contadorDiferentesCero = 0;
        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("../PracticaGrafos/src/tiempos_1.csv"));

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

            }
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
            bufferLectura = new BufferedReader(new FileReader("../PracticaGrafos/src/tiempos_1.csv"));
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
        return matriz;
    }
    
    /**
     * public int mayorGrado() { int cantidadValores = (int)
     * tripletas[0].getV(); int gradoMayor = 0; int gradoTemporal = 0; int
     * verticeGradoMayor = 0; int verticeRecorrido = 1; for (int i = 1; i <= cantidadValores; i++) {
     * int f = tripletas[i].getF();
     * if (f == verticeRecorrido) {
     * gradoTemporal++;
     * } else if (f > verticeRecorrido) { if (gradoMayor < gradoTemporal) {
     * gradoMayor = gradoTemporal; verticeGradoMayor = verticeRecorrido; }
     * verticeRecorrido = f; i--; gradoTemporal = 0; } }
     *
     * if (gradoMayor < gradoTemporal) { gradoMayor = gradoTemporal;
     * verticeGradoMayor = verticeRecorrido; }
     *
     * return verticeGradoMayor;
    }
     */
    /**
     * Construye un Grafo Representado en Lista ligada de adyacencia basandose
     * en el grafo de la Matriz de Adyacencia almacenada en Matriz dispersa de
     * Tripletas
     *
     * @return
     */
    // public GrafoListaLigadaAdyacencia parseGrafoListaLigadaAdyacencia() {
    /**
     * Creo el arreglo inicial
     */
    //  int cantidadVertices = (int) tripletas[0].getF();
    //  Nodo[] arregloGrafoListaLigadaAdyacencia = new Nodo[cantidadVertices];
    /**
     * Recorrido de la matriz de tripletas
     */
    //  int cantidadValores = (int) tripletas[0].getV();
    //   for (int i = 1; i <= cantidadValores; i++) {
    //      int f = tripletas[i].getF();
    //     int c = tripletas[i].getC();
    /**
     * Ingresar el verticeAdyancete a la lista ligada segun la posición en el
     * arreglo
     */
    //   Nodo verticeAdyacente = new Nodo(c);
    //   Nodo nodoViejoPrimero = arregloGrafoListaLigadaAdyacencia[f - 1];
    //   verticeAdyacente.setLiga(nodoViejoPrimero);
    //   arregloGrafoListaLigadaAdyacencia[f - 1] = verticeAdyacente;
    //   }
    //   return new GrafoListaLigadaAdyacencia(arregloGrafoListaLigadaAdyacencia);
    //  }
}
