package br.com.eurofarma.lactare_api.service;

import br.com.eurofarma.lactare_api.dto.request.DoacaoRequest;
import br.com.eurofarma.lactare_api.dto.response.DoacaoResponse;
import br.com.eurofarma.lactare_api.entities.Agendamento;
import br.com.eurofarma.lactare_api.entities.BancoLeite;
import br.com.eurofarma.lactare_api.entities.Doacao;
import br.com.eurofarma.lactare_api.entities.Nutriz;
import br.com.eurofarma.lactare_api.exceptions.DatabaseException;
import br.com.eurofarma.lactare_api.exceptions.ResourceNotFoundException;
import br.com.eurofarma.lactare_api.repository.AgendamentoRepository;
import br.com.eurofarma.lactare_api.repository.BancoLeiteRepository;
import br.com.eurofarma.lactare_api.repository.DoacaoRepository;
import br.com.eurofarma.lactare_api.repository.NutrizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private NutrizRepository nutrizRepository;

    @Autowired
    private BancoLeiteRepository bancoLeiteRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional(readOnly = true)
    public List<DoacaoResponse> findAllDoacoes(){

        List<Doacao> doacoes = doacaoRepository.findAll();

        return doacoes.stream().map(DoacaoResponse::new).toList();
    }

    @Transactional(readOnly = true)
    public DoacaoResponse findDoacaoById(Long id){

        Doacao doacao = doacaoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: ")
        );

        return new DoacaoResponse(doacao);
    }

    @Transactional
    public DoacaoResponse saveDoacao(DoacaoRequest request){

        Doacao doacao = new Doacao();
        copyDtoToDoacao(request,doacao);
        Doacao save = doacaoRepository.save(doacao);

        return new DoacaoResponse(save);
    }

    @Transactional
    public DoacaoResponse updateDoacao(Long id, DoacaoRequest request){

        Doacao doacao = doacaoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: "+id)
        );

        copyDtoToDoacao(request,doacao);
        Doacao update = doacaoRepository.save(doacao);

        return new DoacaoResponse(update);
    }

    @Transactional
    public void deleteDoacao(Long id){

        if (!doacaoRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: "+id);
        }

        doacaoRepository.deleteById(id);
    }

    private void copyDtoToDoacao(DoacaoRequest request, Doacao doacao){

        doacao.setData(request.getData());
        doacao.setQuantidade(request.getQuantidade());

        Nutriz nutriz = nutrizRepository.findById(request.getNutrizId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Doação. Nutriz inexistente. "
                        + "(ID: "+ request.getNutrizId()+ ")")
        );

        BancoLeite banco = bancoLeiteRepository.findById(request.getBancoLeiteId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Doação. Banco de Leite inexistente. "
                        + "(ID: "+ request.getBancoLeiteId()+ ")")
        );

        Agendamento agendamento = agendamentoRepository.findById(request.getAgendamentoId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Doação. Agendamento inexistente. "
                        + "(ID: "+ request.getAgendamentoId()+ ")")
        );

        doacao.setNutriz(nutriz);
        doacao.setBancoLeite(banco);
        doacao.setAgendamento(agendamento);

    }

}
