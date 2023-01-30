package com.kaibacorp.figmabackend.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SetorOutputDTO {
    @Schema(description = "Id do Setor na tablea TB_SETOR", example = "1")
    private long id;
    @Schema(description = "Nome do Setor", example = "administrativo")
    private String nome;
    private List<CargoOutputDTO> listaCargos;

}
