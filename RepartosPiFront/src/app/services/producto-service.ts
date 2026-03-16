import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  constructor() {}

getProductos(){
  return fetch('http://localhost:8080/api/products')
    .then(async (response) => {
      if (!response.ok) {
        console.error(`HTTP ${response.status} ${response.statusText}`);
        return [];
      }
      return await response.json();
    })
    .catch(error => {
      console.error('Error fetching productos:', error);
      return [];
    });
}
}
