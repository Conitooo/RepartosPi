package com.ejercicios.repartospi.producto;

import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product producto);
    Product toEntity(ProductDto productoDto);
    List<ProductDto> toDtoList(List<Product> productos);
}