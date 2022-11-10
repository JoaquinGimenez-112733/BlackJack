import { AuthService } from './../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Carta } from '../interfaces/carta';
import { MazoService } from '../services/mazo.service';
import { Router } from '@angular/router';

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

  alerta: boolean = false;
  textoAlerta: string = '';
  tipoAlerta: string = 'success';

  flagNuevaPartida = false;

  constructor(
    private auth: AuthService,
    private mazoService: MazoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.auth.getPartidaEnCurso().subscribe({
      next: (cartas: any) => {
        this.mazo = cartas?.mazo; //mazo nuevo
        this.puntosCompu = cartas?.puntosCompu;
        this.puntosJugador = cartas?.puntosJugador;
        this.puntoOcultoCompu = cartas?.puntoOcultoCompu;
        this.manoCompu = cartas?.manoCompu;
        this.manoJugador = cartas?.manoJugador;

        if (this.puntosJugador == 0) {
          console.log('false');
          this.flagNuevaPartida = false;
        } else {
          console.log('true');
          this.flagNuevaPartida = true;
        }
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  cerrarNotificacion() {
    this.alerta = false;
  }

  showAlerta() {
    this.alerta = true;
    setTimeout(() => {
      this.alerta = false;
    }, 3000);
  }

  reiniciar() {
    this.mazo = [];
    this.manoJugador = [];
    this.manoCompu = [];
    this.jugadores = [this.manoJugador];
    this.carta = {} as Carta;
    this.puntosJugador = 0;
    this.puntosCompu = 0;
    this.puntoOcultoCompu = 0;
    this.alerta = false;
    this.textoAlerta = '';
    this.tipoAlerta = 'success';
    this.flagNuevaPartida = false;
  }

  nuevaPartida() {
    this.flagNuevaPartida = true;
    this.reiniciar();

    this.mazoService.nuevoMazo().subscribe({
      next: (cartas: any) => {
        this.flagNuevaPartida = true;
        this.mazo = cartas?.mazo; //mazo nuevo
        this.puntosCompu = cartas?.puntosCompu;
        this.puntosJugador = cartas?.puntosJugador;
        this.puntoOcultoCompu = cartas?.puntoOcultoCompu;
        this.manoCompu = cartas?.manoCompu;
        this.manoJugador = cartas?.manoJugador;
      },
    });
  }

  pedirCarta() {
    this.mazoService.pedirCarta().subscribe({
      next: (cartas: any) => {
        this.mazo = cartas?.mazo; //mazo nuevo
        this.puntosCompu = cartas?.puntosCompu;
        this.puntosJugador = cartas?.puntosJugador;
        this.puntoOcultoCompu = cartas?.puntoOcultoCompu;
        this.manoCompu = cartas?.manoCompu;
        this.manoJugador = cartas?.manoJugador;

        if (this.puntosJugador > 21) {
          this.mazoService.perdiste().subscribe({
            next: (cartas: any) => {
              this.mazo = cartas?.mazo; //mazo nuevo
              this.puntosCompu = cartas?.puntosCompu;
              this.puntosJugador = cartas?.puntosJugador;
              this.puntoOcultoCompu = cartas?.puntoOcultoCompu;
              this.manoCompu = cartas?.manoCompu;
              this.manoJugador = cartas?.manoJugador;

              this.manoCompu[1].orden = 99;
              this.flagNuevaPartida = false;
              //this.puntosCompu = this.puntosCompu + this.puntoOcultoCompu;
            },
          });
          this.textoAlerta = 'Perdiste';
          this.tipoAlerta = 'danger';
          this.showAlerta();
          this.flagNuevaPartida = false;
        }
      },
    });
  }

  pasar() {
    if (this.puntosCompu > 21) {
      this.textoAlerta = 'Ganaste!';
      this.tipoAlerta = 'success';
      this.showAlerta();
      this.flagNuevaPartida = false;
    }

    this.mazoService.plantarse().subscribe({
      next: (cartas: any) => {
        this.mazo = cartas?.mazo; //mazo nuevo
        this.puntosCompu = cartas?.puntosCompu;
        this.puntosJugador = cartas?.puntosJugador;
        this.puntoOcultoCompu = cartas?.puntoOcultoCompu;
        this.manoCompu = cartas?.manoCompu;
        this.manoJugador = cartas?.manoJugador;

        if (this.flagNuevaPartida != false) {
          if (
            (this.puntosJugador > this.puntosCompu &&
              this.puntosJugador <= 21) ||
            this.puntosCompu > 21
          ) {
            this.textoAlerta = 'Ganaste!';
            this.tipoAlerta = 'success';
            this.showAlerta();
            this.flagNuevaPartida = false;
          } else if (
            (this.puntosCompu > this.puntosJugador && this.puntosCompu <= 21) ||
            this.puntosJugador > 21
          ) {
            this.textoAlerta = 'Perdiste!';
            this.tipoAlerta = 'danger';
            this.showAlerta();
            this.flagNuevaPartida = false;
          } else if (this.puntosCompu == this.puntosJugador) {
            this.textoAlerta = 'Empate!';
            this.tipoAlerta = 'warning';
            this.showAlerta();
            this.flagNuevaPartida = false;
          }
        }
      },
    });
  }

  sumarPuntos() {
    this.manoJugador.forEach((element) => {
      if (element.numero == 'A' && this.puntosJugador < 12) {
        this.puntosJugador += 10;
      }
      this.puntosJugador += element.valor;
    });
  }
  sumarPuntosCompu() {
    this.manoCompu.forEach((element) => {
      this.puntosCompu += element.valor;
    });
  }
  logout() {
    this.auth.logout();
  }

  verEstadisticas() {
    this.router.navigate(['/reportes']);
  }
}
