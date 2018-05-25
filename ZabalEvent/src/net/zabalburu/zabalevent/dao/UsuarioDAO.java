/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.List;
import net.zabalburu.zabalevent.modelo.Usuario;

/**
 *
 * @author Iñigo
 */
public interface UsuarioDAO {
    List<Usuario> getUsuarios();
    Usuario getUsuario(int idUsuario);
    void nuevoUsuario(Usuario c);
    void eliminarUsuario(Usuario c);
    void modificarUsuario(Usuario c);
    List<Usuario> getUsuariosEvento(int idEvento);
}
