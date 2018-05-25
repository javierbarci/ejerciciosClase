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
public class GestionAlumnos {

    private static Alumno[] alumnos = new Alumno[10];

    public static void main(String[] args) {
        crearAlumnos();
        String resp;
        int opc;
        do {
            resp = JOptionPane.showInputDialog(
                    "Gestión de Alumnos\n"
                    + "==================\n\n"
                    + "1.- Listar Alumnos\n"
                    + "2.- Buscar Notas de un Alumno\n"
                    + "3.- Ver Aprobados\n"
                    + "4.- Salir\n\n"
                    + "Opción [1-4]:");
            opc = Integer.parseInt(resp);
            switch (opc) {
                case 1:
                    listarAlumnos();
                    break;
                case 2:
                    buscarAlumno();
                    break;
                case 3:
                    verAprobados();
                    break;
            }
        } while (opc != 4);
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

    private static void listarAlumnos() {
        String listado = "<html><body>";
        listado += "<h1>Listado de Alumnos</h1>";
        listado += "<table border=1><tr><th>Nombre</th>"
                + "<th>PRO</th><th>BD</th><th>LM</th>"
                + "<th>Media</th></tr>";
        for (int i = 0; i < alumnos.length; i++) {
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

    private static void buscarAlumno() {
        String nombre;
        nombre = JOptionPane.showInputDialog("Nombre del Alumno");
        int i;
        for (i = 0;
                i < alumnos.length
                && !nombre.equalsIgnoreCase(alumnos[i].getNombre());
                i++);
        if (i < alumnos.length) {
            JOptionPane.showMessageDialog(null,
                    "Alumno : " + alumnos[i].getNombre()
                    + "\n\nPRO: " + alumnos[i].getNotas()[0].getNota()
                    + "\nBD: " + alumnos[i].getNotas()[1].getNota()
                    + "\nLM: " + alumnos[i].getNotas()[2].getNota()
                    + "\n\nMedia : " + alumnos[i].getMedia());
        } else {
            JOptionPane.showMessageDialog(null,
                    "No existe ningún alumno con ese nombre",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void verAprobados() {
        int numAprobados = 0;
        int i;
        for (i = 0; i < alumnos.length
                && alumnos[i].getSuspensos() > 0; i++);
        if (i < alumnos.length) {
            // He encontrado al menos un aprobado
            String listado = "<html><h1>Alumnos Aprobados</h1>";
            listado += "<table border=1><tr><th>Alumno</th><th>Nota Media</th></tr>";
            for ( ; i < alumnos.length; i++) {
                if (alumnos[i].getSuspensos() == 0) {
                    listado += "<tr><td>" + alumnos[i].getNombre()
                            + "</td><td>" + alumnos[i].getMedia()
                            + "</td></tr>";
                    numAprobados++;
                }
            }
            listado += "</table><h3>Han aprobado " + numAprobados
                    + " alumnos</h3></html>";
            JOptionPane.showMessageDialog(null,
                    listado,
                    "Alumnos Aprobados",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, 
                "No hay ningún alumno aprobado");
        }
    }
}
