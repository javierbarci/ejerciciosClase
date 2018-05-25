/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.zabalburu.zabalevent.modelo.Lugar;

/**
 *
 * @author Iñigo
 */
public class LugarFile implements LugarDAO{
    private File fichero = new File("datos/lugares.data");
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    @Override
    public List<Lugar> getLugares() {
        List<Lugar> lugares = new ArrayList<>();
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                    new FileInputStream(fichero)));
            while(true){
                Lugar l = (Lugar) ois.readObject();
                lugares.add(l);
            }
        } catch (EOFException ex){
            try {
                ois.close();
            } catch (IOException ex1) {                
            }
        } catch (IOException ex) {
            Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lugares;
    }

    @Override
    public Lugar getLugar(int idLugar) {
        Lugar lugar = null;
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                    new FileInputStream(fichero)));
            Lugar l;
            do{
                l = (Lugar) ois.readObject();
            } while (l.getIdLugar() != idLugar);
            lugar = l;
            ois.close();
        } catch (EOFException ex){
            try {
                ois.close();
            } catch (IOException ex1) {                
            }
        } catch (IOException ex) {
            Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lugar;
    }

    @Override
    public void nuevoLugar(Lugar l) {
        try {
            oos = new ObjectOutputStream(
                new BufferedOutputStream(
                new FileOutputStream(fichero,true)));
            oos.writeObject(l);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarLugar(Lugar l) {
        File temp = null;
        try {
            temp = File.createTempFile("lugar", "tmp");
            ois = new ObjectInputStream(
                new BufferedInputStream(
                new FileInputStream(fichero)));
            oos = new ObjectOutputStream(
                new BufferedOutputStream(
                new FileOutputStream(temp)));
            while (true){
                Lugar lugar = (Lugar) ois.readObject();
                if (!lugar.equals(l)){
                    oos.writeObject(lugar);
                }
            }
        } catch (EOFException ex){
            try {
                ois.close();
                oos.flush();
                oos.close();
                fichero.delete();
                temp.renameTo(fichero);
            } catch (IOException ex1) {
                Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modificarLugar(Lugar l) {
        File temp = null;
        try {
            temp = File.createTempFile("lugar", "tmp");
            ois = new ObjectInputStream(
                new BufferedInputStream(
                new FileInputStream(fichero)));
            oos = new ObjectOutputStream(
                new BufferedOutputStream(
                new FileOutputStream(temp)));
            while (true){
                Lugar lugar = (Lugar) ois.readObject();
                if (!lugar.equals(l)){
                    oos.writeObject(lugar);
                } else {
                    oos.writeObject(l);
                }
            }
        } catch (EOFException ex){
            try {
                ois.close();
                oos.flush();
                oos.close();
                fichero.delete();
                temp.renameTo(fichero);
            } catch (IOException ex1) {
                Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        /*List<Lugar> lugares = new ArrayList<>();
        lugares.add(new Lugar(1,"Bizkaia Arena-BEC","Ronda de Azkue 1", "Ansio-Barakaldo","bec.jfif"));
        lugares.add(new Lugar(2,"Kafe Antzokia","Done Bikendi Kalea, 2", "Bilbao","kafe-antzokia.jpg"));
        lugares.add(new Lugar(3,"Teatro Campos Elíseos","Bertendona, 3", "Bilbao","campos.jpg"));
        LugarFile lf = new LugarFile();
        for(Lugar l : lugares){
            lf.nuevoLugar(l);
        }^*/
        LugarFile lf = new LugarFile();
        for (Lugar l : lf.getLugares()){
            System.out.println(l);
        }
    }
}
