import { Router } from '@angular/router';
import { AuthService } from './../services/auth.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  @Input() usuario: string = '';
  @Input() password: string = '';

  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {}

  login() {
    this.auth.login(this.usuario, this.password).subscribe((data) => {
      data.token && localStorage.setItem('token', data.token);
      this.auth.setManos(data.manos);
      this.router.navigate(['/']);
    });
  }
}
