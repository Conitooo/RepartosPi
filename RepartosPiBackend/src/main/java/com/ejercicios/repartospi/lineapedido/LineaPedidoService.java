package com.ejercicios.repartospi.lineapedido;


public interface LineaPedidoService {

    LineaPedidoDto createLineaPedido(LineaPedidoDto lineaPedidoDto);
    LineaPedidoDto getLineaPedidoById(Long id);
    LineaPedidoDto updateLineaPedido(Long id, LineaPedidoDto lineaPedidoDto);
    void deleteLineaPedido(Long id);
}
