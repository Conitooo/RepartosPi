package com.ejercicios.repartospi.producto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDto> findByName(String name) {
        List<Product> products = productRepository.findByNombreContainingIgnoreCase(name);
        return productMapper.toDtoList(products);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public void deleteById(Long id) {
        boolean exists = productRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }

        productRepository.deleteById(id);
    }
}