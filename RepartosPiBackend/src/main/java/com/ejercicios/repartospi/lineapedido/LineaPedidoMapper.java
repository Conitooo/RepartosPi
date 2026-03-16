package com.ejercicios.repartospi.lineapedido;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LineaPedidoMapper {

    @Mapping(target = "pedidoId", source = "pedido.id")
    @Mapping(target = "productoId", source = "product.id")
    @Mapping(target = "precioUnitarioCentimos", source = "precioCentimos")
    LineaPedidoDto toDto(LineaPedido entity);

    List<LineaPedidoDto> toDtoList(List<LineaPedido> entities);
}