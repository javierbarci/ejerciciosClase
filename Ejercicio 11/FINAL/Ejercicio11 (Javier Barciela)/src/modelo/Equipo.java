 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import util.Util;

/**
 *
 * @author pc
 */
public class Equipo {
    
    int pos; //identifica posición del equipo en nombresEquipos y la imagen del escudo
    int partidosJugados;
    int ganados;
    int empatados;
    int perdidos;
    int golesFavor;
    int golesContra;

    int puntos; //puntos que ha conseguido el equipo en función de sus resultados
    
    public Equipo(int pos){
        this.pos = pos;
    }
    
    public int getPerdidos(){
        return this.partidosJugados - this.ganados - this.empatados;
    }
    
    public String getNombre(int pos){
        return Util.nombresEquipos[pos];
    }
    
    public int getDiferenciaGoles(){
        return this.golesFavor - this.golesContra;
    }
    
    public void añadirResultado(liga.Partido partido){
        if(this.pos != partido.getLocal() && this.pos != partido.getVisitante()){
            return;
        } else if(this.pos == partido.getLocal()) {//es el equipo local
            this.golesFavor += partido.getGolesLocal();
            this.golesContra += partido.getGolesVisitante();
            if (partido.getGolesLocal() > partido.getGolesVisitante()){
                this.puntos += 3;
                this.ganados++;
            } else if (partido.getGolesLocal() == partido.getGolesVisitante()){
                this.puntos++;
                this.empatados++;
            } else if (partido.getGolesLocal() < partido.getGolesVisitante()){
                this.perdidos++;
            }
        } else if(this.pos == partido.getVisitante()){//es el equipo visitante
            this.golesFavor += partido.getGolesVisitante();
            this.golesContra += partido.getGolesLocal();
            if (partido.getGolesLocal() < partido.getGolesVisitante()){
                this.puntos += 3;
                this.ganados++;
            } else if (partido.getGolesLocal() == partido.getGolesVisitante()){
                this.puntos++;
                this.empatados++;
            } else if (partido.getGolesLocal() > partido.getGolesVisitante()){
                this.perdidos++;
            }
        }
        this.partidosJugados++;
        
    }

    public int getPos() {
        return pos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getGanados() {
        return ganados;
    }

    public int getEmpatados() {
        return empatados;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    
}
