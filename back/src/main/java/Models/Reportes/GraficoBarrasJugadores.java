package Models.Reportes;

import java.util.ArrayList;

public class GraficoBarrasJugadores {
    private ArrayList<String> fecha;
    private ArrayList<Integer> jugadores;

    public GraficoBarrasJugadores(ArrayList<String> fecha, ArrayList<Integer> jugadores) {
        this.fecha = fecha;
        this.jugadores = jugadores;
    }

    public ArrayList<String> getFecha() {
        return fecha;
    }

    public void setFecha(ArrayList<String> fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Integer> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Integer> jugadores) {
        this.jugadores = jugadores;
    }
}
