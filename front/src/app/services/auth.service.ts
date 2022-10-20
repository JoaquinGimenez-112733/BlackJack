import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient } from '@angular/common/http';
import { Credenciales } from '../interfaces/credenciales';
@Injectable()
export class AuthService {
  constructor(private _http: HttpClient, public jwtHelper: JwtHelperService) {}

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }

  public getPartidaEnCurso(): Observable<any> {
    return this._http.get('http://localhost:8080/partida-en-curso', {
      responseType: 'json',
      headers: {
        Authorization: `${localStorage.getItem('token')}`,
      },
    });
  }

  public login(usuario: string, contraseña: string): Observable<any> {
    return this._http.post('http://localhost:8080/login', {
      usuario,
      password: contraseña,
    });
  }

  public logout(): void {
    localStorage.removeItem('token');
    window.location.reload();
  }
}
