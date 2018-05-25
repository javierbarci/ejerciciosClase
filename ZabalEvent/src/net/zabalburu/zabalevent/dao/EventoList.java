/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu.zabalevent.dao;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import net.zabalburu.zabalevent.modelo.Evento;
import net.zabalburu.zabalevent.modelo.UsuarioEvento;

/**
 *
 * @author Iñigo
 */
public class EventoList implements EventoDAO {

    private static List<Evento> eventos = new ArrayList<>();

    public EventoList() {
        eventos = new ArrayList<>();
        eventos.add(new Evento(1, 1, "Las noches de Comedy Central", "Euskaltel trae a tres de los mejores cómicos del momento a Las Noches de Comedy Central.\n"
                + "Un evento exclusivo y gratuito para los clientes de Euskaltel.\n"
                + "\n"
                + "La promoción se llevará a cabo del 27/02/2018 al 05/03/2018. Exclusivo para clientes de Euskaltel. El sorteo se celebrará el 06/03/2018 publicando los nombres de los ganadores en la web el 07/03/2018. Euskaltel se pondrá en contacto con los ganadores vía telefónica.", 3, 1, new GregorianCalendar(2018, 2, 10).getTime(), 20, "comedy.png"));
        eventos.add(new Evento(2, 3, "ImagineNano", "Nanociencia & Nanotecnología\n"
                + "\n"
                + "THE LARGEST EUROPEAN EVENT IN NANOTECHNOLOGY", 4, 1, new GregorianCalendar(2018, 2, 13).getTime(), 250, "imagine.jpg"));
        eventos.add(new Evento(3, 1, "Berri Txarrak", "Atención: las entradas para el concierto del BEC están volando. Las entradas para pista ya se han agotado, así como los tickets especiales “experiencia intrasoinuak”. Así las cosas, ampliamos aforo y damos el salto al Bizkaia Arena, el mayor recinto del BEC. Las ENTRADAS PARA GRADA ya están a la venta. Recordad que se mantiene el precio de lanzamiento (17 € + gastos) hasta el día 26 de diciembre. A partir de esa fecha las entradas subirán de precio, y en taquilla -si quedaran-, costarán 25 €. Va a ser memorable, no lo dejes para última hora!!!\n"
                + "\n"
                + "20:00 Apertura de puertas\n"
                + "00:30 Finalización concierto", 1, 1, new GregorianCalendar(2018, 2, 17).getTime(), 20, "berri.jpg"));
        eventos.add(new Evento(4, 2, "E.V.A.", "La veterana compañía catalana T de Teatre presenta E.V.A., una comedia dramática que tiene como principal eje el tema del dolor, de hecho las siglas de su título se corresponden con las de Escala Visual Analógica del Dolor. Cuatro ex compañeras de escuela nos descubren sus historias vitales para reflexionar sobre el dolor en todas sus facetas: el dolor físico, el dolor crónico, el dolor somático, el dolor neuropático, el dolor vital, el dolor moral, el dolor cotidiano, el dolor del alma… Un texto escrito conjuntamente por Marc Artigau, Cristina Genebat y Julio Manrique dirigido por el propio Manrique.", 3, 3, new GregorianCalendar(2018, 2, 23).getTime(), 20, "eva.jpg"));
        eventos.add(new Evento(5, 2, "Dany & The Champions Of The World", 
                "'Brilliant Light' es el sexto trabajo de los británicos Danny & The Champions of the World, producido por su bajista Chris Clarke Chris en los estudios londinenses de Reservoir Studios. En este último trabajo de estudio sus canciones exudan ese sabor a Stax y a toda esa localidad de Muscle Shoals en Alabama, una delicia y un claro homenaje al country, folk, bluegrass, góspel, R&B y rock."
                , 1, 2, new GregorianCalendar(2018, 2, 9).getTime(), 17, "dany.jpg"));
        eventos.add(new Evento(6, 3, "Fito y Fitipaldis", "Durante 2018, Fito & Fitipaldis llevará a cabo la gira de celebración, “20 años, 20 ciudades”.\n"
                + "\n"
                + "Los Fitipaldis han preparado un repertorio exclusivo de grandes éxitos que se presentará en grandes recintos de 20 únicas ciudades. En cada concierto Fito invitará a diferentes artistas y amigos a subir al escenario para compartir canciones.", 1, 1, new GregorianCalendar(2018, 4, 4).getTime(), 40, "fito.jpg"));
        eventos.add(new Evento(7, 4, "Loraldia 2018. Barruko Ametsak", "Azkuna Zentroa acoge un año más parte de la programación de Loraldia, el Festival de Primavera para la promoción de la cultura vasca en euskera, con propuestas contemporáneas de cine, música y artes escénicas.\n"
                + "\n"
                + "LORARTEA, ARTEA KALEAN: 'BARRUKO AMETSAK'. Raimon Bikandi\n"
                + "\n"
                + "'Barruko Ametsak' es una exposición artística creada por Raimon Bikandi. Las flores toman las calles horas antes del comienzo de algunos de los espectáculos de Loraldia.", 2, 3, new GregorianCalendar(2018, 2, 23).getTime(), 0, "loraldia.jfif"));
        eventos.add(new Evento(8, 2, "Los universos paralelos", "Escrito originalmente por David Lindsay-Abbaire y versionado para la ocasión por David Serrano, Los univeros paralelos es un emocionante drama protagonizado por un elenco de lujo en el que encontramos actores de la talla de Malena Alterio, Belén Cuesta, Carmen Balagué, Juan Carlos Vellido e Itzan Escamilla. Hace pocos meses, Patricia y Alberto formaban una familia feliz junto a su hijo pequeño, pero la pérdida de éste les ha sumido en una espiral de recuerdos y culpabilidades difícil de gestionar. Ahora, su decisión es intentar encontrar un camino que les permita vivir en paz.", 3, 3, new GregorianCalendar(2018, 2, 9).getTime(), 38, "universos.jpg"));
        eventos.add(new Evento(9, 2, "Thirty Seconds To Mars", "La banda multiplatino Thirty Seconds To Mars ha anunciado que se embarcarán en una gran gira europea durante marzo, abril y mayo de 2018.\n"
                + "\n"
                + "Jared Leto, Shannon Leto y Tomo Milicevic son actualmente una de las bandas en directo más vibrantes del mundo, lo que queda reflejado sin duda en la actuación con la que volvieron a los escenarios have unos meses en los premios MTV VMA. La actuación es toda una muestra de que Thirty Seconds To Mars son capaces de hacer, incluyendo la innovadora tecnología de ultrarojos y con la colaboración especial de Travis Scott. ‘Walk On Water’ es la canción que interpretaron y es el primer single del que será el quinto álbum de la banda. Actualmente están nominados en la categoria de Best Alternative en los próximos premios MTV European Music Awards, que se celebrarán el próximo 12 de noviembre.", 1, 1, new GregorianCalendar(2018, 2, 14).getTime(), 50, "thirty.jpg"));
        
        
    }

