package com.kaibacorp.figmabackend.core.mapper;

import com.kaibacorp.figmabackend.api.dto.input.CargoInputDTO;
import com.kaibacorp.figmabackend.api.dto.output.CargoOutputDTO;
import com.kaibacorp.figmabackend.core.builder.CargoBuilder;
import com.kaibacorp.figmabackend.domain.entity.Cargo;

import java.util.ArrayList;
import java.util.List;

public final class CargoMapper {

    private CargoMapper(){}

    public static Cargo toEntity(CargoInputDTO cargoInputDTO){
        return CargoBuilder.builder()
                .nome(cargoInputDTO.getNomeCargo().toLowerCase())
                .build()
                .toEntity();
    }


    public static CargoOutputDTO toDTO(Cargo cargo){
        return CargoOutputDTO.builder()
                .id(cargo.getId())
                .nome(cargo.getNome())
                .setor(cargo.getSetor().getNome())
                .build();
    }

    public static List<CargoOutputDTO> listToDTOlist(List<Cargo> cargos){
        List listaDeCargos = new ArrayList<CargoOutputDTO>();
        for(Cargo cargo : cargos)
            listaDeCargos.add(toDTO(cargo));
        return listaDeCargos;
    }

    public static List<Cargo> listToEntitylist(List<CargoInputDTO> cargos){
        List listaDeCargos = new ArrayList<CargoOutputDTO>();
        for(CargoInputDTO cargo : cargos)
            listaDeCargos.add(toEntity(cargo));
        return listaDeCargos;
    }
}
