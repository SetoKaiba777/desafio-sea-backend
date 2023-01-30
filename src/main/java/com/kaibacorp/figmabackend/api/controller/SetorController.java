package com.kaibacorp.figmabackend.api.controller;

import com.kaibacorp.figmabackend.api.dto.input.SetorInputDTO;
import com.kaibacorp.figmabackend.api.dto.output.SetorOutputDTO;
import com.kaibacorp.figmabackend.domain.service.SetorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.kaibacorp.figmabackend.core.mapper.SetorMapper.*;

@RestController
@RequestMapping("api/v1/setores")
@Tag(name = "Setores Controller")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
            @ApiResponse(responseCode = "500", description = "Erro Interno de Servidor.")

    })
    @GetMapping
    @Operation(summary = "Apresenta uma lista com todos os nomes dos setores")
    public List<String> listarSetores(){
        return setorService.buscarTodos();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro Interno de Servidor.")

    })
    @Operation(summary = "Busca o Setor por Nome e Mostra seus respectivos Cargos")
    @GetMapping("/{nomeSetor}")
    public SetorOutputDTO mostrarSetor(@PathVariable("nomeSetor") String nomeSetor){
        return toDTO(setorService.buscarPorNome(nomeSetor));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criação realizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Setor com nome já existente!"),
            @ApiResponse(responseCode = "500", description = "Erro Interno de Servidor.")

    })
    @PostMapping
    @Operation(summary = "Cria um novo Setor")
    @ResponseStatus(HttpStatus.CREATED)
    public SetorOutputDTO salvarSetor(@Valid @RequestBody SetorInputDTO setorInputDTO){
        return toDTO(setorService.salvarSetor(toEntity(setorInputDTO)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado."),
            @ApiResponse(responseCode = "400", description = "Setor com nome já existente!"),
            @ApiResponse(responseCode = "500", description = "Erro Interno de Servidor.")

    })
    @PutMapping("/{nomeSetor}")
    @Operation(summary = "Atualiza um Setor")
    public SetorOutputDTO atualizarSetor(@PathVariable("nomeSetor") String nomeSetor,
                                         @Valid @RequestBody SetorInputDTO setorInputDTO){
        return toDTO(setorService.atualizarSetor(toEntity(setorInputDTO),nomeSetor));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Setor deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro Interno de Servidor.")

    })
    @DeleteMapping("/{nomeSetor}")
    @Operation(summary = "Deleta o Setor por Nome do Setor")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaSetor(@PathVariable("nomeSetor") String nomeSetor){
        setorService.apagarSetorPorNome(nomeSetor);
    }


}
