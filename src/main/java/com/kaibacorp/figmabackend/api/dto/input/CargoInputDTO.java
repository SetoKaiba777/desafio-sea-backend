package com.kaibacorp.figmabackend.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoInputDTO {
    @NotBlank
    @Schema(description = "Nome do Cargo", example = "auxiliar administrativo")
    private String nomeCargo;

}
