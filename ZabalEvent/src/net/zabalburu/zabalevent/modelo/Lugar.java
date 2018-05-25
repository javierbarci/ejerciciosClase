/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.modelo;

import java.io.Serializable;

/**
 *
 * @author Iñigo
 */
public class Lugar implements Serializable{
    private int idLugar;
    private String nombre;
    private String direccion;
    private String localidad;
    private String foto;

    public Lugar() {
    }

    public Lugar(int idLugar, String nombre, String direccion, String localidad, String foto) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.foto = foto;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
        final Lugar other = (Lugar) obj;
        if (this.idLugar != other.idLugar) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}
