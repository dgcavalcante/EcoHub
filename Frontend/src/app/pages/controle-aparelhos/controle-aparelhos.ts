import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-controle-aparelhos',
  standalone: true,              // 👈 ESSA LINHA FAZ TODA A DIFERENÇA
  imports: [RouterModule, CommonModule],
  templateUrl: './controle-aparelhos.html',
  styleUrl: './controle-aparelhos.css',
})
export class ControleAparelhos implements OnInit {
  devices: any[] = [];

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.api.listarDispositivos().subscribe({
      next: (res) => { this.devices = Array.isArray(res) ? res : []; },
      error: () => { this.devices = []; }
    });
  }

}
