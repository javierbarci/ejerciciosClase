/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JOptionPane;
import modelo.FiguraGeometrica;

/**
 *
 * @author Iñigo
 */
public class Figuras {
    public static final int CIRCULO = 1;
    public static final int CUADRADO = 2;
    public static final int CUBO = 3;
    public static final int RECTANGULO = 4;
    public static final int TRIANGULO = 5;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String resp;
        int tipo;
        int base, altura;
        FiguraGeometrica figura;
        do {
            resp = JOptionPane.showInputDialog(
                "Tipo de Figura\n\n" +
                "1 - Círculo\n" +
                "2 - Cuadrado\n" +
                "3 - Cubo\n" +
                "4 - Rectángulo\n" +
                "5 - Triángulo\n\n" +
                "Opción [1-5]:");
            tipo = Integer.parseInt(resp);
        } while (JOptionPane.showConfirmDialog(
            null,
            "Repetir el proceso")
            == JOptionPane.YES_OPTION);
    }
    
}
