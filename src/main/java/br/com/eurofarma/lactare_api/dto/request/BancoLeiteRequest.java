package br.com.eurofarma.lactare_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BancoLeiteRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 150, message = "Nome deve ter entre 3 e 150 caracteres")
    @Schema(example = "Banco de Leite Avenida Central")
    private String nome;

    @NotBlank(message = "Endereço é obrigatório")
    @Schema(example = "Av. Paulista, 1000")
    private String endereco;

    @NotBlank(message = "CEP é obrigatório")
    @Schema(example = "01310100")
    private String cep;

    @NotBlank(message = "Telefone é obrigatório")
    @Schema(example = "1133334444")
    private String telefone;
}