import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient } from '@angular/common/http';
import { Credenciales } from '../interfaces/credenciales';
@Injectable()
export class AuthService {
  private apiURL = 'http://localhost:8080/login';
  private manos: any = {};
  constructor(private _http: HttpClient, public jwtHelper: JwtHelperService) {}

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }

  public setManos(manos: any) {
    this.manos = manos;
  }

  public getManos() {
    return this.manos;
  }

  public login(usuario: string, contraseña: string): Observable<any> {
    return this._http.post(this.apiURL, {
      usuario,
      password: contraseña,
    });
  }
}
