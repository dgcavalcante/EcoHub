import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pagina-nao-encontrada',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './pagina-nao-encontrada.html',
  styleUrl: './pagina-nao-encontrada.css',
})
export class PaginaNaoEncontrada {

}
