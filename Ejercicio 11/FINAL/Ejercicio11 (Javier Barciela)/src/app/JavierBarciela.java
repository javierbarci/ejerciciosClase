/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JOptionPane;
import liga.Partido;
import util.Util;
import modelo.Equipo;

/**
 *
 * @author daw1
 */
public class JavierBarciela {
    
    private static Equipo[] equipos = new Equipo[20];
    
    public static void main(String[] args) {
        
        cargarDatosEquipos();
        String resp;
        int opc;
        do {
            resp = JOptionPane.showInputDialog(null, mostrarMenu());
            opc = Integer.parseInt(resp);
            switch(opc){
                case 1:
                    verClasificación();
                    break;
                case 2:
                    verJornada();
                    break;
                case 3:
                    modificarResultado();
                    break;
                case 4:
                    masGoleadores();
                    break;
                case 5:
                    mejorDiferencia();
                    break;
                case 6:
                    pronosticoQuiniela();
                    break;
            }
        } while (opc != 7);
    }
    
    private static void cargarDatosEquipos(){
        for(int pos=0; pos<equipos.length; pos++){
            equipos[pos] = new Equipo(pos);
            for(int i=0; i<Util.partidos.length; i++){
                if(Util.partidos[i].getGolesLocal() != -1){
                    equipos[pos].añadirResultado(Util.partidos[i]);
                }
            }
        }
        
    }
    
    private static String mostrarMenu(){
        return              "Liga Profesional\n\n" +
                            "1. Ver Clasificación\n" +
                            "2. Ver Jornada\n" +
                            "3. Modificar Resultado\n" +
                            "4. Equipos Más Goleadores\n" +
                            "5. Equipos con Mejor Diferencia de Goles\n" +
                            "6. Pronóstico Quiniela\n" +
                            "7. Salir\n\n" +
                            "Opción [1-7]";
    }
    
    private static void verClasificación(){ // SELECCIÓN
        clasificacionSeleccion();
        String listado =   "<html><body><table bgcolor='#FFFFFF'>";
        listado +=         "<tr><th colspan='2'>Equipo</th>"
                            + "<th>PJ</th>"
                            + "<th>G</th>"
                            + "<th>E</th>"
                            + "<th>P</th>"
                            + "<th>GF</th>"
                            + "<th>GC</th>"
                            + "<th>Ptos</th></tr>";
        for (int i=0; i<equipos.length; i++){
            listado += "<tr><td><img src='file:" + equipos[i].getPos() + ".jpg' style='float:left' height='25'  width='25' /></td>"
                    + "<td>" + equipos[i].getNombre(equipos[i].getPos()) + "</td>"
                    + "<td>" + equipos[i].getPartidosJugados() + "</td>"
                    + "<td>" + equipos[i].getGanados() + "</td>"
                    + "<td>" + equipos[i].getEmpatados() + "</td>"
                    + "<td>" + equipos[i].getPerdidos() + "</td>"
                    + "<td>" + equipos[i].getGolesFavor() + "</td>"
                    + "<td>" + equipos[i].getGolesContra() + "</td>"
                    + "<td><big>" + equipos[i].getPuntos() + "</big></td>"
                    + "</tr>";
        } 
        listado += "</table></body></html>";
        JOptionPane.showMessageDialog(null, listado, "Clasificación", JOptionPane.PLAIN_MESSAGE);
    }
    
    private static void clasificacionSeleccion(){
        int i,j,mayor;
        Equipo temp;
        for(i=0; i<equipos.length-1; i++){
            mayor = i;
            for(j=i+1; j<equipos.length; j++){
                if (equipos[j].getPuntos() > equipos[mayor].getPuntos()){
                    mayor = j;
                }
            }
            if (mayor != i){
                temp = equipos[i];
                equipos[i] = equipos[mayor];
                equipos[mayor] = temp;
            }
        }
    }
    
