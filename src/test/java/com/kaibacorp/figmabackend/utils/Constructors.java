package com.kaibacorp.figmabackend.utils;

import com.kaibacorp.figmabackend.api.dto.input.CargoInputDTO;
import com.kaibacorp.figmabackend.api.dto.input.SetorInputDTO;
import com.kaibacorp.figmabackend.core.builder.CargoBuilder;
import com.kaibacorp.figmabackend.core.builder.SetorBuilder;
import com.kaibacorp.figmabackend.domain.entity.Cargo;
import com.kaibacorp.figmabackend.domain.entity.Setor;

import java.util.ArrayList;

public final class Constructors {
    private Constructors(){}


    public static Setor criarInput(){
        var setorInput = setorConstrutor();
        setorInput.getCargos().add(cargoConstrutor());
        return setorInput;
    }

    public static Setor criarSetorEsperado(){
        var cargo = cargoConstrutor();
        var expected = setorConstrutor();
        cargo.setSetor(expected);
        expected.getCargos().add(cargo);
        return expected;
    }


    public static Setor setorConstrutor(){
        return SetorBuilder
                .builder()
                .cargos(new ArrayList<Cargo>())
                .id(1)
                .nome("limpeza").build().toEntity();
    }

    public static Cargo cargoConstrutor(){
        return CargoBuilder.builder()
                .id(1)
                .nome("cargo teste")
                .build()
                .toEntity();
    }

    public static SetorInputDTO setorInputDTO(Setor setor){
        var setorInput = new SetorInputDTO();
        var cargosInput = new ArrayList<CargoInputDTO>();
        setor.getCargos().stream().forEach(cargo -> cargosInput.add(new CargoInputDTO(cargo.getNome())));
        setorInput.setNomeSetor(setor.getNome());
        setorInput.setListaCargos(cargosInput);
        return setorInput;
    }
}
