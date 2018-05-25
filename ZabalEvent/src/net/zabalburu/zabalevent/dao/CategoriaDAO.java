/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.List;
import net.zabalburu.zabalevent.modelo.Categoria;

/**
 *
 * @author Iñigo
 */
public interface CategoriaDAO {
    List<Categoria> getCategorias();
    Categoria getCategoria(int idCategoria);
    void nuevaCategoria(Categoria c);
    void eliminarCategoria(Categoria c);
    void modificarCategoria(Categoria c);
}
