/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.ArrayList;
import java.util.List;
import net.zabalburu.zabalevent.modelo.Categoria;

/**
 *
 * @author Iñigo
 */
public class CategoriaList implements CategoriaDAO{
    private static List<Categoria> categorias = new ArrayList<>();

    public CategoriaList(){
        categorias = new ArrayList<>();
        categorias.add(new Categoria(1,"Conciertos"));
        categorias.add(new Categoria(2,"Exposiciones"));
        categorias.add(new Categoria(3,"Teatro"));
        categorias.add(new Categoria(4,"Congresos"));
    }
    
    @Override
    public List<Categoria> getCategorias() {
        return categorias;
    }

    @Override
    public Categoria getCategoria(int idCategoria) {
        /*int i;
        for(i=0;i<categorias.size() && 
            idCategoria != categorias.get(i).getIdCategoria();
            i++);
        if (i==categorias.size()){
            return null;
        } else {
            return categorias.get(i);
        }*/
        Categoria buscar = new Categoria();
        buscar.setIdCategoria(idCategoria);
        int pos = categorias.indexOf(buscar);
        if (pos == -1){
            return null;
        } else {
            return categorias.get(pos);
        }
    }

    @Override
    public void nuevaCategoria(Categoria c) {
        int idCategoria;
        if (categorias.isEmpty()){
            idCategoria = 1;
        } else {
            idCategoria = categorias.get(
                categorias.size()-1).getIdCategoria() + 1;
        }
        c.setIdCategoria(idCategoria);
        categorias.add(c);
    }

    @Override
    public void eliminarCategoria(Categoria c) {
        categorias.remove(c);
    }

    @Override
    public void modificarCategoria(Categoria c) {
        int pos = categorias.indexOf(c);
        if (pos != -1){
            categorias.set(pos, c);
        }
    }
    
}
