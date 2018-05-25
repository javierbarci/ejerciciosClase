/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nominas.modelo;

/**
 *
 * @author Iñigo
 */
public class Empleado {
    private String nombre;
    private int tipo;
    private int hijos;
    private double pluses;
    
    public static final int OPERARIO = 1;
    public static final int OBRERO_ESPECIALISTA = 2;
    public static final int ADMINISTRATIVO = 3;
    public static final int LICENCIADO = 4;

    public Empleado() {
    }

    public Empleado(String nombre, int tipo, int hijos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.hijos = hijos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getHijos() {
        return hijos;
    }

    public void setHijos(int hijos) {
        this.hijos = hijos;
    }
    
    public double getSueldoBase(){
        double sueldoBase;
        switch (this.tipo) {
            case Empleado.OBRERO_ESPECIALISTA: // 2
                sueldoBase = 1450;
                break;
            case Empleado.ADMINISTRATIVO: // 3
                sueldoBase = 1300;
                break;
            case Empleado.LICENCIADO: // 4
                sueldoBase = 1450;
                break;
            default:
                sueldoBase = 1200; // Cualquier otro
                break;
        }
        return sueldoBase;
    }
    
    public String getDescripcion(){
        String descripcion;
        switch (this.tipo) {
            case Empleado.OBRERO_ESPECIALISTA: // 2
                descripcion = "Obrero Especialista";
                break;
            case Empleado.ADMINISTRATIVO: // 3
                descripcion = "Administrativo";
                break;
            case Empleado.LICENCIADO: // 4
                descripcion = "Licenciado";
                break;
            default:
                descripcion = "Operario"; // Cualquier otro
                break;
        }
        return descripcion;
    }
    
    public void limpiarPluses(){
        this.pluses = 0;
    }
    
    public void añadirPlus(double cantidad){
        this.pluses += cantidad;
    }

    public double getPluses() {
        return pluses;
    }
    
    public double getSueldoBruto(){
        return this.getSueldoBase() + this.getPluses();
    }
    
    public double getPorcIrpf(){
        double porcIrpf;
        switch (this.hijos) {
            case 0:
                if (this.getSueldoBruto()<1350){
                    porcIrpf = 13;
                } else if (this.getSueldoBruto()<1450){
                    porcIrpf = 14;
                } else {
                    porcIrpf = 17;
                }
                break;
            case 1:
                if (this.getSueldoBruto()<1350){
                    porcIrpf = 12.5;
                } else if (this.getSueldoBruto()<1450){
                    porcIrpf = 13;
                } else {
                    porcIrpf = 16;
                }
                break;
            case 2:
                if (this.getSueldoBruto()<1350){
                    porcIrpf = 11;
                } else if (this.getSueldoBruto()<1450){
                    porcIrpf = 12;
                } else {
                    porcIrpf = 15;
                }
                break;
            default:
                if (this.getSueldoBruto()<1350){
                    porcIrpf = 10;
                } else if (this.getSueldoBruto()<1450){
                    porcIrpf = 11.5;
                } else {
                    porcIrpf = 14.5;
                }
                break;
        }
        return porcIrpf;
    }
    
    public double getIrpf(){
        return this.getSueldoBruto() * this.getPorcIrpf() / 100;
    }
    
    public double getSueldoNeto(){
        return this.getSueldoBruto() - this.getIrpf();
    }
    
}
