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
package matriz.tripleta;

import java.util.ArrayList;
import matriz.util.Tripleta;

public class MatrizEnTripleta {

    protected Tripleta[] tripletas;
    private int posicionDestino = 1;

    /**
     * Construye una matriz en tripleta vacia, con un tamaño de arreglo del
     * unico caso y este es que la todas las posiciones esten llenas con cero
     *
     * @param f
     * @param c
     */
    public MatrizEnTripleta(int f, int c) {
        this.tripletas = new Tripleta[1];
        Tripleta configuracion = new Tripleta(f, c, (int) 0);
        tripletas[0] = configuracion;
    }
    
    
    public MatrizEnTripleta(int f, int c,int diferentesDeCero) {
        this.tripletas = new Tripleta[diferentesDeCero + 1];
        Tripleta configuracion = new Tripleta(f, c, diferentesDeCero);
        tripletas[0] = configuracion;
    }
    
    public void ingresarCelda(int filaDestino, int columnaDestino, Object datoDestino){
     tripletas[posicionDestino]= new Tripleta(filaDestino,columnaDestino,(int)datoDestino);
     posicionDestino++;
     
    }
    /**
     * Fijar un valor valido de Cero en una celda
     *
     * @param filaDestino
     * @param columnaDestino
     * @param datoDestino
     * @throws java.lang.Exception
     */
    public void setCelda(int filaDestino, int columnaDestino, Object datoDestino) throws Exception {
        Tripleta configuracion = tripletas[0];

        /**
         * Valido limites
         */
        int filas = configuracion.getF();
        int columnas = configuracion.getC();
        
        if (filaDestino <= 0 || filas < filaDestino || columnaDestino <= 0 || columnas < columnaDestino) {
            throw new Exception("Esta fuera de los limites de la matriz");
        }

        /**
         * Fijamos el valor
         */
        int cantidadDatosActual = (int) configuracion.getV();
        Tripleta celdaDestino = null;
        int posicionInsertar = cantidadDatosActual + 1;
        for (int r = 1; cantidadDatosActual >= r; r++) {
            Tripleta tripletaRecorrido = tripletas[r];
            int filaRecorrido = tripletaRecorrido.getF();
            if (filaDestino > filaRecorrido) {
                // No hago nada
                
            } else if (filaDestino == filaRecorrido) {
                int columnaRecorrido = tripletaRecorrido.getC();
                if (columnaDestino > columnaRecorrido) {
                    // No hago nada  
                } else if (columnaDestino == columnaRecorrido) {
                    // La celda esta en el arreglo de tripletas
                    // Se debe realizar reemplazo del valor
                    celdaDestino = tripletaRecorrido;
                } else {
                    posicionInsertar = r;
                    break;
                }
            } else {
                posicionInsertar = r;
                break;
            }
        }

        /**
         * Valido si encontre la celda en el arreglo
         */
        if (celdaDestino != null) {
            celdaDestino.setV(datoDestino);
        } else {
            // Realiza crecimiento dinamico del arreglo de tripletas
            // Copiando en un nuevo arreglo todas las tripletas
            celdaDestino = new Tripleta(filaDestino, columnaDestino, datoDestino);
            Tripleta[] nuevasTripletas = new Tripleta[cantidadDatosActual + 1 + 1];
            configuracion.setV(cantidadDatosActual + 1);
            for (int i = 0; i < nuevasTripletas.length; i++) {
                if (i < posicionInsertar) {
                    nuevasTripletas[i] = tripletas[i];
                } else if (i == posicionInsertar) {
                    nuevasTripletas[i] = celdaDestino;
                } else {
                    nuevasTripletas[i] = tripletas[i - 1];
                }
            }
            tripletas = nuevasTripletas;
        }
    }

