package com.marketing.anuncios.services;

import com.marketing.anuncios.domain.Anuncio;
import com.marketing.anuncios.repository.AnuncioRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {
    @Autowired
    private AnuncioRepository repository;

    public List<Anuncio> getAllAnuncios() {
        return repository.findAll();
    }

    public Optional<Anuncio> getAnuncioById(@NonNull Long id) {
        return repository.findById(id);
    }

    public Anuncio saveAnuncio(@NonNull Anuncio anuncio) {
        return repository.save(anuncio);
    }

    public Optional<Anuncio> updateAnuncio(@NonNull Long id, @NonNull Anuncio anuncio) {
        return repository.findById(id).map((a) -> {
            anuncio.setId(id);
            return repository.save(anuncio);
        });
    }

    public void deleteAnuncioById(@NonNull Long id) {
        repository.deleteById(id);
    }
}
