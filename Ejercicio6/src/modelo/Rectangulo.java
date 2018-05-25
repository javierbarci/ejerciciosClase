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
public class Rectangulo extends FiguraGeometrica{
    private int valor2;

    public Rectangulo(int base, int altura){
        super(base);
        this.valor2 = altura;
    }
    
    public int getValor2() {
        return valor2;
    }

    public void setValor2(int valor2) {
        this.valor2 = valor2;
    }
    
    @Override
    public double getArea() {
        return this.getValor1() * this.valor2;
    }

    @Override
    public double getPerimetro() {
        return (this.getValor1() +this.valor2)*2;
    }
    
}
