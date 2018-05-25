/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nominas.app;

import javax.swing.JOptionPane;
import nominas.modelo.Empleado;

/**
 *
 * @author Iñigo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 1 por programa
        String resp;
        Empleado e;
        int tipo, hijos;
        String nombre;
        double plus;
        int numEmpleados = 0;
        int numMas2000 = 0;
        double sumaSueldos = 0;
        do {
            // 1 por Empleado
            numEmpleados++;
            resp = JOptionPane.showInputDialog(
                    "Tipo Empleado :\n"
                    + "1. Operario\n"
                    + "2. Obrero Especialista\n"
                    + "3. Administrativo\n"
                    + "4. Licenciado\n\n"
                    + "Opción [1-4]");
            tipo = Integer.parseInt(resp);
            nombre = JOptionPane.showInputDialog("Nombre Empleado");
            resp = JOptionPane.showInputDialog("Nº Hijos");
            hijos = Integer.parseInt(resp);
            e = new Empleado(nombre, tipo, hijos);
            resp = JOptionPane.showInputDialog("Plus (0 para finalizar)");
            plus = Double.parseDouble(resp);
            while (plus != 0) {
                // 1 vez por cada plus de cada empleado
                e.añadirPlus(plus);
                resp = JOptionPane.showInputDialog("Plus (0 para finalizar)");
                plus = Double.parseDouble(resp);
            }
            // 1 por empleado
            System.out.println("Nómina del Empleado");
            System.out.println("===================");        
            System.out.println("Nombre : " + e.getNombre());
            System.out.println("Tipo Empleado : " + e.getDescripcion());
            System.out.println("Nº Hijos : " + e.getHijos());
            System.out.println("Sueldo Base : " + e.getSueldoBase() + "€");
            System.out.println("Pluses : " + e.getPluses());
            System.out.println("Sueldo Bruto : " + e.getSueldoBruto() + "€");
            System.out.println("IRPF : " + e.getPorcIrpf() + "%\t" +
                    "Importe IRPF : " + e.getIrpf() + "€");
            System.out.println("Sueldo Neto : " + e.getSueldoNeto() + "€");
            System.out.println("-----------------------------\n");
            sumaSueldos += e.getSueldoNeto();
            if (e.getSueldoNeto() > 2000){
                numMas2000++;
            }
        } while (JOptionPane.showConfirmDialog(null,
                "Repetir el proceso") == JOptionPane.YES_OPTION);
        // 1 vez por programa
        System.out.println("Número de empleados procesados : " +
                numEmpleados);
        System.out.println("Hay " + numMas2000 + " empleados que ganan más de 2000€");
        double sueldoMedio = sumaSueldos / numEmpleados;
        System.out.println("El sueldo neto medio es de " +
                sueldoMedio + "€");
        
    }

}
