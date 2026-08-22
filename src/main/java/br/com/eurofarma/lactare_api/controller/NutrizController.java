package br.com.eurofarma.lactare_api.controller;

import br.com.eurofarma.lactare_api.dto.request.NutrizRequest;
import br.com.eurofarma.lactare_api.dto.response.NutrizResponse;
import br.com.eurofarma.lactare_api.service.NutrizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/nutrizes")
public class NutrizController {

    @Autowired
    private NutrizService nutrizService;


    @GetMapping
    public ResponseEntity<List<NutrizResponse>> getAllNutrizes(){

      List<NutrizResponse> nutrizes = nutrizService.findAllNutrizes();

      return ResponseEntity.ok(nutrizes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutrizResponse> getNutrizById(@PathVariable Long id){

        NutrizResponse nutriz = nutrizService.findNutrizById(id);

        return ResponseEntity.ok(nutriz);
    }

    @PostMapping
    public ResponseEntity<NutrizResponse> createNutriz(@Valid @RequestBody NutrizRequest request){

        NutrizResponse nutriz = nutrizService.saveNutriz(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(nutriz.getId())
                .toUri();

        return ResponseEntity.created(uri).body(nutriz);

    }

    @PutMapping("/{id}")
    public ResponseEntity<NutrizResponse> updateNutriz(@PathVariable Long id,
                                                       @Valid @RequestBody NutrizRequest request){

       NutrizResponse nutriz = nutrizService.updateNutriz(id, request);

       return ResponseEntity.ok(nutriz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutriz(@PathVariable Long id){

        nutrizService.deleteNutrizById(id);

        return ResponseEntity.noContent().build();
    }
}