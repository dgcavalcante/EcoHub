import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,              // 👈 ESSA LINHA FAZ TODA A DIFERENÇA
  imports: [RouterModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

}
