package br.com.eurofarma.lactare_api.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AgendamentoRequest {

    @NotNull(message = "Data é obrigatória")
    @FutureOrPresent(message = "A data não pode ser anterior à data atual")
    private LocalDate data;

    @NotNull(message = "Horário é obrigatório")
    private LocalTime horario;

    @NotNull(message = "ID da nutriz é obrigatório")
    @Positive(message = "ID da nutriz deve ser positivo")
    private Long nutrizId;

    @NotNull(message = "ID do banco de leite é obrigatório")
    @Positive(message = "ID do banco de leite deve ser positivo")
    private Long bancoLeiteId;
}
