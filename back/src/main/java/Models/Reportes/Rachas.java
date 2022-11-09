package Models.Reportes;

public class Rachas {
    private int valor;
    private int tier;

    public Rachas(int valor, int tier) {
        this.valor = valor;
        this.tier = tier;
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
}
