import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  constructor(private auth: AuthService, private api: ApiService, private router: Router) {}
  goBack(): void {
    this.router.navigate(['/landingpage']);
  }
  clearPassword(): void {
    const el = document.getElementById('login-password') as HTMLInputElement | null;
    if (el) {
      el.value = '';
    }
  }

  async onSubmit(event: Event) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const email = (form.querySelector('#login-email') as HTMLInputElement).value;
    const senha = (form.querySelector('#login-password') as HTMLInputElement).value;
    this.auth.login(email, senha).subscribe({
      next: () => {
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        const msg = this.api.getErrorMessage(err);
        alert('Erro: ' + msg);
      }
    });
  }
}
