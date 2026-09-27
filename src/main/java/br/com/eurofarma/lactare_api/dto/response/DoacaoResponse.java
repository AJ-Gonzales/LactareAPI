package br.com.eurofarma.lactare_api.dto.response;

import br.com.eurofarma.lactare_api.entities.Doacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DoacaoResponse {

    private Long id;
    private LocalDate data;
    private BigDecimal quantidade;
    private Long nutrizId;
    private String nutrizNome;
    private Long bancoLeiteId;
    private String bancoLeiteNome;
    private Long agendamentoId;

    public DoacaoResponse(Doacao doacao) {
        this.id = doacao.getId();
        this.data = doacao.getData();
        this.quantidade = doacao.getQuantidade();
        this.nutrizId = doacao.getNutriz().getId();
        this.nutrizNome = doacao.getNutriz().getNome();
        this.bancoLeiteId = doacao.getBancoLeite().getId();
        this.bancoLeiteNome = doacao.getBancoLeite().getNome();
        this.agendamentoId = doacao.getAgendamento().getId();
    }
}
