package br.com.eurofarma.lactare_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "2026-09-15")
    private LocalDate data;

    @NotNull(message = "Horário é obrigatório")
    @Schema(example = "14:30:00")
    private LocalTime horario;

    @NotNull(message = "ID da nutriz é obrigatório")
    @Positive(message = "ID da nutriz deve ser positivo")
    @Schema(example = "1")
    private Long nutrizId;

    @NotNull(message = "ID do banco de leite é obrigatório")
    @Positive(message = "ID do banco de leite deve ser positivo")
    @Schema(example = "1")
    private Long bancoLeiteId;
}
