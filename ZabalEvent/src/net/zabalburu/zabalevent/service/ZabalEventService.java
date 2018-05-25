/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.service;

import java.util.List;
import net.zabalburu.zabalevent.dao.CategoriaDAO;
import net.zabalburu.zabalevent.dao.CategoriaList;
import net.zabalburu.zabalevent.dao.EventoDAO;
import net.zabalburu.zabalevent.dao.EventoList;
import net.zabalburu.zabalevent.dao.LugarBBDD;
import net.zabalburu.zabalevent.dao.LugarDAO;
import net.zabalburu.zabalevent.dao.LugarList;
import net.zabalburu.zabalevent.dao.UsuarioDAO;
import net.zabalburu.zabalevent.dao.UsuarioEventoDAO;
import net.zabalburu.zabalevent.dao.UsuarioEventoList;
import net.zabalburu.zabalevent.dao.UsuarioFile;
import net.zabalburu.zabalevent.dao.UsuarioList;
import net.zabalburu.zabalevent.modelo.Categoria;
import net.zabalburu.zabalevent.modelo.Evento;
import net.zabalburu.zabalevent.modelo.Lugar;
import net.zabalburu.zabalevent.modelo.Usuario;
import net.zabalburu.zabalevent.modelo.UsuarioEvento;

/**
 *
 * @author Iñigo
 */
public class ZabalEventService {
    private CategoriaDAO categoriaDAO = new CategoriaList();
    private EventoDAO eventoDAO = new EventoList();
    private LugarDAO lugarDAO = new LugarBBDD();
    private UsuarioDAO usuarioDAO = new UsuarioList();
    private UsuarioEventoDAO usuarioEventoDAO = new UsuarioEventoList();
    
    public List<Categoria> getCategorias(){
        return categoriaDAO.getCategorias();
    }
    
    public Categoria getCategoria(int idCategoria){
        return categoriaDAO.getCategoria(idCategoria);
    }
    
    public boolean nuevaCategoria(Categoria c){
        int i;
        List<Categoria> categorias = categoriaDAO.getCategorias();
        for (i = 0; i < categorias.size()
            && !c.getNombre().equalsIgnoreCase(
                    categorias.get(i).getNombre())
                ; i++);
        if (i==categorias.size()){
            categoriaDAO.nuevaCategoria(c);
            return true;
        } else {
            return false;
        }
    }
    
    public void eliminarCategoria(Categoria c){
        categoriaDAO.eliminarCategoria(c);
    }
    
    public void modificarCategoria(Categoria c){
        categoriaDAO.modificarCategoria(c);
    }
    
    public List<Lugar> getLugares(){
        return lugarDAO.getLugares();
    }
    
    public Lugar getLugar(int idLugar){
        return lugarDAO.getLugar(idLugar);
    }
    
    public boolean nuevoLugar(Lugar c){
        int i;
        List<Lugar> lugares = lugarDAO.getLugares();
        for (i = 0; i < lugares.size()
            && !c.getNombre().equalsIgnoreCase(
                    lugares.get(i).getNombre())
                ; i++);
        if (i<lugares.size()){
            lugarDAO.nuevoLugar(c);
            return true;
        } else {
            return false;
        }
    }
    
    public void eliminarLugar(Lugar c){
        lugarDAO.eliminarLugar(c);
    }
    
    public void modificarLugar(Lugar c){
        lugarDAO.modificarLugar(c);
    }
    
    public List<Evento> getEventos(){
        return eventoDAO.getEventos();
    }
    
    public Evento getEvento(int idEvento){
        return eventoDAO.getEvento(idEvento);
    }
    
    public void nuevoEvento(Evento e){
        eventoDAO.nuevoEvento(e);
    }
    
    public void eliminarEvento(Evento e){
        eventoDAO.eliminarEvento(e);
    }
    
    public void modificarEvento(Evento e){
        eventoDAO.modificarEvento(e);
    }
    
    public List<Evento> getEventosLugar(int idLugar){
        return eventoDAO.getEventosLugar(idLugar);
    }
    
    public List<Evento> getEventosCategoria(int idCategoria){
        return eventoDAO.getEventosCategoria(idCategoria);
    }
    
    public List<Evento> getEventosUsuario(int idUsuario){
        return eventoDAO.getEventosUsuario(idUsuario);
    }
    
    public List<Usuario> getUsuarios(){
        return usuarioDAO.getUsuarios();
    }
    public Usuario getUsuario(int idUsuario){
        return usuarioDAO.getUsuario(idUsuario);
    }
    
    public boolean nuevoUsuario(Usuario u){
        List<Usuario> usuarios = usuarioDAO.getUsuarios();
        int i;
        for(i=0; i<usuarios.size() && 
            !u.getEmail().equalsIgnoreCase(
                usuarios.get(i).getEmail());i++);
        if (i==usuarios.size()){
            usuarioDAO.nuevoUsuario(u);
            return true;
        } else {
            return false;
        }
    }
    
    public void eliminarUsuario(Usuario u){
        usuarioDAO.eliminarUsuario(u);
    }
    
    public void modificarUsuario(Usuario c){
        usuarioDAO.modificarUsuario(c);
    }
    
    public List<Usuario> getUsuariosEvento(int idEvento){
        return usuarioDAO.getUsuariosEvento(idEvento);
    }
    
    /**
     * 
     * Intenta loguear a un usuario con su usuario y su password
     * 
     * @param email La dirección de correo del usuario
     * @param password La contraseña
     * @return Un valor null si el usuario no existe o la contraseña es errónea.
     *         El usuario al que corresponden dichos datos si existe
     */
    public Usuario login(String email, String password){
        Usuario u = null;
        int i;
        List<Usuario> usuarios = usuarioDAO.getUsuarios();
        for(i=0; i<usuarios.size() && 
                !email.equalsIgnoreCase(
                        usuarios.get(i).getEmail());
                i++);
        if (i < usuarios.size()){
            if (usuarios.get(i).getPassword().equals(password)){
                u = usuarios.get(i);
            }
        }
        return u;
    }
    
    public void apuntarAEvento(int idEvento, int idUsuario){
        UsuarioEvento ue = new UsuarioEvento(idUsuario, idEvento);
        usuarioEventoDAO.nuevaUsuarioEvento(ue);
    }
    
    public void quitarDeEvento(int idEvento, int idUsuario){
        UsuarioEvento ue = new UsuarioEvento(idUsuario, idEvento);
        usuarioEventoDAO.eliminarUsuarioEvento(ue);
    }
    
    
    public static void main(String[] args) {
        ZabalEventService s = new ZabalEventService();
        /*for(Categoria c : s.getCategorias()){
            System.out.println(c.getNombre());
            for(Evento e : s.getEventosCategoria(c.getIdCategoria())){
                System.out.println("\t"+e.getTitulo() +
                    s.getLugar(e.getIdLugar()).getDireccion());
            }
        }*/
        
        s.eliminarUsuario(new Usuario(2,"","","",""));
        for(Usuario u : s.getUsuarios()){
            System.out.println(u);
        }
    }
}
