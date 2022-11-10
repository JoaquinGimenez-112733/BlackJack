package Models.Reportes;

public class TortaIzq {
    private float jugadorP;
    private float compuP;

    public TortaIzq(float jugadorP, float compuP) {
        this.jugadorP = jugadorP;
        this.compuP = compuP;
    }

    public float getJugadorP() {
        return jugadorP;
    }

    public void setJugadorP(float jugadorP) {
        this.jugadorP = jugadorP;
    }

    public float getCompuP() {
        return compuP;
    }

    public void setCompuP(float compuP) {
        this.compuP = compuP;
    }
}
