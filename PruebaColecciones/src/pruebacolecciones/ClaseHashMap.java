/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacolecciones;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import modelo.Alumno;
import modelo.Nota;

/**
 *
 * @author Iñigo
 */
public class ClaseHashMap {
    private static HashMap<String,Alumno> alumnos = new HashMap<>();
    public static void main(String[] args) {
        crearAlumnos();
        /*String nombre = JOptionPane.showInputDialog("Nombre");
        Alumno a = alumnos.get(nombre);
        if (a == null){
            JOptionPane.showMessageDialog(null, "No encontrado");
        } else {
            JOptionPane.showMessageDialog(null, a.getNombre() +
                "\nMedia: " + a.getMedia());
        }*/
        // Recorrer claves
        Set<String> nombres = alumnos.keySet();
        Iterator<String> it = nombres.iterator();
        while (it.hasNext()){
            String n = it.next();
            System.out.println(n);
        }
        // Recorrer los valores
        Collection<Alumno> alum = alumnos.values();
        Iterator<Alumno> it2 = alum.iterator();
        while (it2.hasNext()){
            Alumno a = it2.next();
            System.out.println(a);
        }
        // Recorrer todo
        Set<Map.Entry<String,Alumno>> todos = alumnos.entrySet();
        Iterator<Map.Entry<String,Alumno>> al = todos.iterator();
        while (al.hasNext()){
            Map.Entry<String,Alumno> entrada = al.next();
            System.out.println(entrada.getKey() + " : " +
                entrada.getValue());
        }
    }
    
    private static void crearAlumnos() {
        Alumno a = new Alumno("Juan");
        a.setNota(1, 5);
        a.setNota(2, 4);
        a.setNota(3, 6);
        alumnos.put(a.getNombre(), a);
        a = new Alumno("Ana");
        a.setNota(1, 3);
        a.setNota(2, 9);
        a.setNota(3, 10);
        alumnos.put(a.getNombre(), a);
        a = new Alumno("Carlos");
        a.setNota(1, 4);
        a.setNota(2, 4);
        a.setNota(3, 3);
        alumnos.put(a.getNombre(), a);
        a = new Alumno("Pedro");
        a.setNota(1, 7);
        a.setNota(2, 2);
        a.setNota(3, 5);
        alumnos.put(a.getNombre(), a);
        a = new Alumno("Luisa");
        a.setNota(1, 8);
        a.setNota(2, 7);
        a.setNota(3, 1);
        alumnos.put(a.getNombre(), a);
        a = new Alumno("Alberto");
        a.setNota(1, 8);
        a.setNota(2, 4);
        a.setNota(3, 3);
        alumnos.put(a.getNombre(), a);
        a = new Alumno("Elvira");
        a.setNota(1, 2);
        a.setNota(2, 9);
        a.setNota(3, 9);
        alumnos.put(a.getNombre(), a);
        a = new Alumno("Miren");
        a.setNota(1, 7);
        a.setNota(2, 3);
        a.setNota(3, 4);
        alumnos.put(a.getNombre(), a);
        a = new Alumno("Ernesto");
        Nota[] n = new Nota[3];
        n[0] = new Nota(1, 3);
        n[1] = new Nota(2, 5);
        n[2] = new Nota(3, 6);
        a.setNotas(n);
        alumnos.put(a.getNombre(), a);
        a = new Alumno("Paco");
        a.setNotas(new Nota[]{
            new Nota(1, 6),
            new Nota(2, 2),
            new Nota(3, 7)
        });
        alumnos.put(a.getNombre(), a);
    }
}
