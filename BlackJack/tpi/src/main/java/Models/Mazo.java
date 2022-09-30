package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private ArrayList<Carta> mazo;

    public Mazo() {

       // this.mazo = new ArrayList<Carta>();
    }

    public ArrayList<Carta> nuevoMazo() {
        this.mazo = new ArrayList<Carta>();
        String[] numeros = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] palos = {"spades", "clubs", "hearts", "diamonds"};

        for (String palo : palos) {
            for (String numero : numeros) {

                if (numero == "J" || numero == "K" || numero == "Q") {
                    int valor = 10;
                    Carta carta = new Carta(numero, palo, valor, 0);
                    this.mazo.add(carta);
                } else if (numero == "A") {
                    int valor = 11;
                    Carta carta = new Carta(numero, palo, valor, 0);
                    this.mazo.add(carta);
                } else {
                    int valor = Integer.parseInt(numero);
                    Carta carta = new Carta(numero, palo, valor, 0);
                    this.mazo.add(carta);
                }

            }

        }
        Collections.shuffle(mazo);

        int orden = 1;
        for (Carta carta : mazo) {

            carta.setOrden(orden);
            orden++;
        }
        return this.mazo;
    }
}
