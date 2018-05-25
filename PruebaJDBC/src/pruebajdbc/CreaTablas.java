/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iñigo
 */
public class CreaTablas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection cnn = null;
        Statement stmt = null;
        try {
            // Registrar el driver de la BBDD
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Conexión a la BBDD
            cnn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:ORCL",
                    "scott", "tiger");
            stmt = cnn.createStatement();
            try {
                stmt.executeUpdate("Drop table usuarioEventos");
            } catch (SQLException ex) {
             
            }
            try {
                stmt.executeUpdate("Drop table Eventos");
            } catch (SQLException ex) {
             
            }
            try {
                stmt.executeUpdate("Drop table Usuarios");
            } catch (SQLException ex) {
            }
            
            try {
                stmt.executeUpdate("Drop table Categorias");
            } catch (SQLException ex) {
            }
            try {
                stmt.executeUpdate("Drop table Lugares");
            } catch (SQLException ex) {
            }
            
            // Categorías
            stmt.executeUpdate(
                    "Create Table Categorias ("
                    + "idCategoria number(3) constraint PK_CATEGORIAS primary key,"
                    + "nombre varchar2(120) not null)");
            stmt.executeUpdate("Insert into Categorias values(1,'Conciertos')");
            stmt.executeUpdate("Insert into Categorias values(2,'Exposiciones')");
            stmt.executeUpdate("Insert into Categorias values(3,'Teatro')");
            stmt.executeUpdate("Insert into Categorias values(4,'Congresos')");

            //Lugares
            stmt.executeUpdate("Create table Lugares("
                    + "idLugar number(3) constraint PK_LUGARES primary key,"
                    + "nombre varchar2(150) not null,"
                    + "direccion varchar2(255),"
                    + "localidad varchar2(120),"
                    + "foto varchar2(120))"
            );
            stmt.executeUpdate("Insert into Lugares values(1,'Bizkaia Arena-BEC','Ronda de Azkue 1', 'Ansio-Barakaldo','bec.jfif')");
            stmt.executeUpdate("Insert into Lugares values(2,'Kafe Antzokia','Done Bikendi Kalea, 2', 'Bilbao','kafe-antzokia.jpg')");
            stmt.executeUpdate("Insert into Lugares values(3,'Teatro Campos Elíseos','Bertendona, 3', 'Bilbao','campos.jpg')");

            //Usuarios
            stmt.executeUpdate("Create table Usuarios("
                    + "idUsuario number(6) constraint PK_USUARIOS primary key,"
                    + "nombre varchar2(120) not null,"
                    + "email varchar2(120) not null,"
                    + "password varchar2(255) not null,"
                    + "foto varchar2(120))");
            stmt.executeUpdate("Insert into Usuarios Values(1,'Juan López','juanLopez@zabalevent.com','12345','1.jpg')");
            stmt.executeUpdate("Insert into Usuarios Values(2,'Ana Marín','anaMarin@zabalevent.com','12345','2.jpg')");
            stmt.executeUpdate("Insert into Usuarios Values(3,'Sara Sanz','saraSanz@zabalevent.com','12345','3.jpg')");
            stmt.executeUpdate("Insert into Usuarios Values(4,'Carlos Ginés','carlosGines@zabalevent.com','12345','4.jpg')");

            //Eventos
            stmt.executeUpdate(
                    "Create table Eventos("
                    + "idEvento number(5) constraint PK_EVENTOS primary key,"
                    + "idUsuario number(6) constraint FK_EVENTOS_USUARIOS references usuarios,"
                    + "titulo varchar2(120) not null,"
                    + "descripcion varchar2(2000), "
                    + "idCategoria number(3) constraint FK_EVENTOS_CATEGORIAS references categorias,"
                    + "idLugar number(3) constraint FK_EVENTOS_LUGARES references Lugares,"
                    + "fecha date,"
                    + "precio number(7,2),"
                    + "foto varchar2(120))");

            List<Evento> eventos = new ArrayList<>();
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
                    "'Brilliant Light' es el sexto trabajo de los británicos Danny & The Champions of the World, producido por su bajista Chris Clarke Chris en los estudios londinenses de Reservoir Studios. En este último trabajo de estudio sus canciones exudan ese sabor a Stax y a toda esa localidad de Muscle Shoals en Alabama, una delicia y un claro homenaje al country, folk, bluegrass, góspel, R&B y rock.",
                    1, 2, new GregorianCalendar(2018, 2, 9).getTime(), 17, "dany.jpg"));
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

            PreparedStatement pst = cnn.prepareStatement(
                    "Insert into eventos values(?,?,?,?,?,?,?,?,?)");
            for (Evento e : eventos) {
                pst.setInt(1, e.getIdEvento());
                pst.setInt(2, e.getIdUsuario());
                pst.setString(3, e.getTitulo());
                pst.setString(4, e.getDescripcion());
                pst.setInt(5, e.getIdCategoria());
                pst.setInt(6, e.getIdLugar());
                pst.setDate(7, new java.sql.Date(e.getFecha().getTime()));
                pst.setDouble(8, e.getPrecio());
                pst.setString(9, e.getFoto());
                pst.executeUpdate();
            }

            // Usuarios / Eventos
            stmt.executeUpdate(
                "Create table usuarioEventos (" +
                "idUsuario number(6) constraint FK_USUARIOEVENTOS_USUARIOS references Usuarios," +
                "idEvento number(5) constraint FK_USUARIOEVENTOS_EVENTOS references Eventos," +
                "CONSTRAINT PK_USUARIOEVENTOS Primary Key(idUsuario,idEvento))");
            stmt.executeUpdate("insert into usuarioEventos values(1,1)");
            stmt.executeUpdate("insert into usuarioEventos values(1,3)");
            stmt.executeUpdate("insert into usuarioEventos values(2,1)");
            stmt.executeUpdate("insert into usuarioEventos values(2,2)");
            stmt.executeUpdate("insert into usuarioEventos values(3,5)");
            stmt.executeUpdate("insert into usuarioEventos values(3,7)");
            stmt.executeUpdate("insert into usuarioEventos values(4,9)");
            stmt.executeUpdate("insert into usuarioEventos values(4,2)");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreaTablas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreaTablas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
            }
            try {
                cnn.close();
            } catch (Exception ex) {
            }
        }
    }

}
