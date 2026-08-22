package br.com.eurofarma.lactare_api.controller;

import br.com.eurofarma.lactare_api.dto.request.PesquisaSatisfacaoRequest;
import br.com.eurofarma.lactare_api.dto.response.PesquisaSatisfacaoResponse;
import br.com.eurofarma.lactare_api.service.PesquisaSatisfacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pesquisas")
public class PesquisaSatisfacaoController {

    @Autowired
    private PesquisaSatisfacaoService pesquisaSatisfacaoService;

    @GetMapping
    public ResponseEntity<List<PesquisaSatisfacaoResponse>> getAllPesquisas(){

        List<PesquisaSatisfacaoResponse> pesquisas = pesquisaSatisfacaoService.findAllPesquisas();

        return ResponseEntity.ok(pesquisas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PesquisaSatisfacaoResponse> getPesquisaById(@PathVariable Long id){

        PesquisaSatisfacaoResponse pesquisa = pesquisaSatisfacaoService.findPesquisaById(id);

        return ResponseEntity.ok(pesquisa);
    }

    @PostMapping
    public ResponseEntity<PesquisaSatisfacaoResponse> createPesquisa(@Valid @RequestBody
                                                                         PesquisaSatisfacaoRequest request){

        PesquisaSatisfacaoResponse pesquisa = pesquisaSatisfacaoService.savePesquisa(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pesquisa.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pesquisa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PesquisaSatisfacaoResponse> updatePesquisa(@PathVariable Long id,
                                                                     @Valid @RequestBody PesquisaSatisfacaoRequest request){

        PesquisaSatisfacaoResponse pesquisa = pesquisaSatisfacaoService.updatePesquisa(id,request);

        return ResponseEntity.ok(pesquisa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePesquisa(@PathVariable Long id){

        pesquisaSatisfacaoService.deletePesquisa(id);

        return ResponseEntity.noContent().build();
    }
}
