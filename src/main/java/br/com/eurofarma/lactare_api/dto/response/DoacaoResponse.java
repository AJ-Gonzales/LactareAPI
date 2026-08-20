package br.com.eurofarma.lactare_api.dto.response;

import br.com.eurofarma.lactare_api.entities.Doacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DoacaoResponse {

    private Long id;
    private LocalDate data;
    private Double quantidade;
    private Long nutrizId;
    private Long bancoLeiteId;
    private Long agendamentoId;

    public DoacaoResponse(Doacao doacao) {
        this.id = doacao.getId();
        this.data = doacao.getData();
        this.quantidade = doacao.getQuantidade();
        this.nutrizId = doacao.getNutriz().getId();
        this.bancoLeiteId = doacao.getBancoLeite().getId();
        this.agendamentoId = doacao.getAgendamento().getId();
    }
}
