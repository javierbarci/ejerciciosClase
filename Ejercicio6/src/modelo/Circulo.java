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
public class Circulo extends FiguraGeometrica{

    public Circulo(int radio){
        super(radio);
    }
    
    @Override
    public double getArea() {
        return Math.pow(this.getValor1(), 2)*Math.PI;
    }

    @Override
    public double getPerimetro() {
        return 2*Math.PI*this.getValor1();
    }
    
}
