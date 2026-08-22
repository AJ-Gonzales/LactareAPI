package br.com.eurofarma.lactare_api.service;

import br.com.eurofarma.lactare_api.dto.request.AgendamentoRequest;
import br.com.eurofarma.lactare_api.dto.response.AgendamentoResponse;
import br.com.eurofarma.lactare_api.entities.Agendamento;
import br.com.eurofarma.lactare_api.entities.BancoLeite;
import br.com.eurofarma.lactare_api.entities.Nutriz;
import br.com.eurofarma.lactare_api.exceptions.DatabaseException;
import br.com.eurofarma.lactare_api.exceptions.ResourceNotFoundException;
import br.com.eurofarma.lactare_api.repository.AgendamentoRepository;
import br.com.eurofarma.lactare_api.repository.BancoLeiteRepository;
import br.com.eurofarma.lactare_api.repository.NutrizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private NutrizRepository nutrizRepository;

    @Autowired
    private BancoLeiteRepository bancoLeiteRepository;


    @Transactional(readOnly = true)
    public List<AgendamentoResponse> findAllAgendamentos(){

        List<Agendamento> agendamentos = agendamentoRepository.findAll();

        return agendamentos.stream().map(AgendamentoResponse::new).toList();
    }

    @Transactional(readOnly = true)
    public AgendamentoResponse findAgendamentoById(Long id){

        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: "+id)
        );

        return new AgendamentoResponse(agendamento);
    }

    @Transactional
    public AgendamentoResponse saveAgendamento(AgendamentoRequest request){

        Agendamento agendamento = new Agendamento();
        copyDtoToAgendamento(request,agendamento);
        Agendamento save = agendamentoRepository.save(agendamento);

        return new AgendamentoResponse(save);
    }

    @Transactional
    public AgendamentoResponse updateAgendamento(Long id, AgendamentoRequest request){

        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: "+id)
        );

        copyDtoToAgendamento(request,agendamento);
        Agendamento update = agendamentoRepository.save(agendamento);

        return new AgendamentoResponse(update);
    }

    @Transactional
    public void deleteAgendamento(Long id){

        if (!agendamentoRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: "+id);
        }

        agendamentoRepository.deleteById(id);
    }

    private void copyDtoToAgendamento(AgendamentoRequest request, Agendamento agendamento) {

        agendamento.setData(request.getData());
        agendamento.setHorario(request.getHorario());
        agendamento.setStatus(request.getStatus());

        Nutriz nutriz = nutrizRepository.findById(request.getNutrizId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Agendamento. Nutriz inexistente. "
                        + "(ID: " + request.getNutrizId() + ")")
        );

        BancoLeite banco = bancoLeiteRepository.findById(request.getBancoLeiteId()).orElseThrow(
                () -> new DatabaseException("Não foi possível salvar Agendamento. Banco de Leite inexistente. "
                        + "(ID: " + request.getBancoLeiteId() + ")")
        );

        agendamento.setNutriz(nutriz);
        agendamento.setBancoLeite(banco);
    }

}
