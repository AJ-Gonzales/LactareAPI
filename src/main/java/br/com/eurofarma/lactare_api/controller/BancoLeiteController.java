package br.com.eurofarma.lactare_api.controller;

import br.com.eurofarma.lactare_api.dto.request.BancoLeiteRequest;
import br.com.eurofarma.lactare_api.dto.response.BancoLeiteResponse;
import br.com.eurofarma.lactare_api.service.BancoLeiteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bancos-de-leite")
public class BancoLeiteController {

    @Autowired
     private BancoLeiteService bancoLeiteService;

    @GetMapping
    public ResponseEntity<List<BancoLeiteResponse>> getAllBAncos() {

        List<BancoLeiteResponse> bancos = bancoLeiteService.findAllBancos();

        return ResponseEntity.ok(bancos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoLeiteResponse> getBancoById(@PathVariable Long id){

        BancoLeiteResponse banco = bancoLeiteService.findBAncoById(id);

        return ResponseEntity.ok(banco);
    }

    @PostMapping
    public ResponseEntity<BancoLeiteResponse> createBanco(@Valid @RequestBody BancoLeiteRequest request){

        BancoLeiteResponse banco = bancoLeiteService.saveBanco(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(banco.getId())
                .toUri();

        return ResponseEntity.created(uri).body(banco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BancoLeiteResponse> updateBanco(@PathVariable Long id,
                                                          @Valid @RequestBody BancoLeiteRequest request){

        BancoLeiteResponse bando = bancoLeiteService.updateBanco(id,request);

        return ResponseEntity.ok(bando);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanco(@PathVariable Long id){

        bancoLeiteService.deleteBanco(id);

        return ResponseEntity.noContent().build();
    }
}
