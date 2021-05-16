package com.marketing.anuncios.web.rest;

import com.marketing.anuncios.domain.Cliente;
import com.marketing.anuncios.services.ClienteService;
import com.marketing.anuncios.services.dto.ClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        return ResponseEntity.ok(service.getAllClientes());
    }

    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto cliente) {
        cliente.setId(null);
        return ResponseEntity
                .status(CREATED)
                .body(service.saveCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return service
                .updateCliente(id, cliente)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        return ResponseEntity.of(service.getClienteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id) {
        service.deleteClienteById(id);
        return ResponseEntity.ok().build();
    }
}
