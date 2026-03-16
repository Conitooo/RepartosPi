package com.ejercicios.repartospi.lineapedido;

import com.ejercicios.repartospi.pedido.Pedido;
import com.ejercicios.repartospi.pedido.PedidoRepository;
import com.ejercicios.repartospi.producto.Product;
import com.ejercicios.repartospi.producto.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class LineaPedidoServiceImpl implements LineaPedidoService {

    private final LineaPedidoRepository lineaPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductRepository productRepository;
    private final LineaPedidoMapper lineaPedidoMapper;

    @Override
    public LineaPedidoDto createLineaPedido(LineaPedidoDto lineaPedidoDto) {
        Pedido pedido = pedidoRepository.findById(lineaPedidoDto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + lineaPedidoDto.getPedidoId()));

        Product product = productRepository.findById(lineaPedidoDto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + lineaPedidoDto.getProductoId()));

        LineaPedido lineaPedido = new LineaPedido();
        lineaPedido.setPedido(pedido);
        lineaPedido.setProduct(product);
        lineaPedido.setCantidad(lineaPedidoDto.getCantidad());

        lineaPedido.setPrecioCentimos(product.getPrecioCentimos());

        LineaPedido saved = lineaPedidoRepository.save(lineaPedido);
        return lineaPedidoMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public LineaPedidoDto getLineaPedidoById(Long id) {
        LineaPedido lineaPedido = lineaPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Línea de pedido no encontrada con id: " + id));

        return lineaPedidoMapper.toDto(lineaPedido);
    }

    @Override
    public LineaPedidoDto updateLineaPedido(Long id, LineaPedidoDto lineaPedidoDto) {
        LineaPedido lineaPedido = lineaPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Línea de pedido no encontrada con id: " + id));

        if (lineaPedidoDto.getProductoId() != null) {
            Product product = productRepository.findById(lineaPedidoDto.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + lineaPedidoDto.getProductoId()));
            lineaPedido.setProduct(product);
            lineaPedido.setPrecioCentimos(product.getPrecioCentimos());
        }

        if (lineaPedidoDto.getPedidoId() != null) {
            Pedido pedido = pedidoRepository.findById(lineaPedidoDto.getPedidoId())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + lineaPedidoDto.getPedidoId()));
            lineaPedido.setPedido(pedido);
        }

        if (lineaPedidoDto.getCantidad() != null) {
            lineaPedido.setCantidad(lineaPedidoDto.getCantidad());
        }

        LineaPedido updated = lineaPedidoRepository.save(lineaPedido);
        return lineaPedidoMapper.toDto(updated);
    }

    @Override
    public void deleteLineaPedido(Long id) {
        if (!lineaPedidoRepository.existsById(id)) {
            throw new RuntimeException("Línea de pedido no encontrada con id: " + id);
        }

        lineaPedidoRepository.deleteById(id);
    }
}