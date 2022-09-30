import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Carta } from '../interfaces/carta';
import { MazoService } from '../services/mazo.service';

@Component({
  selector: 'app-mesa',
  templateUrl: './mesa.component.html',
  styleUrls: ['./mesa.component.css'],
})
export class MesaComponent implements OnInit {
  mazo: Carta[] = [];
  manoJugador: Carta[] = [];
  manoCompu: Carta[] = [];
  jugadores: Carta[][] = [this.manoJugador];

  carta = {} as Carta;
  puntosJugador: number = 0;
  puntosCompu: number = 0;
  puntoOcultoCompu: number = 0;

  flagNuevaPartida = false;

  private subs = new Subscription();
  constructor(private mazoService: MazoService) {}

  ngOnInit(): void {}

  nuevaPartida() {
    this.flagNuevaPartida = true;
    //inicilizamos
    this.puntosCompu = 0;
    this.puntosJugador = 0;
    this.puntoOcultoCompu = 0;

    this.mazo.splice(0);
    this.manoJugador.splice(0);
    this.manoCompu.splice(0);

    this.subs.add(
      this.mazoService.nuevoMazo().subscribe({
        next: (cartas: Carta[]) => {
          this.mazo = cartas; //mazo nuevo
          console.log(this.mazo);

          this.carta = this.mazo[0]; //primer carta jugador
          this.manoJugador.push(this.carta);
          this.mazo.splice(0, 1);
          this.puntosJugador = this.puntosJugador + this.carta.valor;

          this.carta = this.mazo[0]; //primer carta compu
          this.manoCompu.push(this.carta);
          this.mazo.splice(0, 1);
          this.puntosCompu = this.puntosCompu + this.carta.valor;

          this.carta = this.mazo[0]; //segunda carta jugador
          this.manoJugador.push(this.carta);
          this.mazo.splice(0, 1);
          //el ás puede valer 1 o 11 puntos, dependiendo de nuestro puntaje acumulado
          if (this.puntosJugador == 11 && this.carta.numero == 'ace') {
            this.carta.valor = 1;
          }
          this.puntosJugador = this.puntosJugador + this.carta.valor;

          this.carta = this.mazo[0]; //segunda carta jugador
          this.manoCompu.push(this.carta);
          this.mazo.splice(0, 1);
          //el ás puede valer 1 o 11 puntos, dependiendo de nuestro puntaje acumulado
          if (this.puntosCompu == 11 && this.carta.numero == 'ace') {
            this.carta.valor = 1;
          }
          this.puntoOcultoCompu = this.carta.valor;
          //this.puntosCompu = this.puntosCompu + this.carta.valor
        },
      })
    );
  }

  pedirCarta() {
    this.carta = this.mazo[0];
    //el ás puede valer 1 o 11 puntos, dependiendo de nuestro puntaje acumulado
    if (this.puntosJugador >= 11 && this.carta.numero == 'ace') {
      this.carta.valor = 1;
    }
    this.puntosJugador = this.puntosJugador + this.carta.valor;
    this.manoJugador.push(this.carta);
    this.mazo.splice(0, 1);

    if (this.puntosJugador > 21) {
      alert('Perdiste!');
      this.flagNuevaPartida = false;
    }
  }

  pasar() {
    this.manoCompu[1].orden = 99;
    this.puntosCompu += this.puntoOcultoCompu;
    //como regla de la casa, la compu saca cartas hasta obtener 17 puntos o más
    while (this.puntosCompu < 17) {
      this.carta = this.mazo[0];
      //el ás puede valer 1 o 11 puntos, dependiendo de nuestro puntaje acumulado
      if (this.puntosCompu >= 11 && this.carta.numero == 'ace') {
        this.carta.valor = 1;
      }
      this.puntosCompu = this.puntosCompu + this.carta.valor;
      this.manoCompu.push(this.carta);
      this.mazo.splice(0, 1);
      if (this.puntosCompu > 21) {
        alert('Ganaste!');
        this.flagNuevaPartida = false;
      }
    }

    if (this.flagNuevaPartida != false) {
      if (
        (this.puntosJugador > this.puntosCompu && this.puntosJugador <= 21) ||
        this.puntosCompu > 21
      ) {
        alert('Ganaste!');
        this.flagNuevaPartida = false;
      } else if (
        (this.puntosCompu > this.puntosJugador && this.puntosCompu <= 21) ||
        this.puntosJugador > 21
      ) {
        alert('Perdiste.');
        this.flagNuevaPartida = false;
      } else if (this.puntosCompu == this.puntosJugador) {
        alert('Empate.');
        this.flagNuevaPartida = false;
      }
    }
  }
}
