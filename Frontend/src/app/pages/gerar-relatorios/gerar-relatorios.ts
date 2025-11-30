import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-gerar-relatorios',
  standalone: true,              // 👈 ESSA LINHA FAZ TODA A DIFERENÇA
  imports: [RouterModule, CommonModule],
  templateUrl: './gerar-relatorios.html',
  styleUrl: './gerar-relatorios.css',
})
export class GerarRelatorios {
  devices: any[] = [];

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.api.listarDispositivos().subscribe({
      next: (res) => { this.devices = Array.isArray(res) ? res : []; },
      error: () => { this.devices = []; }
    });
  }

  onSubmit(event: Event) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const tipo = (form.querySelector('#tipo-relatorio') as HTMLSelectElement).value;
    const aparelho = (form.querySelector('#aparelho') as HTMLSelectElement).value;
    const inicio = (form.querySelector('#data-inicio') as HTMLInputElement).value;
    const fim = (form.querySelector('#data-fim') as HTMLInputElement).value;

    if (tipo === 'por-aparelho') {
      // aparelho is expected to be device id when populated
      const id = aparelho;
      if (!id || id === 'todos') {
        alert('Selecione um aparelho válido');
        return;
      }
      this.api.relatorioTotal(id).subscribe({
        next: (res) => { alert('Relatório gerado (ver console)'); console.log(res); },
        error: (err) => { alert('Erro: ' + (err?.error || err?.message || 'Erro')); }
      });
    } else {
      alert('Tipo de relatório ainda não implementado no front-end');
    }
  }

}