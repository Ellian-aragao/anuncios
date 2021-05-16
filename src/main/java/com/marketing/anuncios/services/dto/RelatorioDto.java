package com.marketing.anuncios.services.dto;

import com.marketing.anuncios.domain.Anuncio;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RelatorioDto {
    private Anuncio anuncio;
    private BigDecimal valorTotalInvestido;
    private BigInteger maxVisualizacoes;
    private BigInteger maxCliques;
    private BigInteger maxCompartilhamentos;

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public RelatorioDto setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
        return this;
    }

    public BigDecimal getValorTotalInvestido() {
        return valorTotalInvestido;
    }

    public RelatorioDto setValorTotalInvestido(BigDecimal valorTotalInvestido) {
        this.valorTotalInvestido = valorTotalInvestido;
        return this;
    }

    public BigInteger getMaxVisualizacoes() {
        return maxVisualizacoes;
    }

    public RelatorioDto setMaxVisualizacoes(BigInteger maxVisualizacoes) {
        this.maxVisualizacoes = maxVisualizacoes;
        return this;
    }

    public BigInteger getMaxCliques() {
        return maxCliques;
    }

    public RelatorioDto setMaxCliques(BigInteger maxCliques) {
        this.maxCliques = maxCliques;
        return this;
    }

    public BigInteger getMaxCompartilhamentos() {
        return maxCompartilhamentos;
    }

    public RelatorioDto setMaxCompartilhamentos(BigInteger maxCompartilhamentos) {
        this.maxCompartilhamentos = maxCompartilhamentos;
        return this;
    }
}
