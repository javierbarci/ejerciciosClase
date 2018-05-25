/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liga;

/**
 *
 * @author Inigo
 */
public class Partido {
    private int jornada;
    private int local;
    private int visitante;

    public Partido(int jornada, int local, int visitante, int golesLocal, int golesVisitante) {
        this.jornada = jornada;
        this.local = local;
        this.visitante = visitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    
    public String toString(){
        return this.jornada + " : " +
                util.Util.nombresEquipos[this.local] +
                " - " + util.Util.nombresEquipos[this.visitante] +
                " : " + this.golesLocal + "-" + this.golesVisitante;
                
    }
    
    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public int getVisitante() {
        return visitante;
    }

    public void setVisitante(int visitante) {
        this.visitante = visitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
    private int golesLocal;
    private int golesVisitante;
}
