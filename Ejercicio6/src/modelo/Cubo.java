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
public class Cubo extends Cuadrado{
    
    public Cubo(int base) {
        super(base);
    }

    @Override
    public double getPerimetro() {
        return this.getValor1()*12; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getArea() {
        return super.getArea()*6; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
