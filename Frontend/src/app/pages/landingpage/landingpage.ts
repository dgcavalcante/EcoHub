import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-landingpage',
  standalone: true,              // 👈 ESSA LINHA FAZ TODA A DIFERENÇA
  imports: [RouterModule, CommonModule],
  templateUrl: './landingpage.html',
  styleUrl: './landingpage.css'
})
export class LandingPage {}
