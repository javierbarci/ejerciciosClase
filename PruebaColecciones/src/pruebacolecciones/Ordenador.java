/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacolecciones;

/**
 *
 * @author Iñigo
 */
public class Ordenador {
    public static void ordenar(Comparable[] matriz){
        for(int i=0;i<matriz.length-1;i++){
            for(int j=i+1; j<matriz.length; j++){
                if (matriz[i].compareTo(matriz[j])>0){
                    Comparable temp = matriz[i];
                    matriz[i] = matriz[j];
                    matriz[j] = temp;
                }
            }
        }
    }
}
