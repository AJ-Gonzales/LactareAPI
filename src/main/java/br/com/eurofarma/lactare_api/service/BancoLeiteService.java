package br.com.eurofarma.lactare_api.service;

import br.com.eurofarma.lactare_api.dto.request.BancoLeiteRequest;
import br.com.eurofarma.lactare_api.dto.response.BancoLeiteResponse;
import br.com.eurofarma.lactare_api.entities.BancoLeite;
import br.com.eurofarma.lactare_api.exceptions.ResourceNotFoundException;
import br.com.eurofarma.lactare_api.repository.BancoLeiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BancoLeiteService {

    @Autowired
    private BancoLeiteRepository bancoLeiteRepository;

    @Transactional(readOnly = true)
    public List<BancoLeiteResponse> findAllBancos(){

        List<BancoLeite> bancos = bancoLeiteRepository.findAll();

        return bancos.stream().map(BancoLeiteResponse::new).toList();
    }

    @Transactional(readOnly = true)
    public BancoLeiteResponse findBAncoById(Long id){

        BancoLeite banco = bancoLeiteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: "+id));

        return new BancoLeiteResponse(banco);
    }

    @Transactional
    public BancoLeiteResponse saveBanco(BancoLeiteRequest request){

        BancoLeite banco = new BancoLeite();
        copyDtoToBanco(request,banco);
        BancoLeite save = bancoLeiteRepository.save(banco);

        return new BancoLeiteResponse(save);
    }

    @Transactional
    public BancoLeiteResponse updateBanco(Long id, BancoLeiteRequest request){

        BancoLeite banco = bancoLeiteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: "+id)
        );

        copyDtoToBanco(request,banco);
        BancoLeite update = bancoLeiteRepository.save(banco);

        return new BancoLeiteResponse(update);
    }

    @Transactional
    public void deleteBanco(Long id){

        if (!bancoLeiteRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: "+id);
        }

        bancoLeiteRepository.deleteById(id);
    }


    private void copyDtoToBanco(BancoLeiteRequest request, BancoLeite banco){

        banco.setNome(request.getNome());
        banco.setTelefone(request.getTelefone());
        banco.setEndereco(request.getEndereco());
        banco.setCep(request.getCep());

    }

}
