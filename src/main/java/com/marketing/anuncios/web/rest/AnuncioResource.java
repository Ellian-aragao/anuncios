package com.marketing.anuncios.web.rest;

import com.marketing.anuncios.domain.Anuncio;
import com.marketing.anuncios.domain.Cliente;
import com.marketing.anuncios.services.AnuncioService;
import com.marketing.anuncios.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/anuncio")
public class AnuncioResource {

    @Autowired
    private AnuncioService service;

    @GetMapping
    public ResponseEntity<List<Anuncio>> getAllAnuncios() {
        return ResponseEntity.ok(service.getAllAnuncios());
    }

    @PostMapping
    public ResponseEntity<Anuncio> createAnuncio(@RequestBody Anuncio anuncio) {
        anuncio.setId(null);
        return ResponseEntity
                .status(CREATED)
                .body(service.saveAnuncio(anuncio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnuncio(@PathVariable Long id, @RequestBody Anuncio anuncio) {
        return service
                .updateAnuncio(id, anuncio)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anuncio> getAnuncioById(@PathVariable Long id) {
        return ResponseEntity.of(service.getAnuncioById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id) {
        service.deleteAnuncioById(id);
        return ResponseEntity.ok().build();
    }
}
