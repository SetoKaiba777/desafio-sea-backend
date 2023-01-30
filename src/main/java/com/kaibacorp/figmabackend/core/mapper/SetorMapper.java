package com.kaibacorp.figmabackend.core.mapper;

import com.kaibacorp.figmabackend.api.dto.input.SetorInputDTO;
import com.kaibacorp.figmabackend.api.dto.output.SetorOutputDTO;
import com.kaibacorp.figmabackend.core.builder.SetorBuilder;
import com.kaibacorp.figmabackend.domain.entity.Cargo;
import com.kaibacorp.figmabackend.domain.entity.Setor;

import java.util.ArrayList;

public final class SetorMapper {

    private SetorMapper() {
    }

    public static Setor toEntity(SetorInputDTO setorInputDTO) {
        if(setorInputDTO.getListaCargos()==null)
            return SetorBuilder.builder()
                    .nome(setorInputDTO.getNomeSetor())
                    .cargos(new ArrayList<Cargo>())
                    .build().toEntity();
        return SetorBuilder.builder()
                .nome(setorInputDTO.getNomeSetor())
                .cargos(CargoMapper.listToEntitylist(setorInputDTO.getListaCargos()))
                .build().toEntity();
    }

    public static SetorOutputDTO toDTO(Setor setor){
        return SetorOutputDTO.builder()
                .id(setor.getId())
                .listaCargos(CargoMapper.listToDTOlist(setor.getCargos()))
                .nome(setor.getNome().toLowerCase()).build();
    }

}
