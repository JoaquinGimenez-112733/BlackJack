import { Injectable } from '@angular/core';
import {
  AbstractControl,
  AsyncValidator,
  ValidationErrors,
} from '@angular/forms';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class UserAsyncValidator implements AsyncValidator {
  constructor(private auth: AuthService) {}

  validate(control: AbstractControl): Observable<ValidationErrors | null> {
    return this.auth.userCheck(control.value).pipe(
      tap((a) => {
        console.log(a);
      }),
      map((result) => (result ? { usuarioExists: true } : null)),
      catchError(() => of(null))
    );
  }
}
