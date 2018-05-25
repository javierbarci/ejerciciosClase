/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio8;

import javax.swing.JOptionPane;
import modelo.DVD;

/**
 *
 * @author Iñigo
 */
public class GestionDVD {
    private static DVD[] dvds = new DVD[30];
    private static int numDvds = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        dvds[0] = new DVD("dvd01","artista01","genero03");
        dvds[1] = new DVD("dvd03","artista03","genero02");
        dvds[2] = new DVD("dvd05","artista02","genero01");
        dvds[3] = new DVD("dvd07","artista01","genero01");
        dvds[4] = new DVD("dvd10","artista01","genero03");
        numDvds = 5;
        String resp;
        int opc;
        do {
            resp = JOptionPane.showInputDialog(
                "1. Nuevo DVD\n" +
                "2. Eliminar DVD\n" +
                "3. Buscar DVD\n" +
                "4. Listar por Género\n" +
                "5. Resumen\n" +
                "6. Salir\n\n" +
                "Opción [1-6]");
            opc = Integer.parseInt(resp);
            switch (opc) {
                case 1:
                    nuevoDVD();
                    break;
                case 2:
                    eliminarDVD();
                    break;
                case 3:
                    buscarDVD();
                    break;
                case 4:
                    listarPorGenero();
                    break;
                case 5:
                    resumen();
                    break;
            }
        } while (opc != 6);
        
    }

    private static void nuevoDVD() {
        String titulo, artista, genero;
        titulo = JOptionPane.showInputDialog("Título");
        // Búsqueda ORDENADA ASCENDENTE
        int i;
        for(i=0; i<numDvds && 
            titulo.compareToIgnoreCase(dvds[i].getTitulo())>0;
            i++);
        // Comprobar SI EXISTE
        if (i < numDvds &&
                titulo.equalsIgnoreCase(dvds[i].getTitulo())){
            JOptionPane.showMessageDialog(null, 
                "Ya hay un DVD con ese título\n\n" +
                "Artista : " + dvds[i].getArtista() +
                "\nGénero : " + dvds[i].getGenero(),
                "Ya existe",
                JOptionPane.WARNING_MESSAGE);
        } else {
            // Creamos el DVD
            artista = JOptionPane.showInputDialog("Artista");
            genero = JOptionPane.showInputDialog("Género");
            DVD d = new DVD(titulo,artista, genero);
            // Hacemos hueco para el DVD
            for (int j=numDvds - 1; j>=i; j--){
                dvds[j+1] = dvds[j];
            }
            // Ponemos el DVD en su posición
            dvds[i] = d;
            numDvds++;
        }
    }

    private static void buscarDVD() {
        String titulo;
        int menor, mayor, medio;
        do {
            titulo = JOptionPane.showInputDialog("Título");
            // Búsqueda BINARIA
            menor = 0;
            mayor = numDvds - 1;
            medio = (menor + mayor) / 2;
            while (menor <= mayor && 
                !titulo.equalsIgnoreCase(
                    dvds[medio].getTitulo())){
                if (titulo.compareToIgnoreCase(
                    dvds[medio].getTitulo())>0){
                    // Buscar en la parte superior
                    menor = medio + 1;
                } else {
                    mayor = medio - 1;
                }
                medio = (menor + mayor) / 2;
            }
            if (menor <= mayor){
                // Encontrado en el punto MEDIO
                JOptionPane.showMessageDialog(null, 
                    "Título : " + dvds[medio].getTitulo() +
                    "\nArtista : " + dvds[medio].getArtista() +
                    "\nGénero : " + dvds[medio].getGenero());
            } else {
                // Error
                JOptionPane.showMessageDialog(null, 
                    "No hay ningún DVD con ese título",
                    "No Encontrado",
                    JOptionPane.ERROR_MESSAGE);
            }
        } while (JOptionPane.showConfirmDialog(
            null,
            "Buscar otro DVD",
            "Pregunta",
            JOptionPane.YES_NO_OPTION) 
            == JOptionPane.YES_OPTION);
        
    }

    private static void listarPorGenero() {
        String genero;
        int cuenta;
        int i;
        genero = JOptionPane.showInputDialog("Género a listar");
        // Buscamos si hay AL MENOS 1 DVD de ese género
        // Búsqueda DESORDENADA
        for(i=0; i<numDvds && 
            !genero.equalsIgnoreCase(dvds[i].getGenero());
            i++);
        if (i<numDvds){
            // Está en la posición i
            cuenta = 0;
            String listado = "<html><h1>Género : " + genero + "</h1>";
            listado += "<table border=1><tr><th>Título</th>" +
                    "<th>Artista</th></tr>";
            // Buscamos TODOS los DVDS del género DESDE LA POSICIÓN ACTUAL
            for ( ; i<numDvds; i++ ){
                if (dvds[i].getGenero().equalsIgnoreCase(genero)){
                    cuenta++;
                    listado += "<tr><td>" + dvds[i].getTitulo() +
                        "</td><td>" + dvds[i].getArtista() + "</td></tr>";
                }
            }
            listado += "</table><h2>Hay " + cuenta + " dvds de " + genero +
                    "</h2></html>";
            JOptionPane.showMessageDialog(null, 
                    listado,
                    "DVDs de " + genero,
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            // No está
            JOptionPane.showMessageDialog(
                null,
                "No hay ningún DVD de ese género",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void resumen() {
        String[] generos = new String[20];
        int[] cuenta = new int[20];
        int numGeneros = 0;
        // Recorremos todos los DVDS
        for(int i=0; i<numDvds; i++){
            // Buscamos si su género está ya en la matriz de géneros
            int j;
            for(j=0; j<numGeneros && 
                !dvds[i].getGenero().equals(generos[j]);
                j++);
            if (j < numGeneros){
                // Ya existe el género
                // Incrementamos su cuenta de DVDS
                cuenta[j]++;
            } else {
                // No existe el género
                // Lo añadimos al final de la matriz de géneros
                generos[numGeneros] = dvds[i].getGenero();
                // Tenemos 1 DVD de ese género
                cuenta[numGeneros] = 1;
                // Hemos añadido un género
                numGeneros++;
            }
        }
        // Listamos las dos matrices
        String listado = "<html><h1>Resúmen por Género</h1>";
        listado += "<table border=1><tr><th>Género</th><th>Cuenta</th></tr>";
        for(int i=0; i<numGeneros; i++){
            listado += "<tr><td>" + generos[i] +
                    "</td><td>" + cuenta[i] + "</td></tr>";
        }
        JOptionPane.showMessageDialog(
            null,
            listado,
            "Resumen",
            JOptionPane.PLAIN_MESSAGE);
    }

    private static void eliminarDVD() {
        String titulo = JOptionPane.showInputDialog("Título");
        int menor = 0;
        int mayor = numDvds - 1;
        int medio = (menor + mayor) / 2;
        while (menor <= mayor && 
            !titulo.equalsIgnoreCase(dvds[medio].getTitulo())){
            if (titulo.compareToIgnoreCase(dvds[medio].getTitulo())>0){
                menor = medio + 1;
            } else {
                mayor = medio - 1;
            }
            medio = (menor + mayor) / 2;
        }
        if (menor <= mayor){
            if (JOptionPane.showConfirmDialog(
                null,
                "Eliminar DVD\n\n" +
                "Título : " + dvds[medio].getTitulo() +
                "\nArtista : " + dvds[medio].getArtista() +
                "\nGénero : " + dvds[medio].getGenero(),
                "Eliminar DVD",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION){
                for (int i=medio+1; i<numDvds; i++){
                    dvds[i-1] = dvds[i];
                }
                numDvds--;
                JOptionPane.showMessageDialog(null, 
                    "DVD Eliminado");
            }
        } else {
            JOptionPane.showMessageDialog(null,
                "No hay ningún DVD con ese título",
                "No encontrado",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
