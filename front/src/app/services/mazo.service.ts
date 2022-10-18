import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Carta } from '../interfaces/carta';
@Injectable({
  providedIn: 'root',
})
export class MazoService {
  private apiURL = 'http://localhost:8080/mazo';
  constructor(private _http: HttpClient) {}

  nuevoMazo(): Observable<any> {
    const headers = {
      'content-type': 'application/json charset=utf-8',
      'x-auth-token': localStorage.getItem('token') || '',
    };
    return this._http.get<any>(this.apiURL, { headers: headers });
  }

  pedirCarta(): Observable<any> {
    const headers = {
      'content-type': 'application/json charset=utf-8',
      'x-auth-token': localStorage.getItem('token') || '',
    };
    return this._http.get<any>(this.apiURL + '/pedir', {
      headers: headers,
    });
  }
}
