package br.com.eurofarma.lactare_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_doacao")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate data;
    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(name = "nutriz_id")
    private Nutriz nutriz;

    @ManyToOne
    @JoinColumn(name = "banco_leite_id")
    private BancoLeite bancoLeite;

    @ManyToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;
}
