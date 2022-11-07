import { Router } from '@angular/router';
import { AuthService } from './../services/auth.service';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  @Input() usuario: string = '';
  @Input() password: string = '';

  formulario: FormGroup;
  constructor(
    private auth: AuthService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.formulario = this.fb.group({
      usuario: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  login() {
    this.auth
      .login(
        this.formulario.get('usuario')?.value,
        this.formulario.get('password')?.value
      )
      .subscribe((data) => {
        data.token && localStorage.setItem('token', data.token);
        this.router.navigate(['/']);
      });
  }

  goToRegistrar() {
    this.router.navigate(['/signup']);
  }
}
