package br.com.eurofarma.lactare_api.service;

import br.com.eurofarma.lactare_api.dto.request.AgendamentoRequest;
import br.com.eurofarma.lactare_api.dto.response.AgendamentoResponse;
import br.com.eurofarma.lactare_api.entities.Agendamento;
import br.com.eurofarma.lactare_api.entities.BancoLeite;
import br.com.eurofarma.lactare_api.entities.Nutriz;
import br.com.eurofarma.lactare_api.entities.Status;
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
    public AgendamentoResponse saveAgendamento(AgendamentoRequest request) {

        Agendamento agendamento = new Agendamento();
        copyDtoToAgendamento(request, agendamento);
        agendamento.setStatus(Status.PENDENTE);
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
    public AgendamentoResponse updateStatus(Long id, Status status) {

        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id));

        Status statusAtual = agendamento.getStatus();
        validarTransicao(statusAtual, status);
        agendamento.setStatus(status);
        Agendamento update = agendamentoRepository.save(agendamento);
        return new AgendamentoResponse(update);
    }

    private void validarTransicao(Status atual, Status novo) {

        if (atual == Status.PENDENTE) {

            if (novo != Status.CONFIRMADO && novo != Status.CANCELADO) {
                throw new IllegalStateException("Um agendamento pendente só pode ser confirmado ou cancelado");
            }

        } else if (atual == Status.CONFIRMADO) {

            if (novo != Status.CONCLUIDO && novo != Status.NAO_COMPARECEU && novo != Status.CANCELADO) {

                throw new IllegalStateException(
                        "Um agendamento confirmado só pode ser concluído, cancelado ou marcado como não compareceu"
                );
            }

        } else {
            throw new IllegalStateException("Não é possível alterar o status de um agendamento finalizado");
        }
    }

    private void copyDtoToAgendamento(AgendamentoRequest request, Agendamento agendamento) {

        agendamento.setData(request.getData());
        agendamento.setHorario(request.getHorario());

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
