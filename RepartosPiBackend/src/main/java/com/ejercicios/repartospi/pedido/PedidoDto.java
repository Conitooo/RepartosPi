package com.ejercicios.repartospi.pedido;

import com.ejercicios.repartospi.lineapedido.LineaPedidoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDto {

    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();

    @NotBlank
    private String descripcion;

    @NotBlank
    private String direccionEntrega;

    @NotBlank
    private String estado; // idealmente enum

    @NotNull
    private Long usuarioId;

    @NotEmpty
    @Valid
    private List<LineaPedidoDto> lineas;
}