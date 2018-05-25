/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio;

import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Nota;

/**
 *
 * @author Iñigo
 */
public class OrdenacionInserción {

    private static Alumno[] alumnos = new Alumno[20];
    private static int numAlumnos = 10;
    
    public static void main(String[] args) {
        crearAlumnos();
        // Ordenación por INSERCIÓN
        for (int i = 0; i < numAlumnos-1; i++) {
            for (int j = i+1; j < numAlumnos; j++) {
                // Si NO ESTÁN ORDENADOS i y j los INTERCAMBIAMOS!
                if (alumnos[i].getNombre()
                    .compareToIgnoreCase(alumnos[j].getNombre())>0){
                    Alumno temp = alumnos[i];
                    alumnos[i] = alumnos[j];
                    alumnos[j] = temp;
                }
            }
        }
        listarAlumnos();
    }

    private static void listarAlumnos() {
        String listado = "<html><body>";
        listado += "<h1>Listado de Alumnos</h1>";
        listado += "<table border=1><tr><th>Nombre</th>"
                + "<th>PRO</th><th>BD</th><th>LM</th>"
                + "<th>Media</th></tr>";
        for (int i = 0; i < numAlumnos; i++) {
            listado += "<tr><td>" + alumnos[i].getNombre()
                    + "</td><td>" + alumnos[i].getNotas()[0].getNota()
                    + "</td><td>" + alumnos[i].getNotas()[1].getNota()
                    + "</td><td>" + alumnos[i].getNotas()[2].getNota()
                    + "</td><td>" + alumnos[i].getMedia()
                    + "</td></tr>";
        }
        listado += "</table></body></html>";
        JOptionPane.showMessageDialog(null, listado,
                "Listado de Alumnos",
                JOptionPane.PLAIN_MESSAGE);
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
