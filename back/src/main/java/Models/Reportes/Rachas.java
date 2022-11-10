package Models.Reportes;

public class Rachas {
    private int valor;
    private int tier;

    private float porcentaje;

    public Rachas(int valor, int tier, float porcentaje) {
        this.valor = valor;
        this.tier = tier;
        this.porcentaje = porcentaje;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }
}
