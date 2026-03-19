import { Component } from '@angular/core';

@Component({
  selector: 'app-nav',
  imports: [],
  standalone: true,
  templateUrl: './nav.html',
  styleUrl: './nav.css',
})
export class Nav {

  saludo() {
    alert("¡Hola! Bienvenido a tu perfil.");
  }
}
