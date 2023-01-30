package com.kaibacorp.figmabackend.controller;


import com.kaibacorp.figmabackend.api.controller.SetorController;
import com.kaibacorp.figmabackend.api.dto.input.SetorInputDTO;
import com.kaibacorp.figmabackend.api.dto.output.SetorOutputDTO;
import com.kaibacorp.figmabackend.core.mapper.SetorMapper;
import com.kaibacorp.figmabackend.domain.entity.Setor;
import com.kaibacorp.figmabackend.domain.exception.ObjetoJaRegistradoException;
import com.kaibacorp.figmabackend.domain.exception.ObjetoNaoEncontradoException;
import com.kaibacorp.figmabackend.domain.service.SetorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.kaibacorp.figmabackend.utils.Constructors.criarSetorEsperado;
import static com.kaibacorp.figmabackend.utils.Constructors.setorInputDTO;
import static com.kaibacorp.figmabackend.utils.JsonConvertionUtils.asJsonString;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SetorControllerTest {

    private final String API_URL_PATH = "/api/v1/setores";
    private final String VALID_SETOR = "limpeza";
    private final String INVALID_SETOR = "invalid";
    private MockMvc mockMvc;

    @Mock
    private SetorService setorService;
    @InjectMocks
    private SetorController setorController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(setorController).build();
    }


    @Test
    void whenGETlistarSetoresIsCalledThenAListOfSetorIsReturned() throws Exception{
        String nomeSetor = "limpeza";
        when(setorService.buscarTodos()).thenReturn(singletonList(nomeSetor));
        mockMvc.perform(get(API_URL_PATH).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]",is(nomeSetor)));

    }

    @Test
    void whenGETmostrarSetorIsCalledWithAValidNameThenASetorIsReturned() throws Exception{
        Setor setorEsperado = criarSetorEsperado();
        SetorOutputDTO outputDTO = SetorMapper.toDTO(setorEsperado);
        when(setorService.buscarPorNome(VALID_SETOR)).thenReturn(setorEsperado);
        mockMvc.perform(get(API_URL_PATH+"/"+VALID_SETOR)
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(outputDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is((int) outputDTO.getId())))
                .andExpect(jsonPath("$.nome",is(outputDTO.getNome())));

    }

    @Test
    void whenPOSTsalvarSetorIsCalledWithAValidNameThenASetorIsReturned() throws Exception{
        Setor setorEsperado = criarSetorEsperado();
        SetorInputDTO inputDTO = setorInputDTO(setorEsperado);
        String teste = asJsonString(inputDTO);
        when(setorService.salvarSetor(any())).thenReturn(setorEsperado);
        mockMvc.perform(post(API_URL_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teste))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is((int) setorEsperado.getId())))
                .andExpect(jsonPath("$.nome", is(setorEsperado.getNome())));

    }


    @Test
    void whenPOSTsalvarSetorIsCalledWithAnInvalidNameThenAnErrorIsReturned() throws Exception{
        Setor setorEsperado = criarSetorEsperado();
        setorEsperado.setNome(INVALID_SETOR);
        when(setorService.salvarSetor(any())).thenThrow(ObjetoJaRegistradoException.class);
        mockMvc.perform(post(API_URL_PATH))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenPUTatualizarSetorIsCalledWithAValidNameThenASetorIsReturned() throws Exception{
        Setor setorEsperado = criarSetorEsperado();
        setorEsperado.setNome("diretoria");
        SetorInputDTO inputDTO = setorInputDTO(setorEsperado);
        when(setorService.atualizarSetor(any(Setor.class),eq(VALID_SETOR))).thenReturn(setorEsperado);
        mockMvc.perform(put(API_URL_PATH+"/"+VALID_SETOR)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) setorEsperado.getId())))
                .andExpect(jsonPath("$.nome", is(setorEsperado.getNome())));

    }


    @Test
    void whenPUTatualizarSetorIsCalledWithAnInvalidObjectThenAnErrorIsReturned() throws Exception{
        Setor setorEsperado = criarSetorEsperado();
        setorEsperado.setNome("Valor existente");
        SetorInputDTO inputDTO = setorInputDTO(setorEsperado);
        when(setorService.atualizarSetor(any(Setor.class),eq(VALID_SETOR))).thenThrow(ObjetoJaRegistradoException.class);
        mockMvc.perform(put(API_URL_PATH+"/"+VALID_SETOR))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenDELETEsalvarSetorIsCalledWithAnInvalidNameThenAnErrorIsReturned() throws Exception{
        doThrow(ObjetoNaoEncontradoException.class).when(setorService).apagarSetorPorNome(INVALID_SETOR);
        mockMvc.perform(delete(API_URL_PATH+"/"+INVALID_SETOR))
                .andExpect(status().isNotFound());

    }

    @Test
    void whenDELETEdeletaSetorIsCalledWithAValidNameThenStatusNoContentIsReturned() throws Exception{
        doNothing().when(setorService).apagarSetorPorNome(VALID_SETOR);
        mockMvc.perform(delete(API_URL_PATH+"/"+VALID_SETOR))
                .andExpect(status().isNoContent());
    }

}
