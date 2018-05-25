/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.zabalburu.zabalevent.modelo.Lugar;
import net.zabalburu.zabalevent.util.Conexion;

/**
 *
 * @author Iñigo
 */
public class LugarBBDD implements LugarDAO{

    @Override
    public List<Lugar> getLugares() {
        List<Lugar> lugares = new ArrayList<>();
        ResultSet rst;
        try {
            rst = Conexion.getConexion()
                    .createStatement()
                    .executeQuery("Select * From Lugares");
            while(rst.next()){
                Lugar l = leerLugar(rst);
                lugares.add(l);
            }
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lugares;
    }

    @Override
    public Lugar getLugar(int idLugar) {
        Lugar l = null;
        PreparedStatement pst;
        ResultSet rst;
        
        try {
            pst = Conexion.getConexion()
                    .prepareStatement(
                        "Select * From Lugares " +
                        "where idLugar=?");
            pst.setInt(1, idLugar);
            rst = pst.executeQuery();
            if (rst.next()){
                l = leerLugar(rst);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LugarBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return l;
    }

    @Override
    public void nuevoLugar(Lugar l) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement(
                    "Insert into Lugares(idLugar,nombre,direccion,localidad,foto) " +
                    "values(?,?,?,?,?)");
            pst.setInt(1, l.getIdLugar());
            pst.setString(2, l.getNombre());
            pst.setString(3, l.getDireccion());
            pst.setString(4, l.getLocalidad());
            pst.setString(5, l.getFoto());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarLugar(Lugar l) {
        try {
            PreparedStatement pst;
            pst = Conexion.getConexion()
                    .prepareStatement(
                    "Delete From Lugares where idLugar=?");
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificarLugar(Lugar l) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement(
                    "Update Lugares Set " +
                    "nombre=?, direcccion=?" +
                    "localidad=?, foto=? " +
                    "where idLugar=?");
            pst.setString(1, l.getNombre());
            pst.setString(2, l.getDireccion());
            pst.setString(3, l.getLocalidad());
            pst.setString(4, l.getFoto());
            pst.setInt(5, l.getIdLugar());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(LugarBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
        Con los datos del ResultSet ya leidos
        convertimos la información de la tabla
        en un objeto Lugar
    */
    private Lugar leerLugar(ResultSet rst){
        Lugar l = new Lugar();
        try {
            l.setIdLugar(rst.getInt("idLugar"));
            l.setNombre(rst.getString("nombre"));
            l.setDireccion(rst.getString("direccion"));
            l.setLocalidad(rst.getString("localidad"));
            l.setFoto(rst.getString("foto"));
        } catch (SQLException ex) {
            Logger.getLogger(LugarBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public static void main(String[] args) {
        LugarDAO lugarDAO = new LugarBBDD();
        for(Lugar l : lugarDAO.getLugares()){
            System.out.println(l);
        }
        Lugar l = new Lugar(0, 
                "Teatro Esukalduna", "Abandoibarra Etorb., 4, 48011", 
                "Bilbao", "euskalduna.jpg");
        lugarDAO.nuevoLugar(l);
    }
    
}
