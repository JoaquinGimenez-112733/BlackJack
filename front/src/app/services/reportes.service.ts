import {
  Grafico,
  RespuestaReportesGlobales,
  TortaDer,
  TortaIzq,
} from './../interfaces/reportes';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RespuestaReportesIndividuales } from '../interfaces/reportes';

@Injectable({
  providedIn: 'root',
})
export class ReportesService {
  private apiURL = 'http://localhost:8080';

  constructor(private _http: HttpClient) {}

  getReportesIndividuales(): Observable<RespuestaReportesIndividuales> {
    return this._http.get<any>(this.apiURL + '/reportesIndividuales', {});
  }
  getTortaIzq(): Observable<TortaIzq> {
    return this._http.get<any>(this.apiURL + '/tortaIzq');
  }
  getTortaDer(): Observable<TortaDer> {
    return this._http.get<any>(this.apiURL + '/tortaDer');
  }
  getGraficoPartidas(): Observable<Grafico> {
    return this._http.get<any>(this.apiURL + '/grafico-partidas');
  }
  getGraficoJugadores(): Observable<Grafico> {
    return this._http.get<any>(this.apiURL + '/grafico-jugadores');
  }
}
