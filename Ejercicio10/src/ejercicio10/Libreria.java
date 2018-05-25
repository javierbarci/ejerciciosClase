/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio10;

import javax.swing.JOptionPane;
import modelo.Libro;

/**
 *
 * @author Iñigo
 */
public class Libreria {

    private static Libro[] libros = {
        new Libro("Aprenda C Ya", "Programación", 75.12, 5),
        new Libro("Microsoft Office", "Ofimática", 58.6, 12),
        new Libro("Windows 10", "Sistemas Operativos", 45, 8),
        new Libro("C Avanzado", "Programación", 90, 3),
        new Libro("Word Básico", "Ofimática", 64.6, 10),
        new Libro("Windows 2015 Server", "Sistemas Operativos", 52.3, 7),
        new Libro("Access 2015", "Ofimática", 32.45, 5),
        new Libro("Diseño de Algoritmos", "Programación", 90.15, 10),
        new Libro("Excel 2015", "Ofimática", 52.58, 4)
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String resp;
        int opc;
        do {
            resp = JOptionPane.showInputDialog(
                    "1. Listar Libros Alfabéticamente\n"
                    + "2. Mostrar Libros de un Tema\n"
                    + "3. Listar Libros por Temas\n"
                    + "4. Listar Libros por Precio\n"
                    + "5. Salir\n\n"
                    + "Opción [1-5]");
            opc = Integer.parseInt(resp);
            switch (opc) {
                case 1:
                    listarLibros();
                    break;
                case 2:
                    mostrarLibrosTema();
                    break;
                case 3:
                    listarPorTemas();
                    break;
                case 4:
                    listarPorPrecio();
                    break;
            }
        } while (opc != 5);

    }

    private static void listarLibros() {
        // Ordenación por Inserción
        for (int i = 0; i < libros.length - 1; i++) {
            for (int j = i + 1; j < libros.length; j++) {
                if (libros[i].getTitulo().
                        compareToIgnoreCase(libros[j].getTitulo()) > 0) {
                    // intercambiamos
                    Libro temp = libros[i];
                    libros[i] = libros[j];
                    libros[j] = temp;
                }
            }
        }
        String listado = "<html><h1>Listado de Libros</h1>";
        double sumaPrecios = 0;
        int sumaUnidades = 0;
        listado += "<table border=1><tr><th>Título</th>"
                + "<th>Tema</th><th>Unidades</th><th>Precio</th></tr>";
        for (int i = 0; i < libros.length; i++) {
            listado += "<tr><td>" + libros[i].getTitulo()
                    + "</td><td>" + libros[i].getTema()
                    + "</td><td>" + libros[i].getUnidades()
                    + "</td><td>" + libros[i].getPrecio() + "</td></tr>";
            sumaUnidades += libros[i].getUnidades();
            sumaPrecios += libros[i].getPrecio();
        }
        listado += "<tr><td colspan=2></td><td>" + sumaUnidades
                + "</td><td>" + sumaPrecios + "</td></tr></table></html>";
        JOptionPane.showMessageDialog(
                null,
                listado,
                "Listado de Libros",
                JOptionPane.PLAIN_MESSAGE);
    }

