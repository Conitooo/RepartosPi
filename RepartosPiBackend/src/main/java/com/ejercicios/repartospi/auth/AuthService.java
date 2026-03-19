package com.ejercicios.repartospi.auth;

import com.ejercicios.repartospi.Users.UserDto;

public interface AuthService {

    UserDto register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}