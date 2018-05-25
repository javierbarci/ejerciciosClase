/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.modelo;

/**
 *
 * @author Iñigo
 */
public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, double precio, int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    public void setIdProducto(int idProducto){
        this.idProducto = idProducto;
    }
    
    public int getIdProducto(){
        return this.idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    /**
     * Retorna si el producto está bajo stock
     * 
     * @return boolean
     */
    public boolean isBajoStock(){
//       if (this.stock <= 0){
//           return true;
//       } else {
//           return false;
//       }
        return (this.stock <= 0);
    }
    
    /**
     * Vende la cantidad de productos indicada y retorna
     * el importe de la venta, descontando dicha cantidad
     * del stock. En caso de que no haya suficiente stock
     * retorna -1
     * 
     * @param cant La cantidad a vender
     * @return -1 si no hay stock. El importe de la venta si lo hay
     */
    public double vender(int cant){
        if (cant > this.stock){
            return -1;
        } else {
            this.stock -= cant;
            return cant * this.precio;
        }
    }
    public static void main(String[] args) {
        
    }
}
