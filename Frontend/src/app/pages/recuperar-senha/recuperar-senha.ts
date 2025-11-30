import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { environment } from '../../../environments/environment';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-recuperar-senha',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './recuperar-senha.html',
  styleUrl: './recuperar-senha.css',
})
export class RecuperarSenha {
  constructor(private api: ApiService) {}

  onSubmit(event: Event) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const email = (form.querySelector('#recover-email') as HTMLInputElement).value;
    this.api.recoverPassword({ email }).subscribe({
      next: (res) => {
        const text = (res && typeof res === 'string') ? res : 'Email enviado';
        alert(text);
        form.reset();
      },
      error: (err) => {
        const msg = err?.error || err?.message || 'Erro de rede';
        alert('Erro: ' + msg);
      }
    });
  }

}
