/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.ArrayList;
import java.util.List;
import net.zabalburu.zabalevent.modelo.Lugar;

/**
 *
 * @author Iñigo
 */
public class LugarList implements LugarDAO{
    private static List<Lugar> lugares = new ArrayList<>();

    public LugarList(){
        lugares = new ArrayList<>();
        lugares.add(new Lugar(1,"Bizkaia Arena-BEC","Ronda de Azkue 1", "Ansio-Barakaldo","bec.jfif"));
        lugares.add(new Lugar(2,"Kafe Antzokia","Done Bikendi Kalea, 2", "Bilbao","kafe-antzokia.jpg"));
        lugares.add(new Lugar(3,"Teatro Campos Elíseos","Bertendona, 3", "Bilbao","campos.jpg"));
    }
    
    @Override
    public List<Lugar> getLugares() {
        return lugares;
    }

    @Override
    public Lugar getLugar(int idLugar) {
        /*int i;
        for(i=0;i<categorias.size() && 
            idLugar != lugares.get(i).getIdLugar();
            i++);
        if (i==lugares.size()){
            return null;
        } else {
            return lugares.get(i);
        }*/
        Lugar buscar = new Lugar();
        buscar.setIdLugar(idLugar);
        int pos = lugares.indexOf(buscar);
        if (pos == -1){
            return null;
        } else {
            return lugares.get(pos);
        }
    }

    @Override
    public void nuevoLugar(Lugar c) {
        int idLugar;
        if (lugares.isEmpty()){
            idLugar = 1;
        } else {
            idLugar = lugares.get(lugares.size()-1).getIdLugar() + 1;
        }
        c.setIdLugar(idLugar);
        lugares.add(c);
    }

    @Override
    public void eliminarLugar(Lugar c) {
        lugares.remove(c);
    }

    @Override
    public void modificarLugar(Lugar c) {
        int pos = lugares.indexOf(c);
        if (pos != -1){
            lugares.set(pos, c);
        }
    }
    
}
