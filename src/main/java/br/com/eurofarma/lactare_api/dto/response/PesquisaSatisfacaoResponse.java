package br.com.eurofarma.lactare_api.dto.response;

import br.com.eurofarma.lactare_api.entities.PesquisaSatisfacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PesquisaSatisfacaoResponse {

    private Long id;
    private Integer nota;
    private String comentario;
    private LocalDate dataResposta;
    private Long nutrizId;
    private Long doacaoId;

    public PesquisaSatisfacaoResponse(PesquisaSatisfacao pesquisaSatisfacao) {
        this.id = pesquisaSatisfacao.getId();
        this.nota = pesquisaSatisfacao.getNota();
        this.comentario = pesquisaSatisfacao.getComentario();
        this.dataResposta = pesquisaSatisfacao.getDataResposta();
        this.nutrizId = pesquisaSatisfacao.getNutriz().getId();
        this.doacaoId = pesquisaSatisfacao.getDoacao().getId();
    }
}
