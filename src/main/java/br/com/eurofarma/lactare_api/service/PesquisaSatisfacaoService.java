package br.com.eurofarma.lactare_api.service;

import br.com.eurofarma.lactare_api.dto.request.PesquisaSatisfacaoRequest;
import br.com.eurofarma.lactare_api.dto.response.PesquisaSatisfacaoResponse;
import br.com.eurofarma.lactare_api.entities.Doacao;
import br.com.eurofarma.lactare_api.entities.Nutriz;
import br.com.eurofarma.lactare_api.entities.PesquisaSatisfacao;
import br.com.eurofarma.lactare_api.exceptions.DatabaseException;
import br.com.eurofarma.lactare_api.exceptions.ResourceNotFoundException;
import br.com.eurofarma.lactare_api.repository.DoacaoRepository;
import br.com.eurofarma.lactare_api.repository.NutrizRepository;
import br.com.eurofarma.lactare_api.repository.PesquisaSatisfacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PesquisaSatisfacaoService {

    @Autowired
    private PesquisaSatisfacaoRepository pesquisaSatisfacaoRepository;

    @Autowired
    private NutrizRepository nutrizRepository;

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Transactional(readOnly = true)
    public List<PesquisaSatisfacaoResponse> findAllPesquisas(){

        List<PesquisaSatisfacao> pesquisas = pesquisaSatisfacaoRepository.findAll();

        return pesquisas.stream().map(PesquisaSatisfacaoResponse::new).toList();
    }

    @Transactional(readOnly = true)
    public PesquisaSatisfacaoResponse findPesquisaById(Long id){

        PesquisaSatisfacao pesquisa = pesquisaSatisfacaoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: "+id)
        );

        return new PesquisaSatisfacaoResponse(pesquisa);
    }

    @Transactional
    public PesquisaSatisfacaoResponse savePesquisa(PesquisaSatisfacaoRequest request) {

        if (request.getNota() <= 3 && (request.getComentario() == null || request.getComentario().isBlank())) {
            throw new IllegalArgumentException("O comentário é obrigatório para notas iguais ou menores que 3");
        }

        PesquisaSatisfacao pesquisa = new PesquisaSatisfacao();
        copyDtoToPesquisa(request, pesquisa);
        pesquisa = pesquisaSatisfacaoRepository.save(pesquisa);
        return new PesquisaSatisfacaoResponse(pesquisa);
    }

    private void copyDtoToPesquisa(PesquisaSatisfacaoRequest request,PesquisaSatisfacao pesquisa){

        pesquisa.setNota(request.getNota());
        pesquisa.setComentario(request.getComentario());
        pesquisa.setDataResposta(request.getDataResposta());

        Nutriz nutriz = nutrizRepository.findById(request.getNutrizId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Pesquisa. Nutriz inexistente. "
                        + "(ID: " + request.getNutrizId() + ")")
        );

        Doacao doacao = doacaoRepository.findById(request.getDoacaoId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Pesquisa. Doação inexistente. "
                        + "(ID: " + request.getDoacaoId() + ")")
        );

        pesquisa.setNutriz(nutriz);
        pesquisa.setDoacao(doacao);
    }

}
