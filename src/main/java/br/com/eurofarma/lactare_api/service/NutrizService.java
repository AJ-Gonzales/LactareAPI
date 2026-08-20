package br.com.eurofarma.lactare_api.service;

import br.com.eurofarma.lactare_api.dto.request.NutrizRequest;
import br.com.eurofarma.lactare_api.dto.response.NutrizResponse;
import br.com.eurofarma.lactare_api.entities.Nutriz;
import br.com.eurofarma.lactare_api.exceptions.ResourceNotFoundException;
import br.com.eurofarma.lactare_api.repository.NutrizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NutrizService {

    @Autowired
    private NutrizRepository nutrizRepository;

    @Transactional(readOnly = true)
    public List<NutrizResponse> findAllNutrizes(){

        List<Nutriz> produtos = nutrizRepository.findAll();

        return produtos.stream().map(NutrizResponse::new).toList();
    }

    @Transactional(readOnly = true)
    public NutrizResponse findNutrizById(Long id) {

        Nutriz nutriz = nutrizRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recurso não encontrado. ID: "));

        return new NutrizResponse(nutriz);
    }

    @Transactional
    public NutrizResponse createNutriz(NutrizRequest request) {

        Nutriz nutriz = new Nutriz();
        copyDtoToNutriz(request,nutriz);
        Nutriz save = nutrizRepository.save(nutriz);

        return new NutrizResponse(save);
    }

    @Transactional
    public NutrizResponse updateNutriz(Long id, NutrizRequest request) {

        Nutriz nutriz = nutrizRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("REcurso não encontrado. ID: "+id));

        copyDtoToNutriz(request,nutriz);
        Nutriz update = nutrizRepository.save(nutriz);

        return new NutrizResponse(update);
    }

    @Transactional
    public void deleteNutrizById(Long id) {

        if (!nutrizRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: ");
        }

        nutrizRepository.deleteById(id);
    }

    private void copyDtoToNutriz(NutrizRequest request, Nutriz nutriz) {

        nutriz.setNome(request.getNome());
        nutriz.setCpf(request.getCpf());
        nutriz.setTelefone(request.getTelefone());
        nutriz.setEmail(request.getEmail());
        nutriz.setDataNascimento(request.getDataNascimento());
        nutriz.setEndereco(request.getEndereco());
        nutriz.setCep(request.getCep());
    }

}
