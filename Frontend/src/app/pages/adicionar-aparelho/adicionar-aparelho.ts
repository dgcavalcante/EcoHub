import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { environment } from '../../../environments/environment';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-adicionar-aparelho',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './adicionar-aparelho.html',
  styleUrl: './adicionar-aparelho.css',
})
export class AdicionarAparelhoComponent implements OnInit {

  newDevice = {
    name: '',
    type: '',
    location: '',
    consumption: 0,
    ip: ''
  };

  constructor(private router: Router, private api: ApiService) { }

  ngOnInit(): void {}

  async saveDevice() {
    const dto: any = {
      nome: this.newDevice.name,
      tipo: this.newDevice.type,
      consumoPorHora: this.newDevice.consumption,
      statusDispositivo: true
    };
    this.api.cadastrarDispositivo(dto).subscribe({
      next: () => {
        alert('Aparelho adicionado com sucesso');
        this.router.navigate(['/controle-aparelhos']);
      },
      error: (err) => {
        const msg = err?.error || err?.message || 'Erro de rede';
        alert('Erro: ' + msg);
      }
    });
  }
}