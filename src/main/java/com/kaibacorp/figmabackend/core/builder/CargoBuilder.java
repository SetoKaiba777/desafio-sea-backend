package com.kaibacorp.figmabackend.core.builder;

import com.kaibacorp.figmabackend.domain.entity.Cargo;
import com.kaibacorp.figmabackend.domain.entity.Setor;
import com.kaibacorp.figmabackend.domain.entity.Trabalhador;
import lombok.Builder;

import java.util.List;


@Builder
public class CargoBuilder {

    private long id;
    private String nome;
    private Setor setor;
    private List<Trabalhador> trabalhadores;

    public Cargo toEntity(){
        return new Cargo(id,nome,setor,trabalhadores);
    }

}
