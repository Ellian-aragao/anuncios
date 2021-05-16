package com.marketing.anuncios.services.dto;

import com.marketing.anuncios.domain.Cliente;
import lombok.Data;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Data
public class ClienteDto {
    private Long id;
    private String nome;

    public ClienteDto(@NonNull Long id,@NonNull String nome) {
        this.id = id;
        this.nome = (nome);
    }

    public static List<ClienteDto> clienteToDto(@NonNull Collection<Cliente> clientes) {
        return clientes.stream()
                .map(cliente -> new ClienteDto(cliente.getId(), cliente.getNome()))
                .collect(toUnmodifiableList());
    }

    public static ClienteDto clienteToDto(@NonNull Cliente cliente) {
        return new ClienteDto(cliente.getId(), cliente.getNome());
    }

    public static Cliente dtoToCliente(@NonNull ClienteDto cliente) {
        return new Cliente(cliente.getId(), cliente.getNome());
    }
}
