/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import modelo.Pelicula;

/**
 *
 * @author Iñigo
 */
public class PeliculaService {
    private List<Pelicula> peliculas = new ArrayList<>();
    private List<String> generos = new ArrayList<>();
    
    public PeliculaService(){
        peliculas.add(new Pelicula("El Padrino",1972,"MGM","La historia comienza en el verano de 1945, cuando se celebra la boda de Connie (Talia Shire) y Carlo Rizzi (Gianni Russo). Connie es la hija de Don Vito Corleone (Marlon Brando), es cabeza de la familia Corleone, y jefe de una de las cinco familias que ejercen el mando de la Cosa Nostra en la ciudad de Nueva York. Con el argumento de que todo siciliano debe atender las peticiones que le hacen el día de la boda de su hija, Don Vito es visitado, en el primer plano de la acción, por Amerigo Bonasera, dueño de una funeraria",5,"NC-17","EEUU","padrino.jpg"));
        peliculas.add(new Pelicula("12 Hombres Sin Piedad",1957,"Paramount","Los doce miembros de un jurado deben juzgar a un adolescente acusado de haber matado a su padre. Todos menos uno están convencidos de la culpabilidad del acusado. El que disiente intenta con sus razonamientos introducir en el debate una duda razonable que haga recapacitar a sus compañeros para que cambien el sentido de su voto.",5,"PG-13","EEUU","12hombres.jpg"));
        peliculas.add(new Pelicula("Cadena Perpetua",1994,"MGM","Acusado del asesinato de su mujer, Andrew Dufresne (Tim Robbins), tras ser condenado a cadena perpetua, es enviado a la cárcel de Shawshank. Con el paso de los años conseguirá ganarse la confianza del director del centro y el respeto de sus compañeros de prisión, especialmente de Red (Morgan Freeman), el jefe de la mafia de los sobornos.",4,"PG","EEUU","cadena.jpg"));
        peliculas.add(new Pelicula("La Vida es Bella",1997,"Universal","En 1939, a punto de estallar la Segunda Guerra Mundial (1939-1945), el extravagante Guido llega a Arezzo, en la Toscana, con la intención de abrir una librería. Allí conoce a la encantadora Dora y, a pesar de que es la prometida del fascista Rodolfo, se casa con ella y tiene un hijo. Al estallar la guerra, los tres son internados en un campo de exterminio, donde Guido hará lo imposible para hacer creer a su hijo que la terrible situación que están padeciendo es tan sólo un juego. ",4,"G","Italia","vida.jpg"));
        List<String> idiomas = new ArrayList<>();
        idiomas.add("Inglés");
        idiomas.add("Castellano");
        peliculas.get(0).setIdiomas(idiomas);
        peliculas.get(1).setIdiomas(idiomas);
        peliculas.get(2).setIdiomas(idiomas);
        peliculas.get(3).setIdiomas(idiomas);
        List<String> genero = new ArrayList<>();
        genero.add("Drama");
        genero.add("Crimen");
        genero.add("Mafia");
        peliculas.get(0).setGenero(genero);
        List<Pelicula> puedeInteresar = new ArrayList<Pelicula>();
        puedeInteresar.add(peliculas.get(1));
        puedeInteresar.add(peliculas.get(2));
        peliculas.get(0).setPuedeInteresar(puedeInteresar);
        genero = new ArrayList<>();
        genero.add("Drama");
        genero.add("Crimen");
        peliculas.get(1).setGenero(genero);
        puedeInteresar = new ArrayList<Pelicula>();
        puedeInteresar.add(peliculas.get(0));
        puedeInteresar.add(peliculas.get(2));
        peliculas.get(1).setPuedeInteresar(puedeInteresar);
        genero = new ArrayList<>();
        genero.add("Drama");
        genero.add("Carcel");
        peliculas.get(2).setGenero(genero);
        puedeInteresar = new ArrayList<Pelicula>();
        puedeInteresar.add(peliculas.get(0));
        puedeInteresar.add(peliculas.get(1));
        peliculas.get(2).setPuedeInteresar(puedeInteresar);
        genero = new ArrayList<>();
        genero.add("Comedia");
        genero.add("Drama");
        peliculas.get(3).setGenero(genero);
        generos.add("Drama");
        generos.add("Carcel");
        generos.add("Comedia");
        generos.add("Crimen");
        generos.add("Mafia");
        peliculas.get(3).setPuedeInteresar(new ArrayList<Pelicula>());
    }
    
    public  List<Pelicula> getPeliculas(){
        return this.peliculas;
    }
 
    public List<String> getGeneros(){
        return this.generos;
    }
    
    public List<Pelicula> getPeliculasGenero(String genero){
        List<Pelicula> pelis = new ArrayList<Pelicula>();
        for(Pelicula p : getPeliculas()){
            int i;
            for(i=0; i<p.getGenero().size() && 
                !genero.equalsIgnoreCase(
                    p.getGenero().get(i));i++);
            if (i<p.getGenero().size()){
                pelis.add(p);
            }
        }
        return pelis;
    }
    
}