    private static void verJornada(){ // BÚSQUEDA ORDENADA
        int jornada = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce la jornada a buscar"));
        int posJornada = verJornadaOrdenada(jornada);
        if (posJornada == -1){
            JOptionPane.showMessageDialog(null, "No se ha encontrado la jornada especificada.", 
                    "Jornada no existente", JOptionPane.ERROR);
        } else { // ENCONTRADO
            String infoJornada = "<html><body><table>";
            infoJornada += "<tr><td colspan='7' align='center'><big>Jornada " + jornada + "</big></td></tr>";
            for(; jornada == Util.partidos[posJornada].getJornada(); posJornada++){
                if (Util.partidos[posJornada].getGolesLocal() != -1){ // SI SE HA DISPUTADO
                    infoJornada += "<tr>"
                            + "<td><img src='file:" + Util.partidos[posJornada].getLocal() + ".jpg' width='25' height='25'/></td>"
                            + "<td>" + Util.nombresEquipos[Util.partidos[posJornada].getLocal()] + "</td>"
                            + "<td>" + Util.partidos[posJornada].getGolesLocal() + "</td>"
                            + "<td>-</td>"
                            + "<td>" + Util.partidos[posJornada].getGolesVisitante()+ "</td>"
                            + "<td><img src='file:" + Util.partidos[posJornada].getVisitante()+ ".jpg' width='25' height='25'/></td>"
                            + "<td>" + Util.nombresEquipos[Util.partidos[posJornada].getVisitante()] + "</td>"
                            + "</tr>";
                } else {
                    infoJornada += "<tr>"
                            + "<td><img src='file:" + Util.partidos[posJornada].getLocal() + ".jpg' width='25' height='25'/></td>"
                            + "<td>" + Util.nombresEquipos[Util.partidos[posJornada].getLocal()] + "</td>"
                            + "<td></td>"
                            + "<td>-</td>"
                            + "<td></td>"
                            + "<td><img src='file:" + Util.partidos[posJornada].getVisitante()+ ".jpg' width='25' height='25'/></td>"
                            + "<td>" + Util.nombresEquipos[Util.partidos[posJornada].getVisitante()] + "</td>"
                            + "</tr>";
                }
            }
            infoJornada += "</table></body></html>";
            JOptionPane.showMessageDialog(null, infoJornada);
        }
    }
    
    private static int verJornadaOrdenada(int jornada){
        int i;
        for(i=0; i<Util.partidos.length && jornada != Util.partidos[i].getJornada(); i++);
        if (i<Util.partidos.length){ //ENCONTRADO
            return i;
        } else { //NO ENCONTRADO
            return -1;
        }
    }
    
