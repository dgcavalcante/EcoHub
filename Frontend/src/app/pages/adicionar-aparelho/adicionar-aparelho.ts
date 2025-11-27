import { Component, OnInit } from '@angular/core'; // Component e OnInit importados corretamente
import { Router, RouterModule } from '@angular/router'; // Router e RouterModule consolidados
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-adicionar-aparelho',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './adicionar-aparelho.html',
  styleUrl: './adicionar-aparelho.css',
})
export class AdicionarAparelhoComponent implements OnInit {

  // Modelo de dados vinculado ao formulário usando [(ngModel)] USEI PARA TESTE (Pode apagar)
  newDevice = {
    name: '',
    type: '',
    location: '',
    consumption: 0,
    ip: ''
  };

  // O Router é injetado no construtor
  constructor(private router: Router) { } 

  ngOnInit(): void {
    // Inicialização, se necessário
  }

  /**
   * Função chamada quando o formulário é submetido (ngSubmit).
   * Contém a lógica de simulação para salvar e navegar.
   */
  saveDevice() {
    console.log('Dados do novo aparelho salvos (Simulação):', this.newDevice);
    // Nota: O 'alert()' deve ser substituído por um modal customizado em produção.
    alert(`Aparelho "${this.newDevice.name}" adicionado com sucesso!`);

    // Após salvar (simulação), navega de volta para a lista de aparelhos
    this.router.navigate(['/controle-aparelhos']); 
  }
}