    @Override
    public List<Evento> getEventos() {
        return eventos;
    }

    @Override
    public Evento getEvento(int idEvento) {
        /*int i;
        for(i=0;i<categorias.size() && 
            idEvento != eventos.get(i).getIdEvento();
            i++);
        if (i==eventos.size()){
            return null;
        } else {
            return eventos.get(i);
        }*/
        Evento buscar = new Evento();
        buscar.setIdEvento(idEvento);
        int pos = eventos.indexOf(buscar);
        if (pos == -1) {
            return null;
        } else {
            return eventos.get(pos);
        }
    }

    @Override
    public void nuevoEvento(Evento c) {
        int idEvento;
        if (eventos.isEmpty()) {
            idEvento = 1;
        } else {
            idEvento = eventos.get(eventos.size() - 1).getIdEvento() + 1;
        }
        c.setIdEvento(idEvento);
        eventos.add(c);
    }

    @Override
    public void eliminarEvento(Evento c) {
        eventos.remove(c);
    }

    @Override
    public void modificarEvento(Evento c) {
        int pos = eventos.indexOf(c);
        if (pos != -1) {
            eventos.set(pos, c);
        }
    }

    @Override
    public List<Evento> getEventosLugar(int idLugar) {
        List<Evento> eventos = new ArrayList<>();
        for (Evento e : EventoList.eventos) {
            if (e.getIdLugar() == idLugar) {
                eventos.add(e);
            }
        }
        return eventos;
    }

    @Override
    public List<Evento> getEventosCategoria(int idCategoria) {
        List<Evento> eventos = new ArrayList<>();
        for (Evento e : EventoList.eventos) {
            if (e.getIdCategoria() == idCategoria) {
                eventos.add(e);
            }
        }
        return eventos;
    }

    @Override
    public List<Evento> getEventosUsuario(int idUsuario) {
        UsuarioEventoDAO usuarioEventoDAO = new UsuarioEventoList();
        List<Evento> eventos = new ArrayList<>();
        for (UsuarioEvento ue : usuarioEventoDAO.getUsuarioEventosUsuario(idUsuario)) {
            eventos.add(this.getEvento(ue.getIdEvento()));
        }
        return eventos;
    }

}
