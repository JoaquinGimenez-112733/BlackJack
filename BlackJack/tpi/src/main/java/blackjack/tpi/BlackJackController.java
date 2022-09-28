package blackjack.tpi;

import java.util.ArrayList;
import Models.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlackJackController {

    ArrayList<Carta> nuevoMazo;

    private Mazo mazo;

    public BlackJackController() {
        mazo = new Mazo();

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/mazo")
    public ArrayList<Carta> getMazo() {
        nuevoMazo = new ArrayList<Carta>();
        nuevoMazo = mazo.nuevoMazo();
        return this.nuevoMazo;

    }
}