    private static void modificarResultado(){ // BÚSQUEDA DESORDENADA
        String local = "local"; //Para modificar el texto al pedir el equipo
        String visitante = "visitante"; //Para modificar el texto al pedir el equipo
        int posLocal = pedirPosEquipo(local);
        int posVisitante = pedirPosEquipo(visitante);
        Partido partido = modificarResultadoDesordenada(posLocal, posVisitante);
        if (partido == null){ //SI NO SE HA ENCONTRADO EL PARTIDO
            JOptionPane.showMessageDialog(null, "No se ha encontrado el partido", "Error", JOptionPane.ERROR_MESSAGE);
        } else { // SE HA ENCONTRADO EL PARTIDO
            String infoPartido;
            if(partido.getGolesLocal() == -1){ //EL PARTIDO NO SE HA DISPUTADO
                infoPartido = "<html><body>"
                        + "<table><tr>"
                        + "<td colspan='5' align='center'><big>Jornada " + partido.getJornada() + "</big></td></tr>"
                        + "<tr><td><img src='file:" + partido.getLocal() + ".jpg' height='25'  width='25'/></td><td>" 
                        + Util.nombresEquipos[partido.getLocal()] + "</td><td>" 
                        + "</td><td>-</td><td>"
                        + "<img src='file:" + partido.getVisitante() + ".jpg' height='25'  width='25'/></td><td>"
                        + Util.nombresEquipos[partido.getVisitante()] + "</td>"
                        + "</tr></table>"
                        + "<tr><td colspan='5'>Use el siguiente formato: GolesLocal-GolesVisitante</td></tr>"
                        + "</body></html>";
            } else { //EL PARTIDO SE HA DISPUTADO
                infoPartido = "<html><body>"
                        + "<table><tr>"
                        + "<td colspan='7' align='center'><big>Jornada " + partido.getJornada() + "</big></td></tr>"
                        + "<tr><td><img src='file:" + partido.getLocal() + ".jpg' height='25'  width='25'/></td><td>" 
                        + Util.nombresEquipos[partido.getLocal()] + "</td><td>" 
                        + partido.getGolesLocal() + "</td><td>-</td><td>" 
                        + partido.getGolesVisitante() + "</td><td>"
                        + "<img src='file:" + partido.getVisitante() + ".jpg' height='25'  width='25'/></td><td>"
                        + Util.nombresEquipos[partido.getVisitante()] + "</td>"
                        + "</tr>"
                        + "<tr><td colspan='7'>Use el siguiente formato: GolesLocal-GolesVisitante</td></tr>"
                        + "</table></body></html>";
            }
            String resultado = JOptionPane.showInputDialog(null, infoPartido);
            int golesLocal = Integer.parseInt(resultado.substring(0,1));
            int golesVisitante = Integer.parseInt(resultado.substring(2,3));
            partido.setGolesLocal(golesLocal);
            partido.setGolesVisitante(golesVisitante);
            cargarDatosEquipos();
        }
    }
    
    private static Partido modificarResultadoDesordenada(int posLoc, int posVis){
        int i;
        for(i=0; i<Util.partidos.length 
                && (posLoc!=Util.partidos[i].getLocal() 
                || posVis!=Util.partidos[i].getVisitante()); i++);
        if(i<Util.partidos.length){ //ENCONTRADO
            return Util.partidos[i];
        } else {
            return null;
        }
    }
    
    private static int pedirPosEquipo(String localOVisitante){
        String listado = "<html><body><table><th>Pos</th>"
                + "<th colspan='2'>Equipo</th>"
                + "<th>Pos</th>"
                + "<th colspan='2'>Equipo</th>";
        for(int i=0; i<equipos.length/2; i++){
            listado += "<tr>"
                    + "<th>" + equipos[i].getPos() + "</th>"
                    + "<td><img src='file:" + equipos[i].getPos() + ".jpg' style='float:left' height='25'  width='25' /></td>"
                    + "<td>" + equipos[i].getNombre(equipos[i].getPos()) + "</td>"
                    + "<td>" + equipos[i+10].getPos() + "</td>"
                    + "<td><img src='file:" + equipos[i+10].getPos() + ".jpg' style='float:left' height='25'  width='25' /></td>"
                    + "<td>" + equipos[i+10].getNombre(equipos[i+10].getPos()) + "</td>"
                    + "</tr>";
        }
        if(localOVisitante.equalsIgnoreCase("local")){
            listado += "</table><p><big>Introduzca posición del equipo local</big></p></body></html>";
        } else {
            listado += "</table><p><big>Introduzca posición del equipo visitante</big></p></body></html>";
        }
        
        String resp = JOptionPane.showInputDialog(null, listado);
        return Integer.parseInt(resp);
    }
    
