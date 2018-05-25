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
public class GestionAlumnosMatrizNoLlena {

    private static Alumno[] alumnos = new Alumno[30];
    private static int numAlumnos = 0;

    public static void main(String[] args) {
        crearAlumnos();
        numAlumnos = 10;
        String resp;
        int opc;
        do {
            resp = JOptionPane.showInputDialog(
                    "Gestión de Alumnos\n"
                    + "==================\n\n"
                    + "1.- Listar Alumnos\n"
                    + "2.- Buscar Notas de un Alumno\n"
                    + "3.- Ver Aprobados\n"
                    + "4.- Nuevo Alumno\n"
                    + "5.- Eliminar Alumno\n"         
                    + "6.- Salir\n\n"
                    + "Opción [1-6]:");
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
                case 4:
                    nuevoAlumno();
                    break;
                case 5:
                    eliminarAlumno();
                    break;
            }
        } while (opc != 6);
    }

    private static void crearAlumnos() {
        Alumno a = new Alumno("Ana");
        a.setNota(1, 5);
        a.setNota(2, 4);
        a.setNota(3, 6);
        alumnos[0] = a;
        a = new Alumno("Carlos");
        a.setNota(1, 3);
        a.setNota(2, 9);
        a.setNota(3, 10);
        alumnos[1] = a;
        a = new Alumno("Enrique");
        a.setNota(1, 4);
        a.setNota(2, 4);
        a.setNota(3, 3);
        alumnos[2] = a;
        a = new Alumno("Eva");
        a.setNota(1, 7);
        a.setNota(2, 2);
        a.setNota(3, 5);
        alumnos[3] = a;
        a = new Alumno("Luisa");
        a.setNota(1, 8);
        a.setNota(2, 7);
        a.setNota(3, 1);
        alumnos[4] = a;
        a = new Alumno("Marcos");
        a.setNota(1, 8);
        a.setNota(2, 7);
        a.setNota(3, 5);
        alumnos[5] = a;
        a = new Alumno("Marta");
        a.setNota(1, 2);
        a.setNota(2, 9);
        a.setNota(3, 9);
        alumnos[6] = a;
        a = new Alumno("Miren");
        a.setNota(1, 7);
        a.setNota(2, 3);
        a.setNota(3, 4);
        alumnos[7] = a;
        a = new Alumno("Paco");
        Nota[] n = new Nota[3];
        n[0] = new Nota(1, 6);
        n[1] = new Nota(2, 5);
        n[2] = new Nota(3, 6);
        a.setNotas(n);
        alumnos[8] = a;
        a = new Alumno("Pepa");
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

    private static void buscarAlumno() {
        String nombre;
        nombre = JOptionPane.showInputDialog("Nombre del Alumno");
        /*
        * 
        * Búsqueda DESORDENADA
        *
        */
        /*int i;
        for (i = 0;
                i < numAlumnos
                && !nombre.equalsIgnoreCase(alumnos[i].getNombre());
                i++);
        if (i < numAlumnos) {
            // ENCONTRADO  
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
        }*/
        
        /*
        * 
        * Búsqueda ORDENADA
        *
        */
        /*int i;
        for (i = 0;
                i < numAlumnos
                && nombre.compareToIgnoreCase(alumnos[i].getNombre())>0;
                i++);
        if (i < numAlumnos 
            && nombre.equalsIgnoreCase(alumnos[i].getNombre())) {
            // ENCONTRADO  
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
        }*/
        /*
        * 
        * Búsqueda BINARIA
        *
        */
        int menor = 0;
        int mayor = numAlumnos - 1;
        int medio = (menor+mayor) / 2;
        while (menor <= mayor && 
            !nombre.equalsIgnoreCase(alumnos[medio].getNombre())){
            if (nombre.compareToIgnoreCase(alumnos[medio].getNombre())>0){
                // Está en la parte superior
                menor = medio + 1;
            } else {
                // Está en la parte inferior
                mayor = medio - 1;
            }
            // Recalculamos el punto medio
            medio = (menor + mayor) / 2;
        }
        if (menor <= mayor) {
            // ENCONTRADO  
            JOptionPane.showMessageDialog(null,
                    "Alumno : " + alumnos[medio].getNombre()
                    + "\n\nPRO: " + alumnos[medio].getNotas()[0].getNota()
                    + "\nBD: " + alumnos[medio].getNotas()[1].getNota()
                    + "\nLM: " + alumnos[medio].getNotas()[2].getNota()
                    + "\n\nMedia : " + alumnos[medio].getMedia());
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
        for (i = 0; i < numAlumnos
                && alumnos[i].getSuspensos() > 0; i++);
        if (i < numAlumnos) {
            // He encontrado al menos un aprobado
            String listado = "<html><h1>Alumnos Aprobados</h1>";
            listado += "<table border=1><tr><th>Alumno</th><th>Nota Media</th></tr>";
            for (; i < numAlumnos; i++) {
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

    private static void nuevoAlumno() {
        if (numAlumnos == alumnos.length) {
            JOptionPane.showMessageDialog(
                    null,
                    "No queda espacio para nuevos alumnos",
                    "Error de espacio",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String nombre = JOptionPane.showInputDialog("Nombre");
            Alumno a = new Alumno(nombre);
            for (int i = 0; i < Nota.ASIGNATURAS.length; i++) {
                String resp = JOptionPane.showInputDialog("Nota de "
                        + Nota.ASIGNATURAS[i]);
                int nota = Integer.parseInt(resp);
                a.setNota(i + 1, nota);
            }
            // Inserción al FINAL
            /*alumnos[numAlumnos] = a;
            numAlumnos++;*/
            
            // Inserción ORDENADA
            // Buscar la posición de inserción 
            int i;
            for(i=0; i<numAlumnos &&
                a.getNombre().compareToIgnoreCase(
                alumnos[i].getNombre())>0;i++);
            // Hacemos hueco
            for(int j=numAlumnos-1; j>=i; j--){
                alumnos[j+1] = alumnos[j];
            }
            // Insertamos en la posición i
            alumnos[i] = a;
            numAlumnos++;
        }
    }

    private static void eliminarAlumno() {
        String nombre;
        nombre = JOptionPane.showInputDialog("Nombre Alumno");
        // Búsqueda ORDENADA
        int i;
        for(i=0; i<numAlumnos && 
            nombre.compareToIgnoreCase(alumnos[i].getNombre())>0;
            i++);
        if ( i<numAlumnos && 
            nombre.equalsIgnoreCase(alumnos[i].getNombre())){
            // Encontrado en la posición i
            if (JOptionPane.showConfirmDialog(
                null,
                "Nombre : " + alumnos[i].getNombre() +
                "\n\nEliminar Alumno",
                "Eliminar Alumno",
                JOptionPane.YES_NO_OPTION) 
                == JOptionPane.YES_OPTION){
                for(int j=i+1; j<numAlumnos; j++){
                    alumnos[j-1] = alumnos[j];
                }
                numAlumnos--;
            } 
        } else {
            JOptionPane.showMessageDialog(null, 
                "No hay ningún alumno con ese nombre",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
