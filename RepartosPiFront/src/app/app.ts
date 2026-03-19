import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Nav } from "./componentes/nav/nav";
import { Footer } from "./componentes/footer/footer";
import { Main } from "./componentes/main/main";
import { LoginPageComponent } from "./componentes/login-page/login-page";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Nav, Footer, Main, LoginPageComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('hola');
}
