package com.kaibacorp.figmabackend.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_CARGO")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "setor_id", referencedColumnName = "id", nullable = false)
    private Setor setor;

    @OneToMany(mappedBy = "cargo",cascade = CascadeType.ALL)
    private List<Trabalhador> trabalhadores;

}
