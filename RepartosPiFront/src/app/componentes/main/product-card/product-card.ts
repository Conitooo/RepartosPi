import { Component, input } from '@angular/core';
import { FetchedProduct } from '../../types/fetchedPorduct';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [],
  templateUrl: './product-card.html',
  styleUrl: './product-card.css',
})
export class ProductCard {
  producto = input.required<FetchedProduct>();
  
}