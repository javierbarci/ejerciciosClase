/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.List;
import net.zabalburu.zabalevent.modelo.Evento;

/**
 *
 * @author Iñigo
 */
public interface EventoDAO {
    List<Evento> getEventos();
    Evento getEvento(int idEvento);
    void nuevoEvento(Evento c);
    void eliminarEvento(Evento c);
    void modificarEvento(Evento c);
    List<Evento> getEventosLugar(int idLugar);
    List<Evento> getEventosCategoria(int idCategoria);
    List<Evento> getEventosUsuario(int idUsuario);
}
