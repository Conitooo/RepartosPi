package com.ejercicios.repartospi.pedido;

import com.ejercicios.repartospi.Users.UserEntity;
import com.ejercicios.repartospi.Users.UserRepository;
import com.ejercicios.repartospi.lineapedido.LineaPedido;
import com.ejercicios.repartospi.lineapedido.LineaPedidoDto;
import com.ejercicios.repartospi.lineapedido.LineaPedidoRepository;
import com.ejercicios.repartospi.producto.Product;
import com.ejercicios.repartospi.producto.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final UserRepository userRepository;
    private final LineaPedidoRepository lineaPedidoRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDto> findByNombre(String nombre) {
        String filtro = (nombre == null) ? "" : nombre.trim();
        return pedidoRepository.findByDescripcionContainingIgnoreCase(filtro)
                .stream()
                .map(pedidoMapper::toDto)
                .collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoDto findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        return pedidoMapper.toDto(pedido);
    }

    @Override
    @Transactional
    public PedidoDto save(PedidoDto pedidoDto) {
        if (pedidoDto == null) {
            throw new RuntimeException("El pedido no puede ser nulo");
        }

        if (pedidoDto.getLineas() == null || pedidoDto.getLineas().isEmpty()) {
            throw new RuntimeException("El pedido debe tener al menos una línea");
        }

        UserEntity usuario = userRepository.findById(pedidoDto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + pedidoDto.getUsuarioId()));

        // 1) Crear y guardar el pedido
        Pedido pedido = new Pedido();
        pedido.setDescripcion(pedidoDto.getDescripcion());
        pedido.setDireccionEntrega(pedidoDto.getDireccionEntrega());
        pedido.setEstado(pedidoDto.getEstado());
        pedido.setFecha(LocalDateTime.now());
        pedido.setUser(usuario);

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // 2) Crear las líneas usando los productoId que vienen en el DTO
        List<LineaPedido> lineas = new ArrayList<>();

        for (LineaPedidoDto lineaDto : pedidoDto.getLineas()) {
            if (lineaDto == null) {
                throw new RuntimeException("Existe una línea de pedido nula");
            }

            Product product = productRepository.findById(lineaDto.getProductoId())
                    .orElseThrow(() -> new RuntimeException(
                            "Producto no encontrado con id: " + lineaDto.getProductoId()
                    ));

            LineaPedido linea = new LineaPedido();
            linea.setPedido(pedidoGuardado); // FK pedido_id
            linea.setProduct(product);       // FK producto_id
            linea.setCantidad(lineaDto.getCantidad());

            // Precio snapshot (muy importante)
            linea.setPrecioCentimos(product.getPrecioCentimos());

            lineas.add(linea);
        }

        // 3) Guardar todas las líneas
        lineaPedidoRepository.saveAll(lineas);

        // Opcional: si tu Pedido tiene relación lineas y quieres devolverlas en DTO
        // pedidoGuardado.setLineas(lineas);

        return pedidoMapper.toDto(pedidoGuardado);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado con id: " + id);
        }

        // Importante si no tienes cascade/orphanRemoval en Pedido -> LineaPedido
        lineaPedidoRepository.deleteByPedidoId(id);

        pedidoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PedidoDto update(Long id, PedidoDto pedidoDto) {
        if (pedidoDto == null) {
            throw new RuntimeException("El pedido no puede ser nulo");
        }

        if (pedidoDto.getLineas() == null || pedidoDto.getLineas().isEmpty()) {
            throw new RuntimeException("El pedido debe tener al menos una línea");
        }

        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));

        UserEntity usuario = userRepository.findById(pedidoDto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + pedidoDto.getUsuarioId()));


        pedidoExistente.setDescripcion(pedidoDto.getDescripcion());
        pedidoExistente.setDireccionEntrega(pedidoDto.getDireccionEntrega());
        pedidoExistente.setEstado(pedidoDto.getEstado());
        pedidoExistente.setUser(usuario);

        Pedido pedidoActualizado = pedidoRepository.save(pedidoExistente);


        lineaPedidoRepository.deleteByPedidoId(id);

        List<LineaPedido> nuevasLineas = new ArrayList<>();

        for (LineaPedidoDto lineaDto : pedidoDto.getLineas()) {
            if (lineaDto == null) {
                throw new RuntimeException("Existe una línea de pedido nula");
            }

            Product product = productRepository.findById(lineaDto.getProductoId())
                    .orElseThrow(() -> new RuntimeException(
                            "Producto no encontrado con id: " + lineaDto.getProductoId()
                    ));

            LineaPedido linea = new LineaPedido();
            linea.setPedido(pedidoActualizado);
            linea.setProduct(product);
            linea.setCantidad(lineaDto.getCantidad());

            linea.setPrecioCentimos(product.getPrecioCentimos());

            nuevasLineas.add(linea);
        }

        lineaPedidoRepository.saveAll(nuevasLineas);



        return pedidoMapper.toDto(pedidoActualizado);
    }
}