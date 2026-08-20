package br.com.eurofarma.lactare_api.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PesquisaSatisfacaoRequest {

    @NotNull(message = "Nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    private Integer nota;

    private String comentario;

    @NotNull(message = "ID da nutriz é obrigatório")
    @Positive(message = "ID da nutriz deve ser positivo")
    private Long nutrizId;

    @NotNull(message = "ID da doação é obrigatório")
    @Positive(message = "ID da doação deve ser positivo")
    private Long doacaoId;
}
