package Models.Reportes;

import java.util.ArrayList;

public class GraficoBarrasPartidas {
    private ArrayList<String> fecha;
    private ArrayList<Integer> partidas;

    public GraficoBarrasPartidas(ArrayList<String> fecha, ArrayList<Integer> partidas) {
        this.fecha = fecha;
        this.partidas = partidas;
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
}
