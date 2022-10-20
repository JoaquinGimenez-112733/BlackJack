import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Carta } from '../interfaces/carta';
@Injectable({
  providedIn: 'root',
})
export class MazoService {
  private apiURL = 'http://localhost:8080';
  constructor(private _http: HttpClient) {}

  nuevoMazo(): Observable<any> {
    return this._http.get<any>(this.apiURL + '/mazo', {
      headers: {
        'content-type': 'application/json charset=utf-8',
        Authorization: `${localStorage.getItem('token')}`,
      },
    });
  }

  pedirCarta(): Observable<any> {
    return this._http.get<any>(this.apiURL + '/pedir', {
      headers: {
        'content-type': 'application/json charset=utf-8',
        Authorization: `${localStorage.getItem('token')}`,
      },
    });
  }

  plantarse(): Observable<any> {
    return this._http.get<any>(this.apiURL + '/pasar', {
      headers: {
        'content-type': 'application/json charset=utf-8',
        Authorization: `${localStorage.getItem('token')}`,
      },
    });
  }
}
