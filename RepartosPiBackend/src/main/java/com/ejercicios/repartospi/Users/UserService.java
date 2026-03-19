package com.ejercicios.repartospi.Users;

import com.ejercicios.repartospi.producto.ProductDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    List<ProductDto> getProductsByUserId(Long userId);
}