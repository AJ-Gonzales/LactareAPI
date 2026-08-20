package br.com.eurofarma.lactare_api.dto.response;

import br.com.eurofarma.lactare_api.entities.BancoLeite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BancoLeiteResponse {

    private Long id;
    private String nome;
    private String endereco;
    private String cep;
    private String telefone;

    public BancoLeiteResponse(BancoLeite bancoLeite) {
        this.id = bancoLeite.getId();
        this.nome = bancoLeite.getNome();
        this.endereco = bancoLeite.getEndereco();
        this.cep = bancoLeite.getCep();
        this.telefone = bancoLeite.getTelefone();
    }
}
