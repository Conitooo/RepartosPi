package com.ejercicios.repartospi.Users;

import com.ejercicios.repartospi.pedido.Pedido;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Data
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Pedido> pedidos;
}
