package com.ejercicios.repartospi.lineapedido;

import org.springframework.transaction.annotation.Transactional;

public interface LineaPedidoRepository  extends org.springframework.data.jpa.repository.JpaRepository<LineaPedido, Long> {

    @Transactional
    void deleteByPedidoId(Long pedidoId);
}
