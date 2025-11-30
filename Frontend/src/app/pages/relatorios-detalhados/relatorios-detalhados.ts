import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-relatorios-detalhados',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './relatorios-detalhados.html',
  styleUrl: './relatorios-detalhados.css',
})
export class RelatoriosDetalhados implements OnInit {
  devices: any[] = [];

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.api.listarDispositivos().subscribe({ next: (res) => { this.devices = Array.isArray(res) ? res : []; }, error: () => { this.devices = []; } });
  }

}
