/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.app;

import javax.swing.JOptionPane;
import net.zabalburu.modelo.Producto;

/**
 *
 * @author Iñigo
 */
public class App {

    public static void main(String[] args) {
        Producto p1 = new Producto(1, "Producto 1", 12.5, 10);
        Producto p2 = new Producto(2, "Producto 2", 25.75, 5);
        Producto p3 = new Producto(3, "Producto 3", 55.0, 7);
        String resp;
        String nombreProducto;
        int cantidad;
        double importe;
        double total = 0;
        System.out.println("Introduzca los datos del pedido\n"
                + "===============================");
        do {
            nombreProducto = JOptionPane.showInputDialog("Nombre Producto");
            Producto seleccionado;
            if (nombreProducto.equalsIgnoreCase(p1.getNombre())) {
                seleccionado = p1;
            } else if (nombreProducto.equalsIgnoreCase(p2.getNombre())) {
                seleccionado = p2;
            } else {
                seleccionado = p3;
            }
            System.out.println("Nombre : " + seleccionado.getNombre()
                    + " (id:" + seleccionado.getIdProducto() + ")");
            System.out.println("Precio : " + seleccionado.getPrecio());
            System.out.println("Stock : " + seleccionado.getStock());
            resp = JOptionPane.showInputDialog("Cantidad Comprada");
            cantidad = Integer.parseInt(resp); // Convierte la cadena en un entero
            /*if (cantidad > seleccionado.getStock()){
                System.out.println("No hay suficiente stock de ese producto");
            } else {
                
            }*/
            importe = seleccionado.vender(cantidad);
            if (importe == -1){
                System.out.println("No hay suficiente stock de ese producto");
            } else {
                System.out.println(cantidad + " unidades a " +
                    seleccionado.getPrecio() + "€ : " +
                    importe + "€");
                total += importe;
            }
            resp = JOptionPane.showInputDialog("Pedir otro producto (S/N)");
        } while (resp.equalsIgnoreCase("s"));
        System.out.println("\nTotal de la Compra : " + total + "€");

    }
}
