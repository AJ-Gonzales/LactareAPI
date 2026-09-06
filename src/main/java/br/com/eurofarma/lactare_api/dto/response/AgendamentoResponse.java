package br.com.eurofarma.lactare_api.dto.response;

import br.com.eurofarma.lactare_api.entities.Agendamento;
import br.com.eurofarma.lactare_api.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AgendamentoResponse {

    private Long id;
    private LocalDate data;
    private String horario;
    private Long nutrizId;
    private Long bancoLeiteId;
    private Status status;

    public AgendamentoResponse(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.data = agendamento.getData();
        this.horario = agendamento.getHorario();
        this.nutrizId = agendamento.getNutriz().getId();
        this.bancoLeiteId = agendamento.getBancoLeite().getId();
        this.status = agendamento.getStatus();
    }
}
