/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.ArrayList;
import java.util.List;
import net.zabalburu.zabalevent.modelo.UsuarioEvento;

/**
 *
 * @author Iñigo
 */
public class UsuarioEventoList implements UsuarioEventoDAO{
    private static List<UsuarioEvento> usuariosEventos = new ArrayList<>();

    public UsuarioEventoList(){
        usuariosEventos = new ArrayList<>();
        usuariosEventos.add(new UsuarioEvento(1,1));
        usuariosEventos.add(new UsuarioEvento(1,3));
        usuariosEventos.add(new UsuarioEvento(2,1));
        usuariosEventos.add(new UsuarioEvento(2,2));
        usuariosEventos.add(new UsuarioEvento(3,5));
        usuariosEventos.add(new UsuarioEvento(3,7));
        usuariosEventos.add(new UsuarioEvento(4,9));
        usuariosEventos.add(new UsuarioEvento(4,2));
    }
    
    @Override
    public List<UsuarioEvento> getUsuarioEventos() {
        return usuariosEventos;
    }

    @Override
    public UsuarioEvento getUsuarioEvento(int idUsuario, int idEvento) {
        /*int i;
        for(i=0;i<categorias.size() && 
            idUsuarioEvento != usuariosEventos.get(i).getIdUsuarioEvento();
            i++);
        if (i==usuariosEventos.size()){
            return null;
        } else {
            return usuariosEventos.get(i);
        }*/
        UsuarioEvento buscar = new UsuarioEvento();
        buscar.setIdUsuario(idUsuario);
        buscar.setIdEvento(idEvento);
        int pos = usuariosEventos.indexOf(buscar);
        if (pos == -1){
            return null;
        } else {
            return usuariosEventos.get(pos);
        }
    }

    @Override
    public void nuevaUsuarioEvento(UsuarioEvento c) {
        usuariosEventos.add(c);
    }

    @Override
    public void eliminarUsuarioEvento(UsuarioEvento c) {
        usuariosEventos.remove(c);
    }

    @Override
    public void modificarUsuarioEvento(UsuarioEvento c) {
        int pos = usuariosEventos.indexOf(c);
        if (pos != -1){
            usuariosEventos.set(pos, c);
        }
    }

    @Override
    public List<UsuarioEvento> getUsuarioEventosEvento(int idEvento){
        List<UsuarioEvento> ue = new ArrayList<>();
        for(UsuarioEvento u : usuariosEventos){
            if (u.getIdEvento() == idEvento){
                ue.add(u);
            }
        }
        return ue;
    }

    @Override
    public List<UsuarioEvento> getUsuarioEventosUsuario(int idUsuario) {
        List<UsuarioEvento> ue = new ArrayList<>();
        for(UsuarioEvento u : usuariosEventos){
            if (u.getIdEvento() == idUsuario){
                ue.add(u);
            }
        }
        return ue;
    }
    
}
