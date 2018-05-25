/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JOptionPane;
import modelo.Gasto;

/**
 *
 * @author Iñigo
 */
public class GestionGastos {

    private static Gasto[] gastos = new Gasto[20];
    private static int numGastos = 0;
    private static String[] comerciales = {
        "Juan", "Eva", "Ana", "Carlos"
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        crearDatosEjemplo();
        String resp;
        int opc;
        do {
            resp = JOptionPane.showInputDialog(
                    "1 Nuevo Gasto\n"
                    + "2 Eliminar Gasto\n"
                    + "3 Modificar Importe\n"
                    + "4 Listar Todos los Gastos\n"
                    + "5 Listar Gastos de un Comercial\n"
                    + "6 Salir\n"
                    + "Opción [1-6] :");
            opc = Integer.parseInt(resp);
            switch (opc) {
                case 1:
                    nuevoGasto();
                    break;
                case 2:
                    eliminarGasto();
                    break;
                case 3:
                    modificarImporte();
                    break;
                case 4:
                    listarGastos();
                    break;
                case 5:
                    gastosComercial();
                    break;
            }
        } while (opc != 6);

    }

    private static int buscarComercial(String nombre){
        int i;
        for(i=0; i<comerciales.length && 
            !nombre.equalsIgnoreCase(comerciales[i]); 
            i++);
        if (i == comerciales.length){
            return -1;
        } else {
            return i;
        }
    }
    
    private static void nuevoGasto() {
        String comercial = JOptionPane.showInputDialog("Nombre Comercial");
        int pos = buscarComercial(comercial);
        if (pos == -1){
            JOptionPane.showMessageDialog(null, 
                "No hay ningún comercial con ese nombre",
                "Error en búsqueda",
                JOptionPane.ERROR_MESSAGE);
        } else {
            String concepto = JOptionPane.showInputDialog("Concepto");
            String fecha = JOptionPane.showInputDialog("Fecha (dd/mm/aaaa)");
            String resp = JOptionPane.showInputDialog("Importe");
            double importe = Double.parseDouble(resp);
            Gasto g = new Gasto(concepto, fecha, importe, pos);
            int i;
            for(i=0; i<numGastos && 
                concepto.compareToIgnoreCase(gastos[i].getConcepto())>0;
                i++);
            // Hacemos hueco
            for(int j=numGastos-1; j>=i; j--){
                gastos[j+1] = gastos[j];
            }
            gastos[i] = g;
            numGastos++;
            JOptionPane.showMessageDialog(null, 
                "Gasto añadido correctamente");
        }
    }

    private static void crearDatosEjemplo() {
        gastos[0] = new Gasto("Concepto01", "06/01/18", 120.45, 0);
        gastos[1] = new Gasto("Concepto03", "06/01/18", 15.5, 0);
        gastos[2] = new Gasto("Concepto05", "06/01/18", 120, 1);
        gastos[3] = new Gasto("Concepto06", "06/01/18", 75.78, 0);
        gastos[4] = new Gasto("Concepto10", "07/01/18", 34, 1);
        gastos[5] = new Gasto("Concepto11", "07/01/18", 45.6, 1);
        gastos[6] = new Gasto("Concepto14", "07/01/18", 90.9, 2);
        gastos[7] = new Gasto("Concepto21", "08/01/18", 120, 0);
        gastos[8] = new Gasto("Concepto22", "08/01/18", 45.6, 2);
        gastos[9] = new Gasto("Concepto24", "08/01/18", 32.15, 1);
        numGastos = 10;
    }

