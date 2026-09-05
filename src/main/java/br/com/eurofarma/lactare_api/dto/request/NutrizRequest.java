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
public class NutrizRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    @Schema(example = "Maria Silva")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve possuir 11 caracteres")
    @Schema(example = "12345678901")
    private String cpf;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(min = 10,max = 11, message = "Telefone deve conter 10 ou 11 carateres")
    @Schema(example = "11999999999")
    private String telefone;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Schema(example = "maria@email.com")
    private String email;

    @NotBlank(message = "Endereço é obrigatório")
    @Schema(example = "Rua das Flores, 100")
    private String endereco;

    @NotBlank(message = "CEP é obrigatório")
    @Size(min = 8,max = 8, message = "CEP deve possuir 8 carateres")
    @Schema(example = "01310100")
    private String cep;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve estar no passado")
    @Schema(example = "1998-05-10")
    private LocalDate dataNascimento;

}