    private static void masGoleadores(){ // BURBUJA
        masGoleadoresBurbuja();
        String listado =   "<html><body><table bgcolor='#FFFFFF'>";
        listado +=         "<tr><th colspan='2'>Equipo</th>"
                            + "<th>Goles</th></tr>";
        int i;
        for (i=0; i<equipos.length; i++){
            listado += "<tr><td><img src='file:" + equipos[i].getPos() + ".jpg' style='float:left' height='25'  width='25' /></td>"
                    + "<td>" + equipos[i].getNombre(equipos[i].getPos()) + "</td>"
                    + "<th>" + equipos[i].getGolesFavor()+ "</th></tr>";
        } 
        listado += "</table></body></html>";
        JOptionPane.showMessageDialog(null, listado, "Equipos más goleadores", JOptionPane.PLAIN_MESSAGE);
    }
    
    private static void masGoleadoresBurbuja(){
        int i,j;
        Equipo temp;
        for(i=0; i<equipos.length-1; i++){
            for(j=equipos.length-2; j>=i; j--){
                if (equipos[j].getGolesFavor() < equipos[j+1].getGolesFavor()){
                    temp = equipos[j];
                    equipos[j] = equipos[j+1];
                    equipos[j+1] = temp;
                }
            }
        }
    }
    
    private static void mejorDiferencia(){ // INSERCIÓN
        mejorDiferenciaInsercion();
        String listado =   "<html><body><table bgcolor='#FFFFFF'>";
        listado +=         "<tr><th colspan='2'>Equipo</th>"
                            + "<th>Diferencia goles</th></tr>";
        int i;
        for (i=0; i<7; i++){ // ESTÁ HECHO PARA QUE SALGAN LOS 6 PRIMEROS Y LA SEGUNDA REPETITIVA TENGA REPERCUSIÓN EN LA TABLA
            listado += "<tr><td><img src='file:" + equipos[i].getPos() + ".jpg' style='float:left' height='25'  width='25' /></td>"
                    + "<td>" + equipos[i].getNombre(equipos[i].getPos()) + "</td>"
                    + "<th>" + equipos[i].getDiferenciaGoles()+ "</th></tr>";
        } 
        for (i=6; equipos[i].getDiferenciaGoles() == equipos[i+1].getDiferenciaGoles(); i++){
            listado += "<tr><td><img src='file:" + equipos[i+1].getPos() + ".jpg' style='float:left' height='25'  width='25' /></td>"
                    + "<td>" + equipos[i+1].getNombre(equipos[i+1].getPos()) + "</td>"
                    + "<th>" + equipos[i+1].getDiferenciaGoles()+ "</th></tr>";
        }
        listado += "</tr></table></body></html>";
        JOptionPane.showMessageDialog(null, listado, "Mejor diferencia", JOptionPane.PLAIN_MESSAGE);
    }
    
    private static void mejorDiferenciaInsercion(){ 
        int i,j;
        Equipo temp;
        for(i=0; i<equipos.length-1; i++){
            for(j=0; j<equipos.length; j++){
                if(equipos[j].getDiferenciaGoles() < equipos[i].getDiferenciaGoles()){
                    temp = equipos[i];
                    equipos[i] = equipos[j];
                    equipos[j] = temp;
                }
            }
        }
    }
    
