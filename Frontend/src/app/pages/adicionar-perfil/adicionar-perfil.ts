import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-adicionar-perfil',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './adicionar-perfil.html',
  styleUrl: './adicionar-perfil.css',
})
export class AdicionarPerfil {

  constructor(private api: ApiService, private router: Router) {}

  goBack(): void {
    // On adicionar-perfil page, back should go to dashboard
    this.router.navigate(['/dashboard']);
  }

  onSubmit(event: Event) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const nome = (form.querySelector('#perfil-nome') as HTMLInputElement).value;
    this.api.cadastrarPerfil({ nome }).subscribe({
      next: () => {
        alert('Perfil criado com sucesso');
        form.reset();
      },
      error: (err) => {
        const msg = err?.error || err?.message || 'Erro';
        alert('Erro: ' + msg);
      }
    });
  }
}