    private static void eliminarGasto() {
        String concepto = JOptionPane.showInputDialog("Concepto");
        int menor = 0;
        int mayor = numGastos - 1;
        int medio = (menor + mayor) / 2;
        while (menor <= mayor && 
            !concepto.equalsIgnoreCase(
                    gastos[medio].getConcepto())){
            if (concepto.compareToIgnoreCase(
                    gastos[medio].getConcepto())>0){
                menor = medio + 1;
            } else {
                mayor = medio - 1;
            }
            medio = (menor + mayor) / 2;
        }
        if (menor <= mayor){
            if (JOptionPane.showConfirmDialog(null, 
                "Eliminar Gasto\n\n" +
                "Concepto : " + gastos[medio].getConcepto() +
                "\nFecha : " + gastos[medio].getFecha() +
                "\nImporte : " + gastos[medio].getImporte() +
                "\nComercial : " + 
                        comerciales[gastos[medio].getComercial()],
                "Eliminar Gasto",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION){
                // Eliminamos el gasto
                for(int j=medio+1; j<numGastos; j++){
                    gastos[j-1] = gastos[j];
                }
                numGastos--;
                JOptionPane.showMessageDialog(null, 
                    "Gasto Eliminado!");
            }
        } else {
            JOptionPane.showMessageDialog(null, 
                "No hay ningún gasto con ese concepto",
                "No Encontrado",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void modificarImporte() {
        String concepto = JOptionPane.showInputDialog("Concepto");
        int i;
        for(i=0; i<numGastos && 
            !concepto.equalsIgnoreCase(gastos[i].getConcepto());
            i++);
        if (i < numGastos){
            String resp = JOptionPane.showInputDialog(
                "Concepto : " + gastos[i].getConcepto() +
                "\nFecha : " + gastos[i].getFecha() +
                "\nImporte Actual : " + gastos[i].getImporte() +
                "\nComercial : " + 
                        comerciales[gastos[i].getComercial()] +
                "\n\nIntroduzca nuevo importe"
                );
            double importe = Double.parseDouble(resp);
            gastos[i].setImporte(importe);
            JOptionPane.showMessageDialog(null, 
                    "Importe Modificado!");
        } else {
            JOptionPane.showMessageDialog(null, 
                "No hay ningún gasto con ese concepto",
                "No Encontrado",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listarGastos() {
        double sumaImportes = 0;
        String listado = "<html><h1>Listado de Gastos</h1>";
        listado += "<table border=1><tr><th>Comercial</th>" +
                "<th>Fecha</th><th>Concepto</th>" +
                "<th>Importe</th></tr>";
        for(int i=0; i<numGastos; i++){
            listado += "<tr><td>" + comerciales[gastos[i].getComercial()] +
                    "</td><td>" + gastos[i].getFecha() + "</td><td>" +
                    gastos[i].getConcepto() + "</td><td>" +
                    gastos[i].getImporte() + "</td></tr>";
            sumaImportes += gastos[i].getImporte();
        }
        listado += "<tr><td colspan=3></td><td>" + sumaImportes + "</td></tr>";
        listado += "</table></html>";
        JOptionPane.showMessageDialog(null, 
            listado,
            "Informe de Gastos",
            JOptionPane.PLAIN_MESSAGE);
    }

    private static void gastosComercial() {
        String comercial = JOptionPane.showInputDialog("Comercial");
        int posComercial = buscarComercial(comercial);
        if (posComercial != -1){
            String listado = "<html><h1>Gastos de " + comercial + "</h1>";
            // Buscar el primer gasto del comercial
            int i;
            for(i=0; i<numGastos &&
                posComercial != gastos[i].getComercial();
                i++);
            if (i == numGastos){
                JOptionPane.showMessageDialog(null, 
                comercial + " NO tiene ningún gasto",
                "No hay gastos",
                JOptionPane.WARNING_MESSAGE);
            } else {
                listado += "<table border=1><tr><th>Fecha</th>" +
                    "<th>Concepto</th><th>Importe</th></tr>";
                double sumaImportes = 0;
                for(; i<numGastos; i++){
                    if (posComercial == gastos[i].getComercial()){
                        listado += "<tr><td>" + gastos[i].getConcepto() +
                            "</td><td>" + gastos[i].getFecha() +
                            "</td><td>" + gastos[i].getImporte() +
                            "</td></tr>";
                        sumaImportes += gastos[i].getImporte();
                    }
                }
                listado += "<tr><td colspan=2></td><td>" +
                        sumaImportes + "</td></tr></table></html>";
                JOptionPane.showMessageDialog(null, 
                    listado,
                    "Gastos de " + comercial,
                    JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, 
                "Comercial NO Encontrado",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    

}
