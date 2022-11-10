import { RespuestaReportesGlobales } from './../interfaces/reportes';
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
  getReportesGlobales(): Observable<RespuestaReportesGlobales> {
    return this._http.get<any>(this.apiURL + '/reportesGlobales');
  }
}
