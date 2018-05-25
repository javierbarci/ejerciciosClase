/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.ArrayList;
import java.util.List;
import net.zabalburu.zabalevent.modelo.Usuario;
import net.zabalburu.zabalevent.modelo.UsuarioEvento;

/**
 *
 * @author Iñigo
 */
public class UsuarioList implements UsuarioDAO{
    private static List<Usuario> usuarios = new ArrayList<>();

    public UsuarioList(){
        usuarios.add(new Usuario(1,"Juan López","juanLopez@zabalevent.com","12345","1.jpg"));
        usuarios.add(new Usuario(2,"Ana Marín","anaMarin@zabalevent.com","12345","2.jpg"));
        usuarios.add(new Usuario(3,"Sara Sanz","saraSanz@zabalevent.com","12345","3.jpg"));
        usuarios.add(new Usuario(4,"Carlos Ginés","carlosGines@zabalevent.com","12345","4.jpg"));
    }
    
    @Override
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    @Override
    public Usuario getUsuario(int idUsuario) {
        /*int i;
        for(i=0;i<categorias.size() && 
            idUsuario != usuarios.get(i).getIdUsuario();
            i++);
        if (i==usuarios.size()){
            return null;
        } else {
            return usuarios.get(i);
        }*/
        Usuario buscar = new Usuario();
        buscar.setIdUsuario(idUsuario);
        int pos = usuarios.indexOf(buscar);
        if (pos == -1){
            return null;
        } else {
            return usuarios.get(pos);
        }
    }

    @Override
    public void nuevoUsuario(Usuario c) {
        int idUsuario;
        if (usuarios.isEmpty()){
            idUsuario = 1;
        } else {
            idUsuario = usuarios.get(usuarios.size()-1).getIdUsuario() + 1;
        }
        c.setIdUsuario(idUsuario);
        usuarios.add(c);
    }

    @Override
    public void eliminarUsuario(Usuario c) {
        usuarios.remove(c);
    }

    @Override
    public void modificarUsuario(Usuario c) {
        int pos = usuarios.indexOf(c);
        if (pos != -1){
            usuarios.set(pos, c);
        }
    }

    @Override
    public List<Usuario> getUsuariosEvento(int idEvento) {
        List<Usuario> usuarios = new ArrayList<>();
        UsuarioEventoDAO usuarioEventoDAO = new UsuarioEventoList();
        for (UsuarioEvento ue : usuarioEventoDAO.getUsuarioEventosUsuario(idEvento)){
            Usuario u = this.getUsuario(ue.getIdUsuario());
            usuarios.add(u);
        }
        return usuarios;
    }
    
}
