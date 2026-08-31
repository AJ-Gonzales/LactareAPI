package br.com.eurofarma.lactare_api.controller;

import br.com.eurofarma.lactare_api.dto.request.AgendamentoRequest;
import br.com.eurofarma.lactare_api.dto.response.AgendamentoResponse;
import br.com.eurofarma.lactare_api.entities.Status;
import br.com.eurofarma.lactare_api.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> getAllAgendamentos(){

        List<AgendamentoResponse> agendamentos = agendamentoService.findAllAgendamentos();

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> getAgendamentoById(@PathVariable Long id){

        AgendamentoResponse agendamento = agendamentoService.findAgendamentoById(id);

        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponse> createAgendamento(@Valid @RequestBody AgendamentoRequest request){

        AgendamentoResponse agendamento = agendamentoService.saveAgendamento(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(agendamento.getId())
                .toUri();

        return ResponseEntity.created(uri).body(agendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> updateAgendamento(@PathVariable Long id,
                                                                 @Valid @RequestBody AgendamentoRequest request){

        AgendamentoResponse agendamento = agendamentoService.updateAgendamento(id,request);

        return  ResponseEntity.ok(agendamento);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AgendamentoResponse> updateStatus(@PathVariable Long id,
                                                            @RequestParam Status status) {

        AgendamentoResponse agendamento = agendamentoService.updateStatus(id, status);
        return ResponseEntity.ok(agendamento);
    }
    
}
