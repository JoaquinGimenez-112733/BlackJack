/*
Esta clase se utilizará para instanciar la respuesta que le enviaremos al FRONT
con las manos del jugador, de la computadora, y del mazo restante.
 */
package Models;

import java.util.ArrayList;

public class manosPartidaDTO {

    private ArrayList<Carta> mazo;
    private ArrayList<Carta> manoJugador;
    private ArrayList<Carta> manoCompu;
    private int puntosJugador;
    private int puntosCompu;
    private int puntosOcultosCompu;

    public manosPartidaDTO(ArrayList<Carta> mazo, ArrayList<Carta> manoJugador, ArrayList<Carta> manoCompu, int puntosJugador, int puntosCompu, int puntosOcultosCompu) {
        this.mazo = mazo;
        this.manoJugador = manoJugador;
        this.manoCompu = manoCompu;
        this.puntosJugador = puntosJugador;
        this.puntosCompu = puntosCompu;
        this.puntosOcultosCompu = puntosOcultosCompu;
    }

    public manosPartidaDTO() {
    }

    public ArrayList<Carta> getMazo() {
        return mazo;
    }

    public void setMazo(ArrayList<Carta> mazo) {
        this.mazo = mazo;
    }

    public ArrayList<Carta> getManoJugador() {
        return manoJugador;
    }

    public void setManoJugador(ArrayList<Carta> manoJugador) {
        this.manoJugador = manoJugador;
    }

    public ArrayList<Carta> getManoCompu() {
        return manoCompu;
    }

    public void setManoCompu(ArrayList<Carta> manoCompu) {
        this.manoCompu = manoCompu;
    }

    public int getPuntosJugador() {
        return puntosJugador;
    }

    public void setPuntosJugador(int puntosJugador) {
        this.puntosJugador = puntosJugador;
    }

    public int getPuntosCompu() {
        return puntosCompu;
    }

    public void setPuntosCompu(int puntosCompu) {
        this.puntosCompu = puntosCompu;
    }

    public int getPuntosOcultosCompu() {
        return puntosOcultosCompu;
    }

    public void setPuntosOcultosCompu(int puntosOcultosCompu) {
        this.puntosOcultosCompu = puntosOcultosCompu;
    }

}
