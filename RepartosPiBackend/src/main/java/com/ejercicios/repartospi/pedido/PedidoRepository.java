package com.ejercicios.repartospi.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByDescripcionContainingIgnoreCase(String descripcion);

}
