import { Component, inject, signal } from '@angular/core';
import { ProductCard } from './product-card/product-card';
import { ProductoService } from '../../services/producto-service';
import { FetchedProduct } from '../types/fetchedPorduct';
import { Nav } from '../nav/nav';
import { Footer } from "../footer/footer";
@Component({
  selector: 'app-main',
  standalone: true,
  imports: [ProductCard, Nav, Footer],
  templateUrl: './main.html',
  styleUrl: './main.css',
})
export class Main {
  private productoService = inject(ProductoService);

  productosObtenidos = signal<FetchedProduct[]>([]);

  constructor() {
    this.productoService.getProductos().then(productos => {
      this.productosObtenidos.set(productos);
    });
  }
}