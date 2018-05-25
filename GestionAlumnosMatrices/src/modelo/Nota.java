/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.JOptionPane;

/**
 *
 * @author Iñigo
 */
public class Nota {
    public static final String[] ASIGNATURAS = {
      "PRO","BD","LM"  
    };
    
    private int idAsignatura;
    
    private double nota;

    public Nota(){
        
    }

    public Nota(int idAsignatura, double nota) {
        this.idAsignatura = idAsignatura;
        this.nota = nota;
    }
    
    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    public String getNombre(){
        return Nota.ASIGNATURAS[this.idAsignatura];
    }
    
    
    public static void main(String[] args) {
        Nota n1 = new Nota(2,7);
        
        /*JOptionPane.showMessageDialog(null,
            "Su nota en " + 
             Nota.ASIGNATURAS[n1.getIdAsignatura()]
             + " es " + n1.getNota());*/
        JOptionPane.showMessageDialog(null,
            "Su nota en " + 
             n1.getNombre()
             + " es " + n1.getNota());
    }
}
