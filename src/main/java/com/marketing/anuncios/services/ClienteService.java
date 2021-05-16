package com.marketing.anuncios.services;

import com.marketing.anuncios.domain.Cliente;
import com.marketing.anuncios.repository.ClienteRepository;
import com.marketing.anuncios.services.dto.ClienteDto;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.marketing.anuncios.services.dto.ClienteDto.clienteToDto;
import static com.marketing.anuncios.services.dto.ClienteDto.dtoToCliente;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<ClienteDto> getAllClientes() {
        return clienteToDto(repository.findAll());
    }

    public Optional<ClienteDto> getClienteById(@NonNull Long id) {
        return repository.findById(id).map(ClienteDto::clienteToDto);
    }

    public ClienteDto saveCliente(@NonNull ClienteDto cliente) {
        return clienteToDto(repository.save(dtoToCliente(cliente)));
    }

    public Optional<Cliente> updateCliente(@NonNull Long id, @NonNull Cliente cliente) {
        return repository.findById(id).map((c) -> {
            cliente.setId(id);
            return repository.save(cliente);
        });
    }

    public void deleteClienteById(@NonNull Long id) {
        repository.deleteById(id);
    }
}
