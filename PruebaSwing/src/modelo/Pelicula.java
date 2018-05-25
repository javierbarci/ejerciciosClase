/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author Iñigo
 */
public class Pelicula {
    private String titulo; // JTextBox
    private int año; // JSpinner
    private String sinopsis; // JTextArea
    private int valoracion; // JSlider
    private List<String> idiomas; // JCheckBox
    private String rating; // JRadioButton
    private List<String> genero; // JTree
    private String pais; // JList
    private String imagen; // JLabel
    private List<Pelicula> puedeInteresar; // JTable
    private String productora; // JComboBox

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public Pelicula() {
    }

    public Pelicula(String titulo, int año, String productora, String sinopsis, int valoracion, String rating, String pais, String imagen) {
        this.titulo = titulo;
        this.año = año;
        this.sinopsis = sinopsis;
        this.valoracion = valoracion;
        this.rating = rating;
        this.pais = pais;
        this.imagen = imagen;
        this.productora = productora;
        
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<String> getGenero() {
        return genero;
    }

    public void setGenero(List<String> genero) {
        this.genero = genero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Pelicula> getPuedeInteresar() {
        return puedeInteresar;
    }

    public void setPuedeInteresar(List<Pelicula> puedeInteresar) {
        this.puedeInteresar = puedeInteresar;
    }
    
    @Override
    public String toString(){
        return this.titulo;
    }
    
}
