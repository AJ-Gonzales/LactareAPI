package br.com.eurofarma.lactare_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_pesquisa_satisfacao")
public class PesquisaSatisfacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer nota;
    private String comentario;
    @Column(nullable = false)
    private LocalDate dataResposta;

    @ManyToOne
    @JoinColumn(name = "nutriz_id")
    private Nutriz nutriz;

    @ManyToOne
    @JoinColumn(name = "doacao_id")
    private Doacao doacao;

}
