import { ReportesService } from './../services/reportes.service';
import { Router } from '@angular/router';
import { AuthService } from './../services/auth.service';
import { ChartOptions, ChartConfiguration } from 'chart.js';

import { Component } from '@angular/core';

@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css'],
})
export class ReportesComponent {
  // #region Chart.js
  // Torta izquierda
  public pieChartOptions: any = {
    responsive: false,
    plugins: {
      legend: {
        position: 'right',
        labels: {
          color: 'white',
          fontSize: 20,
        },
      },
    },
  };
  public pieChartLabels = ['% de Croupier', '% de Jugadores'];
  public pieChartDatasets = [
    /* {
      data: [300, 500],
      borderWidth: 0,
      backgroundColor: ['#FF6384bb', '#36A2EBbb'],
      hoverBackgroundColor: ['#FF6384', '#36A2EB'],
    }, */
  ];
  public pieChartLegend = true;
  public pieChartPlugins = [];

  // Torta derecha
  public pieChartOptions2: any = {
    responsive: false,
    plugins: {
      legend: {
        position: 'right',
        labels: {
          color: 'white',
          fontSize: 20,
        },
      },
    },
  };
  public pieChartLabels2 = ['% de Victorias', '% de Empates', '% de Derrotas'];
  public pieChartDatasets2 = [
    /*  {
      data: [300, 500, 100],
      borderWidth: 0,
      backgroundColor: ['#04aa6dbb', '#FFCE56bb', '#FF6384bb'],
      hoverBackgroundColor: ['#04aa6d', '#FFCE56', '#FF6384'],
    }, */
  ];
  public pieChartLegend2 = true;
  public pieChartPlugins2 = [];

  // Grafico historico

  public barChartLegend = true;
  public barChartPlugins = [];

  public barChartData: any = {
    labels: ['2006', '2007', '2008', '2009', '2010', '2011', '2012'],
    datasets: [
      {
        data: [65, 59, 80, 81, 56, 55, 40],
        label: 'Series A',
        borderWidth: 0,
        backgroundColor: ['#36A2EBbb'],
        hoverBackgroundColor: ['#36A2EB'],
      },
      {
        data: [28, 48, 40, 19, 86, 27, 90],
        label: 'Series B',
        borderWidth: 0,
        backgroundColor: ['#FFCE56bb'],
        hoverBackgroundColor: ['#FFCE56'],
      },
    ],
  };

  public barChartOptions: any = {
    responsive: true,
    plugins: {
      legend: {
        labels: {
          color: 'white',
          fontSize: 20,
        },
      },
    },
  };

  // #endregion
  constructor(
    private auth: AuthService,
    private router: Router,
    private reporteService: ReportesService
  ) {}
  rachaVictorias: any = {};
  cantPartidas: any = {};
  rachaDerrotas: any = {};
  cantBJ: any = {};
  ngOnInit(): void {
    this.reporteService.getReportesIndividuales().subscribe((data) => {
      this.rachaVictorias = data.rachaVictorias;
      this.cantPartidas = data.cantPartidas;
      this.rachaDerrotas = data.rachaDerrotas;
      this.cantBJ = data.cantBJ;
    });
    this.reporteService.getTortaIzq().subscribe((data) => {
      // Torta izquierda
      this.pieChartDatasets = [
        {
          data: [data.compuP, data.jugadorP],
          borderWidth: 0,
          backgroundColor: ['#FF6384bb', '#36A2EBbb'],
          hoverBackgroundColor: ['#FF6384', '#36A2EB'],
        },
      ] as any;
    });

    this.reporteService.getTortaDer().subscribe((data) => {
      // Torta derecha
      this.pieChartDatasets2 = [
        {
          data: [data.win, data.tie, data.lose],
          borderWidth: 0,
          backgroundColor: ['#04aa6dbb', '#FFCE56bb', '#FF6384bb'],
          hoverBackgroundColor: ['#04aa6d', '#FFCE56', '#FF6384'],
        },
      ] as any;
    });

    this.reporteService.getGrafico().subscribe((data) => {
      // Grafico historico
      this.barChartData = {
        labels: [...data.fecha],
        datasets: [
          {
            data: [...data.jugadores],
            label: 'Jugadores',
            borderWidth: 0,
            backgroundColor: ['#FF6384bb'],
            hoverBackgroundColor: ['#FF6384'],
          },
          {
            data: [...data.partidas],
            label: 'Partidas',
            borderWidth: 0,
            backgroundColor: ['#36A2EBbb'],
            hoverBackgroundColor: ['#36A2EB'],
          },
        ],
      };
    });
  }

  getTierClass(tier: number) {
    switch (tier) {
      case 1:
        return 'boton bronce';
      case 2:
        return 'boton plata';
      case 3:
        return 'boton oro';
      default:
        return 'boton bronce';
    }
  }

  logout() {
    this.auth.logout();
  }

  volver() {
    this.router.navigate(['/']);
  }
}
