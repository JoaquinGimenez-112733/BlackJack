package Models;

import Models.Reportes.Rachas;

public class EstadisticasDTO {
    //estadisticas personales
    private Rachas rachaVictorias;
    private Rachas rachaDerrotas;
    private Rachas cantBJ;
    private Rachas cantPartidas;

    public EstadisticasDTO(Rachas rachaVictorias, Rachas rachaDerrotas, Rachas cantBJ, Rachas cantPartidas) {
        this.rachaVictorias = rachaVictorias;
        this.rachaDerrotas = rachaDerrotas;
        this.cantBJ = cantBJ;
        this.cantPartidas = cantPartidas;
    }


    public Rachas getRachaVictorias() {
        return rachaVictorias;
    }

    public void setRachaVictorias(Rachas rachaVictorias) {
        this.rachaVictorias = rachaVictorias;
    }

    public Rachas getRachaDerrotas() {
        return rachaDerrotas;
    }

    public void setRachaDerrotas(Rachas rachaDerrotas) {
        this.rachaDerrotas = rachaDerrotas;
    }

    public Rachas getCantBJ() {
        return cantBJ;
    }

    public void setCantBJ(Rachas cantBJ) {
        this.cantBJ = cantBJ;
    }

    public Rachas getCantPartidas() {
        return cantPartidas;
    }

    public void setCantPartidas(Rachas cantPartidas) {
        this.cantPartidas = cantPartidas;
    }
}
