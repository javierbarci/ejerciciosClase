/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.List;
import net.zabalburu.zabalevent.modelo.Lugar;

/**
 *
 * @author Iñigo
 */
public interface LugarDAO {
    List<Lugar> getLugares();
    Lugar getLugar(int idLugar);
    void nuevoLugar(Lugar c);
    void eliminarLugar(Lugar c);
    void modificarLugar(Lugar c);
}