    private static void pronosticoQuiniela(){
        int jornada = Integer.parseInt(JOptionPane.showInputDialog(null, 
                                                                   "Indica la jornada sobre la que hacer el pronóstico"));
        String listado = "<html><body><table>";
        listado += "<tr><th colspan='4'>Partido</th><th>Pronóstico</th></tr>";
        int puntosLocal;
        int puntosVisitante;
        int pesoLocal;
        int pesoVisitante;
        int posJornada = verJornadaOrdenada(jornada);
        for (; jornada == Util.partidos[posJornada].getJornada(); posJornada++){
            puntosLocal = 0;
            puntosVisitante = 0;
            pesoLocal = 0;
            pesoVisitante = 0;
            listado += "<tr>"
                    + "<th><img src='file:" + Util.partidos[posJornada].getLocal() + ".jpg' /></th>"
                    + "<th>" + Util.nombresEquipos[Util.partidos[posJornada].getLocal()] + "</th>"
                    + "<th><img src='file:" + Util.partidos[posJornada].getVisitante() + ".jpg' /></th>"
                    + "<th>" + Util.nombresEquipos[Util.partidos[posJornada].getVisitante()] + "</th>";
            for(int i=0; i<Util.partidos.length; i++){
                if (Util.partidos[i].getGolesLocal() != -1){ // SI SE HA DISPUTADO EL PARTIDO
                    if (Util.partidos[posJornada].getLocal() == Util.partidos[i].getLocal()){ // ES LOCAL EN AMBOS PARTIDOS
                        puntosLocal += getPuntosPartido(Util.partidos[i], 
                                                        Util.partidos[posJornada].getLocal(), 
                                                        true);
                    } else {
                        puntosLocal += getPuntosPartido(Util.partidos[i], 
                                                            Util.partidos[posJornada].getLocal(), 
                                                            false);
                    }
                    if (Util.partidos[posJornada].getVisitante() == Util.partidos[i].getVisitante()){ // ES VISITANTE EN AMBOS PARTIDOS
                        puntosVisitante += getPuntosPartido(Util.partidos[i], 
                                                        Util.partidos[posJornada].getVisitante(), 
                                                        true);
                    } else {
                        puntosVisitante += getPuntosPartido(Util.partidos[i], 
                                                            Util.partidos[posJornada].getVisitante(), 
                                                            false);
                    }
                }
            }
            pesoLocal = (puntosLocal * 100
                    ) / (puntosLocal + puntosVisitante);
            if (pesoLocal < 0){
                pesoLocal = pesoLocal * -1;
            }
            pesoVisitante = 100 - pesoLocal;
            if (pesoVisitante < 0){
                pesoVisitante = pesoVisitante * -1;
            }
            if (pesoLocal - pesoVisitante > 20){ // SALDRÁ UN 1
                listado += "<th>1</th></tr>";
            } else if (pesoVisitante - pesoLocal > 20) { // SALDRÁ UN 2
                listado += "<th>2</th></tr>";
            } else { //SALDRÁ UNA X
                listado += "<th>X</th></tr>";
            }
        }
        JOptionPane.showMessageDialog(null, listado);
    }
    
    private static int getPuntosPartido(Partido partido, int posEquipo, boolean mismoLugar){
        int puntos = 0;
        if (posEquipo == partido.getLocal()){ // ES LOCAL
            if (partido.getGolesLocal() > partido.getGolesVisitante()){ // HA GANADO
                if (mismoLugar){
                    puntos += 50;
                } else {
                    puntos += 40;
                }
            }
            if (partido.getGolesLocal() == partido.getGolesVisitante()){ // HA EMPATADO
                if (mismoLugar){
                    puntos += 20;
                } else {
                    puntos += 15;
                }
            }
            if (mismoLugar){
                puntos += partido.getGolesLocal() * 10;
                puntos -= partido.getGolesVisitante() * 15;
            } else {
                puntos += partido.getGolesLocal() * 5;
                puntos -= partido.getGolesVisitante() * 10;
            }
        } else if (posEquipo == partido.getVisitante()) { // ES VISITANTE
            if (partido.getGolesVisitante()> partido.getGolesLocal()){ // HA GANADO
                if (mismoLugar){
                    puntos += 60;
                } else {
                    puntos += 50;
                }
            }
            if (partido.getGolesVisitante() == partido.getGolesLocal()){ // HA EMPATADO
                if (mismoLugar){
                    puntos += 30;
                } else {
                    puntos += 20;
                }
            }
            if (mismoLugar){
                puntos += partido.getGolesVisitante() * 20;
                puntos -= partido.getGolesLocal()* 10;
            } else {
                puntos += partido.getGolesVisitante()* 15;
                puntos -= partido.getGolesLocal() * 5;
            }
        }
        return puntos;
    }
    
}
