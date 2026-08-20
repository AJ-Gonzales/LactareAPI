package br.com.eurofarma.lactare_api.repository;

import br.com.eurofarma.lactare_api.entities.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacaoRepository extends JpaRepository<Doacao,Long> {
}
