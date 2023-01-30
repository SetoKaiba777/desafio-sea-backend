package com.kaibacorp.figmabackend.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TB_SETOR")
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String nome;

    @OneToMany(mappedBy = "setor",cascade = CascadeType.ALL)
    private List<Cargo> cargos;

}
