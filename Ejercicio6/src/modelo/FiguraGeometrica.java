/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Iñigo
 */
public abstract class FiguraGeometrica {
    private int valor1;

    public FiguraGeometrica() {
    }

    public FiguraGeometrica(int valor1) {
        this.valor1 = valor1;
    }

    public int getValor1() {
        return valor1;
    }

    public void setValor1(int valor1) {
        this.valor1 = valor1;
    }
    
    public abstract double getArea();
    
    public abstract double getPerimetro();
}
