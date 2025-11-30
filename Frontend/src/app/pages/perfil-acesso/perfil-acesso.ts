import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-perfil-acesso',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './perfil-acesso.html',
  styleUrl: './perfil-acesso.css',
})
export class PerfilAcesso implements OnInit {
  perfis: any[] = [];
  currentUserName: string | null = null;

  constructor(private api: ApiService, private auth: AuthService, private router: Router) {}

  goBack(): void {
    // From perfil-acesso we want to return to the dashboard
    this.router.navigate(['/dashboard']);
  }

  ngOnInit(): void {
    this.api.listarPerfis().subscribe({
      next: (res) => { this.perfis = Array.isArray(res) ? res : []; },
      error: () => { this.perfis = []; }
    });

    this.auth.currentUser$.subscribe((u: any) => {
      if (!u) { this.currentUserName = null; return; }
      this.currentUserName = u.nomeCompleto ?? u.nome ?? u.email ?? null;
    });
  }

}
