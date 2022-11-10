package Models.Reportes;

import java.util.ArrayList;

public class GraficoBarras {
    private ArrayList<String> fecha;
    private ArrayList<Integer> partidas;
    private ArrayList<Integer> jugadores;

    public GraficoBarras(ArrayList<String> fecha, ArrayList<Integer> partidas, ArrayList<Integer> jugadores) {
        this.fecha = fecha;
        this.partidas = partidas;
        this.jugadores = jugadores;
    }

    public ArrayList<String> getFecha() {
        return fecha;
    }

    public void setFecha(ArrayList<String> fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Integer> getPartidas() {
        return partidas;
    }

    public void setPartidas(ArrayList<Integer> partidas) {
        this.partidas = partidas;
    }

    public ArrayList<Integer> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Integer> jugadores) {
        this.jugadores = jugadores;
    }
}
