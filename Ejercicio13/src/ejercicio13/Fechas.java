/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio13;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Iñigo
 */
public class Fechas {
    private static String mostrar(GregorianCalendar gc){
        String diaSem;
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SATURDAY:
                diaSem = "Sábado";
                break;
            case Calendar.SUNDAY:
                diaSem = "Domingo";
                break;
            case Calendar.MONDAY:
                diaSem = "Lunes";
                break;
            case Calendar.TUESDAY:
                diaSem = "Martes";
                break;
            case Calendar.WEDNESDAY:
                diaSem = "Miércoles";
                break;
            case Calendar.THURSDAY:
                diaSem = "Jueves";
                break;
             default:
                 diaSem = "Viernes";
                 break;
        }
        String cadena = gc.get(Calendar.DATE) +
                "-" + (gc.get(Calendar.MONTH) + 1) +
                "-" + gc.get(Calendar.YEAR) +
                " (" + diaSem + ")";
        return cadena;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String resp = JOptionPane.showInputDialog("Fecha (dd/mm/aaaa):");
        if (resp == null){
            return ;
        }
        int pos1 = resp.indexOf("/");
        int pos2 = resp.lastIndexOf("/");
        // int pos2 = resp.indexOf("/",pos1+1)
        if (pos1 == -1 || pos1 == pos2){
            JOptionPane.showMessageDialog(null, 
                "Formato de fecha erróneo. Debe ser dd/mm/aaaa");
            return;
        } 
        int dia, mes, año;
        try {
            dia = Integer.parseInt(resp.substring(0, pos1));
            mes = Integer.parseInt(resp.substring(pos1+1, pos2));
            año = Integer.parseInt(resp.substring(pos2+1));
        } catch (NumberFormatException ex){
            // Se ejecuta SI HAY ERRORES
            JOptionPane.showMessageDialog(null, 
               "El día, mes y año deben ser valores enteros");
            return;
       } finally{
            // Se ejecuta haya errores o no
        }
        GregorianCalendar gc = new GregorianCalendar(año, mes-1, dia);
        JOptionPane.showMessageDialog(null,mostrar(gc));
        gc.add(Calendar.DATE, 30);
        if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            gc.add(Calendar.DATE, 2);
        } else if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            gc.add(Calendar.DATE, 1);
        } 
        JOptionPane.showMessageDialog(null,mostrar(gc));
        if (gc.get(Calendar.DAY_OF_MONTH)>15){
            gc.add(Calendar.MONTH, 1);
        }
        gc.set(Calendar.DATE, 15);
        JOptionPane.showMessageDialog(null,mostrar(gc));
        int t = gc.get(Calendar.MONTH) / 3 + 1;
        boolean laborable = gc.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY
                && gc.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY;
        int quedan = gc.getActualMaximum(Calendar.DAY_OF_YEAR) - gc.get(Calendar.DAY_OF_YEAR);
        JOptionPane.showMessageDialog(null,
            "Trimestre : " + t + 
            "\nLaborable : " + ((laborable)?"Sí":"No") +
            "\nHan pasado " + gc.get(Calendar.DAY_OF_YEAR) + " días este año" +
            "\nQuedan " + quedan + " días hasta fin de año"
        );
        while (gc.get(Calendar.DAY_OF_WEEK)!= Calendar.MONDAY){
            gc.add(Calendar.DATE,1);
        }
        JOptionPane.showMessageDialog(null,mostrar(gc));
        
        GregorianCalendar gcHoy = new GregorianCalendar();
        GregorianCalendar gcCumple = new GregorianCalendar(
            gcHoy.get(Calendar.YEAR),
            Calendar.JANUARY,
            4);
        if (gcHoy.after(gcCumple)){
            gcCumple.add(Calendar.YEAR, 1);
        }
        JOptionPane.showMessageDialog(null,"Cumple : " + mostrar(gcCumple));
        int dias = 0;
        if (gcHoy.before(gc)){
            while (gcHoy.before(gc)){
                dias++;
                gcHoy.add(Calendar.DATE, 1);
            }
            JOptionPane.showMessageDialog(null,"Faltan " + dias + 
                " días hasta " + mostrar(gc));
        } else {
            while (gc.before(gcHoy)){
                dias++;
                gc.add(Calendar.DATE, 1);
            }
            JOptionPane.showMessageDialog(null,"Han pasado " + dias + 
                " días");
        }
    }
    
}