    private static void mostrarLibrosTema() {
        for (int i = 0; i < libros.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < libros.length; j++) {
                if (libros[j].getTema()
                    .compareToIgnoreCase(libros[menor].getTema())
                        < 0
                    || (libros[j].getTema().equalsIgnoreCase(libros[menor].getTema())
                       && libros[j].getTitulo().compareToIgnoreCase(libros[menor].getTitulo())<0)    
                       ) {
                    menor = j;
                }
            }
            // En menor está la posición del MENOR elemento
            // DESDE i HASTA el final de la matriz
            // Ese elemento DEBERÁ IR EN LA POSICIÓN i
            // Si no está ya en la posición i lo intercambiamos
            if (menor != i) {
                Libro temp = libros[menor];
                libros[menor] = libros[i];
                libros[i] = temp;
            }
        }
        // La matriz está ordenada en base al tema
        String tema = JOptionPane.showInputDialog("Tema a listar");
        // Buscamos si AL MENOS hay un libro de ese tema
        // Búsqueda ORDENADA en ASCENDENTE
        int i;
        for (i = 0; i < libros.length
                && tema.compareToIgnoreCase(libros[i].getTema()) > 0;
                i++);
        if (i < libros.length
                && tema.equalsIgnoreCase(libros[i].getTema())) {
            // Comenzar el listado
            int contador = 0;
            String listado = "<html><body><table border=1>"
                    + "<h2>Tema : " + libros[i].getTema() + "</h2>"
                    + "<tr><th>Título</th><th>Precio</th><th>Unidades</th></tr>";
            // Listado ORDENADO
            for (; i < libros.length
                    && tema.equalsIgnoreCase(libros[i].getTema()); i++) {
                listado += "<tr><td>" + libros[i].getTitulo()
                        + "</td><td>" + libros[i].getPrecio()
                        + "</td><td>" + libros[i].getUnidades() + "</td></tr>";
                contador++;
            }
            listado += "</table><h4>Se han encontrado "
                    + contador + " libros del tema.</body></html>";

            /*
            // Listado DESORDENADO
            for (; i < libros.length; i++) {
                if (tema.equalsIgnoreCase(libros[i].getTema())) {
                    listado += "<tr><td>" + libros[i].getTitulo()
                            + "</td><td>" + libros[i].getPrecio()
                            + "</td><td>" + libros[i].getUnidades() + "</td></tr>";
                    contador++;
                }
            }
            listado += "</table><h4>Se han encontrado "
                    + contador + " libros del tema.</body></html>";
            */
            JOptionPane.showMessageDialog(null,
                    listado,
                    "Libros de " + tema,
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No hay ningún libro de ese tema",
                    "Tema no encontrado",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void listarPorTemas() {
        for(int i=0; i<libros.length-1; i++){
            for(int j=libros.length -2; j>=i; j--){
                if (libros[j].getTema().compareToIgnoreCase(
                    libros[j+1].getTema())>0){
                    Libro temp = libros[j];
                    libros[j] = libros[j+1];
                    libros[j+1] = temp;
                }
            }
        }
        // Principio del proceso
        String listado = "<html><body><h1>Listado por Temas</h1>";
        int numTemas = 0;
        int numLibros = 0;
        int pos=0;
        while (pos<libros.length){
            // Empiezo un nuevo tema
            String tema = libros[pos].getTema();
            numTemas++;
            listado += "<h2>" + tema + "</h2>";
            listado += "<table border=1><tr><th>Título</th>" +
                    "<th>Unidades</th><th>Precio</th></tr>";
            int numLibrosTema = 0;
            while (pos<libros.length && 
                libros[pos].getTema().equalsIgnoreCase(tema)){
                // Proceso el libro
                listado += "<tr><td>" + libros[pos].getTitulo() +
                    "</td><td>" + libros[pos].getUnidades() +
                    "</td><td>" + libros[pos].getPrecio() + "</td></tr>";
                //numLibros++;
                numLibrosTema++;
                // Pasamos al siguiente libro
                pos++;
            }
            // Fin del tema
            numLibros += numLibrosTema;
            listado += "</table><h3>Hay " + numLibrosTema + " libros de " +
               tema + "</h3><hr>";
        }
        // Fin Proceso
        listado += "<h2>Hay " + numLibros + " libros de " +
                numTemas + " temas</h2>";
        listado += "</body></html>";
        JOptionPane.showMessageDialog(null, listado,
                "Listado por Temas",
                JOptionPane.PLAIN_MESSAGE);
    }

    private static void listarPorPrecio() {
        for(int i=0; i<libros.length-1; i++){
            int mayor = i;
            for (int j = i+1; j < libros.length; j++) {
                if (libros[j].getPrecio()>libros[mayor].getPrecio()){
                    mayor = j;
                }
            }
            if (i != mayor){
                Libro temp = libros[i];
                libros[i] = libros[mayor];
                libros[mayor] = temp;
            }
        }
        String resp = JOptionPane.showInputDialog("Precio Máximo");
        double maximo = Double.parseDouble(resp);
        int i;
        for(i=0; i<libros.length && 
                libros[i].getPrecio()>maximo; i++);
        if (i == libros.length){
            JOptionPane.showMessageDialog(null, 
                "No hay ningún libro con precio inferior a " + maximo,
                "No hay libros",
                JOptionPane.WARNING_MESSAGE);
        } else {
            String listado = "<html><body><h1>Libros con precio máximo de " + maximo + "</h1>";
            listado += "<table border=1><tr><th>Título</th><th>Precio</th><th>Tema</th><th>Unidades</th></tr>";
            int num = 0;
            for(; i<libros.length; i++){
                listado +="<tr><td>" + libros[i].getTitulo() +
                        "</td><td>" + libros[i].getPrecio() +
                    "</td><td>" + libros[i].getTema() +
                    "</td><td>" + libros[i].getUnidades() +
                    "</td></tr>";
                num++;
            }
            listado += "</table><h2>Hay " + num + " libros con precio inferior a " +
                    maximo + "</h2></body></html>";
            JOptionPane.showMessageDialog(null, listado,
                "Listado por Precio",
                JOptionPane.PLAIN_MESSAGE);
        }
    }

}
