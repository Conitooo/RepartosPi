package com.ejercicios.repartospi.pedido;

import com.ejercicios.repartospi.lineapedido.LineaPedidoMapper;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = { LineaPedidoMapper.class })
public interface PedidoMapper {

    @Mapping(target = "usuarioId", source = "id")
    PedidoDto toDto(Pedido entity);

    List<PedidoDto> toDtoList(List<Pedido> entities);

    default Page<PedidoDto> toDtoPage(Page<Pedido> page) {
        return page.map(this::toDto);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "lineas", ignore = true)
    Pedido toEntity(PedidoDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "lineas", ignore = true)
    void updateEntityFromDto(PedidoDto dto, @MappingTarget Pedido entity);
}