package com.ejercicios.repartospi.pedido;

import java.util.List;

public interface PedidoService {
    List<PedidoDto> findByNombre(String nombre);
    PedidoDto findById(Long id);
    PedidoDto save(PedidoDto pedidoDto);
    void deleteById(Long id);
    PedidoDto update(Long id, PedidoDto pedidoDto);
}
