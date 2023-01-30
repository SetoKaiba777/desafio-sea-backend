package com.kaibacorp.figmabackend.core.builder;

import com.kaibacorp.figmabackend.domain.entity.Cargo;
import com.kaibacorp.figmabackend.domain.entity.Setor;
import lombok.Builder;

import java.util.List;

@Builder
public class SetorBuilder {

    private long id;
    private String nome;
    private List<Cargo> cargos;

    public Setor toEntity(){
        return new Setor(id,nome,cargos);
    }
}
