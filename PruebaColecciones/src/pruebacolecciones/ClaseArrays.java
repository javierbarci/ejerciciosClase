/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacolecciones;

import java.util.Arrays;
import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Nota;

/**
 *
 * @author Iñigo
 */
public class ClaseArrays {
    private static Alumno[] alumnos = new Alumno[10];
    
    private static int[] numeros = {
      1,5,7,21,34,45,46,89,100,105,113,121  
    };
    
    private static String[] nombres = { "Lucas", "Carlos", "Ana",
        "Paco", "daniel", "Enrique", "Pedro", "María"
    };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        crearAlumnos();
        /*String resp = JOptionPane.showInputDialog("Número a Buscar");
        int num = Integer.parseInt(resp);
        int pos = Arrays.binarySearch(numeros, num);
        if (pos >= 0){
         JOptionPane.showMessageDialog(null, numeros[pos] +
                 " está en la posición " + pos);
        } else {
            JOptionPane.showMessageDialog(null, "No está");
        }*/
        /*Arrays.sort(nombres);
        Ordenador.ordenar(nombres);
        for(String n : nombres){
            System.out.print(n + " ");
        }
        System.out.println("");
        String resp = JOptionPane.showInputDialog("Nombre a Buscar");
        int pos = Arrays.binarySearch(nombres, resp);
        if (pos >= 0){
         JOptionPane.showMessageDialog(null, nombres[pos] +
                 " está en la posición " + pos);
        } else {
            JOptionPane.showMessageDialog(null, "No está");
        }*/
        Arrays.sort(alumnos);
        //Ordenador.ordenar(alumnos);
        for(Alumno a : alumnos){
            System.out.println(a.getNombre());
        }
        String alumno = JOptionPane.showInputDialog("Alumno");
        Alumno b = new Alumno();
        b.setNombre(alumno);
        int pos = Arrays.binarySearch(alumnos, b);
        if (pos >= 0){
         JOptionPane.showMessageDialog(null, 
                 alumnos[pos].getNombre() + "\nMedia :" +
                 alumnos[pos].getMedia() +
                 " está en la posición " + pos);
        } else {
            JOptionPane.showMessageDialog(null, "No está");
        }
    }
    
    private static void crearAlumnos() {
        Alumno a = new Alumno("Juan");
        a.setNota(1, 5);
        a.setNota(2, 4);
        a.setNota(3, 6);
        alumnos[0] = a;
        a = new Alumno("Ana");
        a.setNota(1, 3);
        a.setNota(2, 9);
        a.setNota(3, 10);
        alumnos[1] = a;
        a = new Alumno("Carlos");
        a.setNota(1, 4);
        a.setNota(2, 4);
        a.setNota(3, 3);
        alumnos[2] = a;
        a = new Alumno("Pedro");
        a.setNota(1, 7);
        a.setNota(2, 2);
        a.setNota(3, 5);
        alumnos[3] = a;
        a = new Alumno("Luisa");
        a.setNota(1, 8);
        a.setNota(2, 7);
        a.setNota(3, 1);
        alumnos[4] = a;
        a = new Alumno("Alberto");
        a.setNota(1, 8);
        a.setNota(2, 4);
        a.setNota(3, 3);
        alumnos[5] = a;
        a = new Alumno("Elvira");
        a.setNota(1, 2);
        a.setNota(2, 9);
        a.setNota(3, 9);
        alumnos[6] = a;
        a = new Alumno("Miren");
        a.setNota(1, 7);
        a.setNota(2, 3);
        a.setNota(3, 4);
        alumnos[7] = a;
        a = new Alumno("Ernesto");
        Nota[] n = new Nota[3];
        n[0] = new Nota(1, 3);
        n[1] = new Nota(2, 5);
        n[2] = new Nota(3, 6);
        a.setNotas(n);
        alumnos[8] = a;
        a = new Alumno("Paco");
        a.setNotas(new Nota[]{
            new Nota(1, 6),
            new Nota(2, 2),
            new Nota(3, 7)
        });
        alumnos[9] = a;
    }
}
