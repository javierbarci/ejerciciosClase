/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacolecciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import modelo.Alumno;
import modelo.Nota;

/**
 *
 * @author Iñigo
 */
public class ClaseArrayList {
    private static ArrayList<Alumno> alumnos = new ArrayList();
    public static void main(String[] args) {
        crearAlumnos();
        // Mostrar alumnos. 
        /*for (int i = 0; i < alumnos.size(); i++) {
            System.out.println(alumnos.get(i));
        }*/
        /*Iterator<Alumno> it = alumnos.iterator();
        while (it.hasNext()){
            Alumno a = it.next();
            System.out.println(a);
        }*/
        /*ListIterator<Alumno> lis = alumnos.listIterator();
        lis.next();
        lis.next();
        lis.next();
        lis.next();
        Alumno a = lis.previous();
        System.out.println(a);*/
        for(Alumno a : alumnos){
            System.out.println(a);
        }
        System.out.println("------------");
        //alumnos.remove(1);
        //Alumno al = alumnos.get(alumnos.size()-1);
        Alumno al = new Alumno("Carlos");
        System.out.println("Posición : " + alumnos.indexOf(al));
        alumnos.remove(al);
        for(Alumno a : alumnos){
            System.out.println(a);
        }
    }
    
    private static void crearAlumnos() {
        Alumno a = new Alumno("Juan");
        a.setNota(1, 5);
        a.setNota(2, 4);
        a.setNota(3, 6);
        alumnos.add(a);
        a = new Alumno("Ana");
        a.setNota(1, 3);
        a.setNota(2, 9);
        a.setNota(3, 10);
        alumnos.add(a);
        a = new Alumno("Carlos");
        a.setNota(1, 4);
        a.setNota(2, 4);
        a.setNota(3, 3);
        alumnos.add(a);
        a = new Alumno("Pedro");
        a.setNota(1, 7);
        a.setNota(2, 2);
        a.setNota(3, 5);
        alumnos.add(a);
        a = new Alumno("Luisa");
        a.setNota(1, 8);
        a.setNota(2, 7);
        a.setNota(3, 1);
        alumnos.add(a);
        a = new Alumno("Alberto");
        a.setNota(1, 8);
        a.setNota(2, 4);
        a.setNota(3, 3);
        alumnos.add(a);
        a = new Alumno("Elvira");
        a.setNota(1, 2);
        a.setNota(2, 9);
        a.setNota(3, 9);
        alumnos.add(a);
        a = new Alumno("Miren");
        a.setNota(1, 7);
        a.setNota(2, 3);
        a.setNota(3, 4);
        alumnos.add(a);
        a = new Alumno("Ernesto");
        Nota[] n = new Nota[3];
        n[0] = new Nota(1, 3);
        n[1] = new Nota(2, 5);
        n[2] = new Nota(3, 6);
        a.setNotas(n);
        alumnos.add(a);
        a = new Alumno("Paco");
        a.setNotas(new Nota[]{
            new Nota(1, 6),
            new Nota(2, 2),
            new Nota(3, 7)
        });
        alumnos.add(0,a);
    }
}
