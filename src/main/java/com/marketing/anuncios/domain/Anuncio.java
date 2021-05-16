package com.marketing.anuncios.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_anuncio")
public class Anuncio {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "pk_id")
    private Long id;

    @Column(name = "nm_nome")
    private String nome;

    @ManyToOne
    private Cliente cliente;

    @Column(name = "dt_inicio")
    private Date inicio;

    @Column(name = "dt_termino")
    private Date termino;

    @Column(name = "num_investimento_diario")
    private BigDecimal investimentoDiario;
}
