/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author Iñigo
 */
public class Alumno implements Comparable<Alumno>{
    private String nombre;
    private Nota[] notas = new Nota[3];

    public Alumno() {
    }

    public Alumno(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nota[] getNotas() {
        return notas;
    }

    public void setNotas(Nota[] notas) {
        this.notas = notas;
    }
    
    public void setNota(int idNota, double nota){
        this.notas[idNota-1] = new Nota(idNota,nota);
    }
    
    public double getMedia(){
        double sumaNotas = 0;
        int i;
        for (i=0; i<this.notas.length; i++){
            sumaNotas += this.notas[i].getNota();
        }
        return sumaNotas / this.notas.length;
    }
    
    public int getSuspensos(){
        int numSuspensos = 0;
        for(int i=0; i<this.notas.length; i++){
            if (this.notas[i].getNota() < 5){
                numSuspensos++;
            }
        }
        return numSuspensos;
    }
    
    public static void main(String[] args) {
        Alumno alumno = new Alumno("Ana");
        /*Nota[] notas = new Nota[3];
        notas[0] = new Nota(0, 4);
        notas[1] = new Nota(1,7);
        notas[2] = new Nota(2,8);
        alumno.setNotas(notas);*/
        alumno.setNota(0, 4);
        alumno.setNota(1, 7);
        alumno.setNota(2, 8);
        JOptionPane.showMessageDialog(null,
            "Nombre : " + alumno.getNombre() +
            "\n" + alumno.getNotas()[0].getNombre() +
                " : " + alumno.getNotas()[0].getNota() + 
            "\n" + alumno.getNotas()[1].getNombre() +
                " : " + alumno.getNotas()[1].getNota() + 
            "\n" + alumno.getNotas()[2].getNombre() +
                " : " + alumno.getNotas()[2].getNota() +
            "\nNota Media : " + alumno.getMedia()
            );
    }

    @Override
    public int compareTo(Alumno o) {
        return this.getNombre().compareToIgnoreCase(o.getNombre());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alumno other = (Alumno) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", notas=" + notas + '}';
    }
    
    
}
