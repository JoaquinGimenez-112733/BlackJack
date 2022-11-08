import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import Swal from 'sweetalert2';
import { UserAsyncValidator } from '../services/userAsyncValidator';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  formulario: FormGroup;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private auth: AuthService,
    private asyncValidator: UserAsyncValidator
  ) {}

  ngOnInit(): void {
    this.formulario = this.fb.group({
      usuario: [
        '',
        {
          validators: [Validators.required],
          asyncValidators: [this.asyncValidator],
        },
      ],
      password: ['', Validators.required],
    });
  }

  registrar() {
    this.auth
      .signup(
        this.formulario.get('usuario')?.value,
        this.formulario.get('password')?.value
      )
      .subscribe({
        next: () => {
          Swal.fire(
            'Felicitaciones!',
            'Jugador registrado exitosamente!',
            'success'
          );
          this.formulario.reset();
          this.router.navigateByUrl('/');
        },
        error: () => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'No se pudo registrar!',
          });
        },
      });
  }
  cancelar() {
    this.router.navigateByUrl('/');
  }
}
