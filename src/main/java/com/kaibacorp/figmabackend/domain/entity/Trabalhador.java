package com.kaibacorp.figmabackend.domain.entity;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="TB_TRABALHADOR")
public class Trabalhador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true,nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "cargo_id", referencedColumnName = "id", nullable = false)
    private Cargo cargo;
}
