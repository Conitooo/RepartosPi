package com.ejercicios.repartospi.producto;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends org.springframework.data.jpa.repository.JpaRepository<Product, Long> {

    List<Product> findByNombreContainingIgnoreCase(String nombre);
    @Query("""
    SELECT DISTINCT lp.product
    FROM LineaPedido lp
    WHERE lp.pedido.user.id = :userId
""")
    List<Product> findDistinctByUserId(@Param("userId") Long userId);
}
