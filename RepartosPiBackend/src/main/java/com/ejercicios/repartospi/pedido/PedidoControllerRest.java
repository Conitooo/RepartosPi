package com.ejercicios.repartospi.pedido;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos")
public class PedidoControllerRest {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDto>> findAll(@RequestParam(required = false) String nombre) {
        return ResponseEntity.ok(pedidoService.findByNombre(nombre != null ? nombre : ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoDto> save(@Valid @RequestBody PedidoDto pedidoDto) {
        PedidoDto pedidoCreado = pedidoService.save(pedidoDto);
        return ResponseEntity
                .created(URI.create("/api/pedidos/" + pedidoCreado.getId()))
                .body(pedidoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> update(@PathVariable Long id, @Valid @RequestBody PedidoDto dto) {
        dto.setId(id);
        return ResponseEntity.ok(pedidoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}