package br.com.eurofarma.lactare_api.dto.response;

import br.com.eurofarma.lactare_api.entities.Nutriz;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NutrizResponse {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String endereco;
    private String cep;

    public NutrizResponse(Nutriz nutriz) {
        this.id = nutriz.getId();
        this.nome = nutriz.getNome();
        this.cpf = nutriz.getCpf();
        this.telefone = nutriz.getTelefone();
        this.email = nutriz.getEmail();
        this.dataNascimento = nutriz.getDataNascimento();
        this.endereco = nutriz.getEndereco();
        this.cep = nutriz.getCep();
    }
}
