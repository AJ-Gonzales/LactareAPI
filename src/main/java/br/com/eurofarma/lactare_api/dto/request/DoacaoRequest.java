package br.com.eurofarma.lactare_api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DoacaoRequest {

    @NotNull(message = "Data é obrigatória")
    @PastOrPresent(message = "A data não pode ser futura")
    private LocalDate data;

    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private BigDecimal quantidade;

    @NotNull(message = "ID da nutriz é obrigatório")
    @Positive(message = "ID da nutriz deve ser positivo")
    private Long nutrizId;

    @NotNull(message = "ID do banco de leite é obrigatório")
    @Positive(message = "ID do banco de leite deve ser positivo")
    private Long bancoLeiteId;

    @NotNull(message = "ID do agendamento é obrigatório")
    @Positive(message = "ID do agendamento deve ser positivo")
    private Long agendamentoId;
}