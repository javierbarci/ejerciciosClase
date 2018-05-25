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
public class Cuadrado extends FiguraGeometrica{

    public Cuadrado(int base){
        super(base);
    }
    
    @Override
    public double getArea() {
        return Math.pow(this.getValor1(),2);
    }

    @Override
    public double getPerimetro() {
        return 4*this.getValor1();
    }
    
}
