package com.ejercicios.repartospi.auth;

import com.ejercicios.repartospi.Users.UserDto;
import com.ejercicios.repartospi.Users.UserEntity;
import com.ejercicios.repartospi.Users.UserMapper;
import com.ejercicios.repartospi.Users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements com.ejercicios.repartospi.auth.AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Override
    public UserDto register(RegisterRequest request) {
        log.info("Registrando usuario con email={}", request.getEmail());
        boolean exists = userRepository.existsByEmail(request.getEmail());

        if (exists) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        UserEntity savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email o contraseña incorrectos"));

        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!matches) {
            throw new RuntimeException("Email o contraseña incorrectos");
        }

        return AuthResponse.builder()
                .token("sin-jwt-por-ahora")
                .user(userMapper.toDto(user))
                .build();
    }
}