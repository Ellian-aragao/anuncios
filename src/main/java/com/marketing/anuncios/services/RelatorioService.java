package com.marketing.anuncios.services;

import com.marketing.anuncios.services.dto.RelatorioDto;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class RelatorioService {

    private static final BigDecimal CLICK_POR_VISUALIZACAO = BigDecimal.valueOf(0.12);
    private static final BigDecimal COMPARTILHAMENTO_POR_CLICK = BigDecimal.valueOf(0.15);
    private static final BigDecimal VISUALIZACAO_POR_COMPARTILHAMENTO = BigDecimal.valueOf(40);
    private static final BigDecimal VISULIZACAO_ORIGINAL_POR_INVESTIMENTO = BigDecimal.valueOf(30);
    private static final int COMPARTILHAMENTO_EM_SEQUENCIA = 4;

    @Autowired
    private AnuncioService anuncioService;

    public Optional<RelatorioDto> getRelatorioFromAnuncioId(@NonNull Long id) {
        return anuncioService.getAnuncioById(id).map(anuncio -> {
            final var totalDiasInvestidos = totalDeDiasInvestidos(anuncio.getInicio(), anuncio.getTermino());
            final var totalInvestido = valorTotalInvestido(totalDiasInvestidos, anuncio.getInvestimentoDiario());
            final var totalVisualizacoes = maxVisualizacoes(totalInvestido, totalDiasInvestidos);
            return new RelatorioDto()
                    .setAnuncio(anuncio)
                    .setValorTotalInvestido(totalInvestido)
                    .setMaxVisualizacoes(totalVisualizacoes)
                    .setMaxCliques(maxCliques(new BigDecimal(totalVisualizacoes), totalInvestido))
                    .setMaxCompartilhamentos(maxCompartilhamentos(new BigDecimal(totalVisualizacoes), totalDiasInvestidos));
        });
    }

    private BigDecimal totalDeDiasInvestidos(@NonNull Date dataInicial, @NonNull Date dataFinal) {
        return BigDecimal.valueOf(ChronoUnit.DAYS.between(dataInicial.toInstant(), dataFinal.toInstant()));
    }

    private BigDecimal valorTotalInvestido(
            @NonNull BigDecimal investimentoDiario,
            @NonNull BigDecimal totalDeDiasInvestidos
    ) {
        return investimentoDiario.multiply(totalDeDiasInvestidos);
    }

    private BigDecimal visualizacoesOriginais(@NonNull BigDecimal valorInvestido) {
        return valorInvestido.multiply(VISULIZACAO_ORIGINAL_POR_INVESTIMENTO);
    }

    private BigDecimal cliquesPorVisualizacao(@NonNull BigDecimal visualizacoes) {
        return visualizacoes.multiply(CLICK_POR_VISUALIZACAO);
    }

    private BigDecimal visualizacoesPorCompartilhamentos(@NonNull BigDecimal cliques) {
        return cliques.multiply(VISUALIZACAO_POR_COMPARTILHAMENTO);
    }

    private BigDecimal visualizacoesPorCompartilhamentoDosCliquesDasVisualizacoes(@NonNull BigDecimal visualizacao) {
        return visualizacoesPorCompartilhamentos(cliquesPorVisualizacao(visualizacao));
    }

    private BigDecimal maxVisualizacoesDiarias(@NonNull BigDecimal valorInvestido) {
        var visualizacao = visualizacoesOriginais(valorInvestido);
        for (int i = 1; i < COMPARTILHAMENTO_EM_SEQUENCIA; i++) {
            visualizacao = visualizacao.add(visualizacoesPorCompartilhamentoDosCliquesDasVisualizacoes(visualizacao));
        }
        return visualizacao;
    }

    private BigInteger maxVisualizacoes(@NonNull BigDecimal valorInvestidoDiario, @NonNull BigDecimal diasInvestidos) {
        return maxVisualizacoesDiarias(valorInvestidoDiario).multiply(diasInvestidos).toBigInteger();
    }

    private BigInteger maxCliques(@NonNull BigDecimal visualizacoes, @NonNull BigDecimal diasInvestidos) {
        return cliquesPorVisualizacao(visualizacoes).multiply(diasInvestidos).toBigInteger();
    }

    private BigInteger maxCompartilhamentos(@NonNull BigDecimal maxVisualizacoes, @NonNull BigDecimal diasInvestidos) {
        return maxVisualizacoes.multiply(diasInvestidos).toBigInteger();
    }
}
