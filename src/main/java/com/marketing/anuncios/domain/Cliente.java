package com.marketing.anuncios.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "pk_id")
    private Long id;

    @Column(name = "nm_nome")
    private String nome;

    @OneToMany
    private List<Anuncio> anuncios;

    public Cliente() {}

    public Cliente(Long id, @NonNull String nome) {
        this.id = id;
        this.nome = nome;
    }
}
