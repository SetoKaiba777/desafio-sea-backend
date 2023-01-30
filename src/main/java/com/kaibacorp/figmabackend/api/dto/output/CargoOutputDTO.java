package com.kaibacorp.figmabackend.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CargoOutputDTO {
    @Schema(description = "Id do Cargo na tablea TB_CARGO", example = "1")
    private long id;
    @Schema(description = "Nome do Cargo", example = "auxiliar administrativo")
    private String nome;
    @Schema(description = "Setor do Cargo em questão", example = "administrativo")
    private String setor;

}
