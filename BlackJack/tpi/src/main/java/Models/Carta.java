
package Models;

public class Carta {
    
    private String numero;
    private String palo;
    private int valor;
    private int orden;

    public Carta(String numero, String palo, int valor, int orden) {
        this.numero = numero;
        this.palo = palo;
        this.valor = valor;
        this.orden = orden;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "Carta{" + "numero=" + numero + ", palo=" + palo + ", valor=" + valor + ", orden=" + orden + '}';
    }
    
    
    
}