import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,              // 👈 ESSA LINHA FAZ TODA A DIFERENÇA
  imports: [RouterModule, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  usersCount = 0;
  devicesCount = 0;
  userName: string | null = null;

  constructor(private api: ApiService, private auth: AuthService) {}

  ngOnInit(): void {
    // load counts
    this.api.listarUsuarios().subscribe({ next: (res) => { this.usersCount = Array.isArray(res) ? res.length : 0; }, error: () => { this.usersCount = 0; } });
    this.api.listarDispositivos().subscribe({ next: (res) => { this.devicesCount = Array.isArray(res) ? res.length : 0; }, error: () => { this.devicesCount = 0; } });

    // subscribe to current user so the UI shows the logged-in user's name
    this.auth.currentUser$.subscribe((u: any) => {
      if (!u) { this.userName = null; return; }
      this.userName = u.nomeCompleto ?? u.nome ?? u.email ?? null;
    });
  }

}
