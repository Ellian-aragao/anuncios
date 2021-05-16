package com.marketing.anuncios.web.rest;

import com.marketing.anuncios.domain.Anuncio;
import com.marketing.anuncios.services.AnuncioService;
import com.marketing.anuncios.services.RelatorioService;
import com.marketing.anuncios.services.dto.RelatorioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/relatorio")
public class RelatorioResource {

    @Autowired
    private RelatorioService service;

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioDto> getAnuncioById(@PathVariable Long id) {
        return ResponseEntity.of(service.getRelatorioFromAnuncioId(id));
    }
}
