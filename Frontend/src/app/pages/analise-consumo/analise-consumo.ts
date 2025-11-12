import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-analise-consumo',
  standalone: true,          
  imports: [RouterModule,CommonModule],
  templateUrl: './analise-consumo.html',
  styleUrl: './analise-consumo.css',
})
export class AnaliseConsumo {

}
