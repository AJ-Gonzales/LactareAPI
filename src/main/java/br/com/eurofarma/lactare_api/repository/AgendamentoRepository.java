package br.com.eurofarma.lactare_api.repository;

import br.com.eurofarma.lactare_api.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
}
