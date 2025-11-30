
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-editar-perfil',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './editar-perfil.html',
  styleUrl: './editar-perfil.css',
})
export class EditarPerfil implements OnInit {
  userNome: string = '';
  userEmail: string = '';
  userCpf: string = '';

  constructor(private api: ApiService, private auth: AuthService) {}

  ngOnInit(): void {
    const u = this.auth.currentUser;
    if (u) {
      this.userNome = u.nomeCompleto ?? u.nome ?? '';
      this.userEmail = u.email ?? '';
      this.userCpf = u.cpf ?? '';
    }

    this.auth.currentUser$.subscribe(user => {
      if (!user) return;
      this.userNome = user.nomeCompleto ?? user.nome ?? '';
      this.userEmail = user.email ?? '';
      this.userCpf = user.cpf ?? '';
    });
  }

  onSubmit(event: Event) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const cpf = (form.querySelector('#cpf') as HTMLInputElement)?.value || '';
    const nome = (form.querySelector('#nome-completo') as HTMLInputElement).value;
    const email = (form.querySelector('#email') as HTMLInputElement).value;
    const senhaAtual = (form.querySelector('#senha-atual') as HTMLInputElement).value;
    const novaSenha = (form.querySelector('#nova-senha') as HTMLInputElement).value;
    const confirmar = (form.querySelector('#confirmar-nova-senha') as HTMLInputElement).value;

    // Atualiza nome e email via cpf
    if (cpf) {
      this.api.updateUser(cpf, { nomeCompleto: nome, email }).subscribe({
        next: () => { alert('Dados atualizados com sucesso'); },
        error: (err) => { alert('Erro: ' + (err?.error || err?.message || 'Erro')); }
      });
    }

    // Atualiza senha se informada
    if (novaSenha) {
      if (novaSenha !== confirmar) { alert('As senhas não coincidem'); return; }
      // backend espera PUT /users/atualizar/{email} com Senha DTO { senha, confirmarSenha }
      this.api.updatePassword(email, { senha: novaSenha, confirmarSenha: confirmar }).subscribe({
        next: () => { alert('Senha atualizada com sucesso'); },
        error: (err) => { alert('Erro ao atualizar senha: ' + (err?.error || err?.message || 'Erro')); }
      });
    }
  }

}
