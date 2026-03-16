package com.ejercicios.repartospi.producto;

import java.util.List;

public interface ProductService {

   List<ProductDto> findAll();
   List<ProductDto> findByName(String name);
    ProductDto findById(Long id);
    ProductDto save(ProductDto productDto);
    void deleteById(Long id);


}