    public void setTripleta(int cv, Tripleta t) {
        tripletas[cv] = t;
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        // Obtengo la configuración de la matriz, fr y cr y la cantidadValores
        Tripleta configuracion = this.tripletas[0];
        int cantidadFilasMatriz = configuracion.getF();
        int cantidadColumnasMatriz = configuracion.getC();
        int valoresDiferentesCero = (Integer) configuracion.getV();

        int posicionArreglo = 1;

        cadena.append("\t");
        for (int columnasVirtuales = 1; columnasVirtuales <= cantidadColumnasMatriz; columnasVirtuales++) {
            cadena.append(columnasVirtuales + "\t");
        }
        cadena.append("\n");
        // Recorrido por una matriz virtual m x n
        for (int filasVirtuales = 1; filasVirtuales <= cantidadFilasMatriz; filasVirtuales++) {
            cadena.append(" " + filasVirtuales  + " \t");
            for (int columnasVirtuales = 1; columnasVirtuales <= cantidadColumnasMatriz; columnasVirtuales++) {
                if (posicionArreglo <= valoresDiferentesCero) {
                    // Estoy en una posicion valida en el arreglo
                    Tripleta posibleTripletaMostrar = tripletas[posicionArreglo];
                    int filaCeldaMostrar = posibleTripletaMostrar.getF();
                    int columnaCeldaMostrar = posibleTripletaMostrar.getC();
                    if (filasVirtuales == filaCeldaMostrar) {
                        if (columnasVirtuales == columnaCeldaMostrar) {
                            Object valorCeldaMostrar = posibleTripletaMostrar.getV();
                            if (valorCeldaMostrar != null) {
                                cadena.append(valorCeldaMostrar + "\t");
                            } else {
                                cadena.append("0.0" + "\t");
                            }
                            posicionArreglo++;
                        } else {
                            cadena.append("0.0" + "\t");
                        }
                    } else {
                        cadena.append("0.0" + "\t");
                    }
                } else {
                    cadena.append("0.0" + "\t");
                }
            }
            cadena.append("\n");
        }

        return cadena.toString();
    }

    public Tripleta[] getTripletas() {
        return tripletas;
    }

    public int getCantidadValores() {
        return (int) tripletas[0].getV();
    }

    public int buscarFila(Object valor){
        int i = 1;
        while( i < tripletas.length && !tripletas[i].getV().equals(valor)){
            i++;
        }
        if(tripletas[i-1].equals(valor)){
            return tripletas[i-1].getF();
        }
        return 0;
    }
    
    /*public Tripleta getTripleta(int valor){
        int i = 1;
        while( i < tripletas.length && tripletas[i].getF() != valor){
            i++;
        }
        if(tripletas[i-1].getF() == valor){
            return tripletas[i-1];
        }
        
        return null;
    }*/
    
    public Tripleta getTripleta(int fila, int columna){
        int i = 1;
        while( i < tripletas.length){
            if(fila == tripletas[i].getF() && columna == tripletas[i].getC()){
                return tripletas[i];
            }
            i++;
        }
        return null;
    }
    
    /*public int getCantidadAdyacencias(int fila) {
        int i = 1;
        int contadorAdyacencias = 0;
        while( i < tripletas.length && tripletas[i].getF() != fila){
            i++;
            int j=i;
            while(j < tripletas.length && tripletas[j].getF() == fila){
                contadorAdyacencias++;
                j++;
            }
        }
        return contadorAdyacencias;
    }*/
    
    public ArrayList getAdyacencias(int fila) {
        int i = 1;
        ArrayList<Integer> Adyacencias = new ArrayList();
        while( i < tripletas.length && tripletas[i].getF() < fila+1){
            if( tripletas[i].getF() == fila){
                Adyacencias.add(tripletas[i].getC());
            }
            i++;
        }
        return Adyacencias;
    }
    
    public ArrayList getAdyacenciasColumnas(int base) {
        int i = 1;
        ArrayList<Integer> Adyacencias = new ArrayList();
        while( i < tripletas.length){
            if( tripletas[i].getC() == base){
                Adyacencias.add(tripletas[i].getF());
            }
            i++;
        }
        return Adyacencias;
    }
    
    public int getPosicion(int f, int c){
        int i = 0;
        while(i < tripletas.length && (tripletas[i].getF() != f)){
                //contadorAdyacencias++;
                i++;
        }
        return 0 ;
    }

}
