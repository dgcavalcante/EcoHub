import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { environment } from '../../../environments/environment';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './cadastro.html',
  styleUrl: './cadastro.css'
})
export class Cadastro {
  constructor(private api: ApiService) {}

  onSubmit(event: Event) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const nome = (form.querySelector('#nome-completo') as HTMLInputElement).value;
    const cpf = (form.querySelector('#cpf') as HTMLInputElement).value;
    const dataNascimento = (form.querySelector('#data-nascimento') as HTMLInputElement).value;
    const email = (form.querySelector('#email-cadastro') as HTMLInputElement).value;
    const senha = (form.querySelector('#senha-cadastro') as HTMLInputElement).value;
    const confirmarSenha = (form.querySelector('#confirmar-senha') as HTMLInputElement).value;
    const body = {
      nomeCompleto: nome,
      cpf: cpf,
      dataNascimento: dataNascimento,
      email: email,
      senha: senha,
      confirmarSenha: confirmarSenha
    };
    this.api.registerUser(body).subscribe({
      next: () => {
        alert('Cadastro realizado com sucesso');
        form.reset();
      },
      error: (err) => {
        const msg = err?.error || err?.message || 'Erro';
        alert('Erro: ' + msg);
      }
    });
  }
}
