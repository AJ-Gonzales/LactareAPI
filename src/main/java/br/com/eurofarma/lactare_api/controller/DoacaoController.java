package br.com.eurofarma.lactare_api.controller;

import br.com.eurofarma.lactare_api.dto.request.DoacaoRequest;
import br.com.eurofarma.lactare_api.dto.response.DoacaoResponse;
import br.com.eurofarma.lactare_api.service.DoacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doações")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @GetMapping
    public ResponseEntity<List<DoacaoResponse>> getAllDoacoes(){

        List<DoacaoResponse> doacoes = doacaoService.findAllDoacoes();

        return ResponseEntity.ok(doacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoacaoResponse> getDoacaoById(@PathVariable Long id){

        DoacaoResponse doacao = doacaoService.findDoacaoById(id);

        return ResponseEntity.ok(doacao);
    }

    @PostMapping
    public ResponseEntity<DoacaoResponse> createDoacao(@Valid @RequestBody DoacaoRequest request){

        DoacaoResponse doacao = doacaoService.saveDoacao(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(doacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(doacao);
    }

}
