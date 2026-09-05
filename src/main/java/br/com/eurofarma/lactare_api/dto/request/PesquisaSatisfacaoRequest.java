package br.com.eurofarma.lactare_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PesquisaSatisfacaoRequest {

    @NotNull(message = "Nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    @Schema(example = "1")
    private Integer nota;

    @Schema(example = "O atendimento demorou mais do que o esperado.")
    private String comentario;

    @NotNull(message = "Data de resposta é obrigatória")
    @PastOrPresent(message = "Data da resposta deve estar no presente ou passado")
    @Schema(example = "2026-08-28")
    private LocalDate dataResposta;

    @NotNull(message = "ID da nutriz é obrigatório")
    @Positive(message = "ID da nutriz deve ser positivo")
    @Schema(example = "1")
    private Long nutrizId;

    @NotNull(message = "ID da doação é obrigatório")
    @Positive(message = "ID da doação deve ser positivo")
    @Schema(example = "1")
    private Long doacaoId;
}
