/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.List;
import net.zabalburu.zabalevent.modelo.UsuarioEvento;

/**
 *
 * @author Iñigo
 */
public interface UsuarioEventoDAO {
    List<UsuarioEvento> getUsuarioEventos();
    UsuarioEvento getUsuarioEvento(int idUsuario, int idEvento);
    void nuevaUsuarioEvento(UsuarioEvento c);
    void eliminarUsuarioEvento(UsuarioEvento c);
    void modificarUsuarioEvento(UsuarioEvento c);
    List<UsuarioEvento> getUsuarioEventosEvento(int idEvento);
    List<UsuarioEvento> getUsuarioEventosUsuario(int idUsuario);
}
