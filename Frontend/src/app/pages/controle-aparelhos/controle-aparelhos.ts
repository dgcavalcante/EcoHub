import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-controle-aparelhos',
  standalone: true,              // 👈 ESSA LINHA FAZ TODA A DIFERENÇA
  imports: [RouterModule, CommonModule],
  templateUrl: './controle-aparelhos.html',
  styleUrl: './controle-aparelhos.css',
})
export class ControleAparelhos {

}
