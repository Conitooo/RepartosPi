package com.ejercicios.repartospi.lineapedido;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lineas-pedido")
@AllArgsConstructor
public class LineaPedidoControllerRest {

    private final LineaPedidoService lineaPedidoService;

    @PostMapping
    public ResponseEntity<LineaPedidoDto> createLineaPedido(@RequestBody @Valid LineaPedidoDto lineaPedidoDto) {
        LineaPedidoDto created = lineaPedidoService.createLineaPedido(lineaPedidoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineaPedidoDto> getLineaPedidoById(@PathVariable Long id) {
        LineaPedidoDto lineaPedido = lineaPedidoService.getLineaPedidoById(id);
        return ResponseEntity.ok(lineaPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LineaPedidoDto> updateLineaPedido(
            @PathVariable Long id,
            @RequestBody @Valid LineaPedidoDto lineaPedidoDto
    ) {
        LineaPedidoDto updated = lineaPedidoService.updateLineaPedido(id, lineaPedidoDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLineaPedido(@PathVariable Long id) {
        lineaPedidoService.deleteLineaPedido(id);
        return ResponseEntity.noContent().build();
    }
}