package com.kaibacorp.figmabackend.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class SetorInputDTO {
    @NotBlank
    @Schema(description = "Nome do Setor", example = "vendas")
    private String nomeSetor;
    private List<CargoInputDTO> listaCargos;

}
