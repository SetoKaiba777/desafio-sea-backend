package com.kaibacorp.figmabackend.service;



import com.kaibacorp.figmabackend.domain.entity.Setor;
import com.kaibacorp.figmabackend.domain.exception.ObjetoJaRegistradoException;
import com.kaibacorp.figmabackend.domain.exception.ObjetoNaoEncontradoException;
import com.kaibacorp.figmabackend.domain.repository.CargoRepository;
import com.kaibacorp.figmabackend.domain.repository.SetorRepository;
import com.kaibacorp.figmabackend.domain.service.SetorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static com.kaibacorp.figmabackend.utils.Constructors.*;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SetorServiceTest {

    private final String VALID_NOME = "novoNome";

    private final String INVALID_NOME = "nomeInvalido";

    @Mock
    private SetorRepository setorRepository;

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private SetorService setorService;

    @Test
    public void whenBuscarTodosIsCalledThenReturnAListOfStrings(){
        var setor = criarSetorEsperado();
        var expected = singletonList(setor.getNome());
        when(setorRepository.findAll()).thenReturn(singletonList(setor));
        var output = setorService.buscarTodos();
        assertEquals(expected, output);
    }

    @Test
    void whenBuscarPorNomeIsCalledWithAValidINameThenReturnSetor() {
        var expected = criarInput();
        when(setorRepository.findByNome(expected.getNome())).thenReturn(Optional.of(expected));
        var output = setorService.buscarPorNome(expected.getNome());
        assertEquals(expected,output);

    }

    @Test
    void whenBuscarPorNomeIsCalledWithAInvalidINameThenAnErrorShouldBeReturned() {
        when(setorRepository.findByNome(INVALID_NOME)).thenReturn(Optional.empty());
        assertThrows(ObjetoNaoEncontradoException.class,() ->setorService.apagarSetorPorNome(INVALID_NOME));
    }

    @Test
    public void whenSalvarSetorIsCalledThenReturnASetor(){
        var setorInput = criarInput();
        var expected = criarSetorEsperado();
        when(setorRepository.findByNome(any(String.class))).thenReturn(Optional.empty());
        when(setorRepository.save(any(Setor.class))).thenReturn(expected);
        var output = setorService.salvarSetor(setorInput);
        assertEquals(expected,output);
    }

    @Test
    public void whenSalvarSetorIsCalledWithInvalidSetorThenReturnAnError(){
        var setorInput = criarInput();
        when(setorRepository.findByNome(any(String.class))).thenReturn(Optional.of(setorInput));
        assertThrows(ObjetoJaRegistradoException.class,()->setorService.salvarSetor(setorInput));
    }

    @Test
    public void whenAtualizarSetorIsCalledThenReturnASetor(){
        Setor atual = criarInput();
        Setor setorInput = criarInput();
        var nomeAtual = setorInput.getNome();
        setorInput.setNome(VALID_NOME);
        Setor expected = criarSetorEsperado();
        expected.setNome(VALID_NOME);
        when(setorRepository.findByNome(nomeAtual)).thenReturn(Optional.of(atual));
        when(setorRepository.findByNome(VALID_NOME.toLowerCase())).thenReturn(Optional.empty());
        when(setorRepository.save(any(Setor.class))).thenReturn(expected);
        var output = setorService.atualizarSetor(setorInput,nomeAtual);
        assertEquals(expected,output);
    }

    @Test
    public void whenAtualizarSetorIsCalledWithInvalidSetorObjectThenReturnAnError(){
        Setor atual = criarInput();
        Setor setorInput = criarInput();
        var nomeAtual = setorInput.getNome();
        setorInput.setNome(VALID_NOME);
        when(setorRepository.findByNome(nomeAtual)).thenReturn(Optional.of(atual));
        when(setorRepository.findByNome(VALID_NOME.toLowerCase())).thenReturn(Optional.of(atual));
        assertThrows(ObjetoJaRegistradoException.class,()->setorService.atualizarSetor(setorInput,nomeAtual));
    }

    @Test
    public void whenAtualizarSetorIsCalledToNoExistentSetorThenReturnAnError(){
        var setorInput = criarInput();
        when(setorRepository.findByNome(INVALID_NOME.toLowerCase())).thenReturn(Optional.empty());
        assertThrows(ObjetoNaoEncontradoException.class,()->setorService.atualizarSetor(setorInput,INVALID_NOME));
    }

    @Test
    void whenApagarSetorPorNomeIsCalledWithAValidINameThenSetorShouldBeExcluded() {
        var setor = criarInput();
        when(setorRepository.findByNome(setor.getNome())).thenReturn(Optional.of(setor));
        doNothing().when(setorRepository).deleteByNome(setor.getNome());
        setorService.apagarSetorPorNome(setor.getNome());

        verify(setorRepository,times(1)).findByNome(setor.getNome());
        verify(setorRepository,times(1)).deleteByNome(setor.getNome());
    }

    @Test
    void whenApagarSetorPorNomeIsCalledWithAInvalidINameThenAnErrorShouldBeReturned() {
        when(setorRepository.findByNome(INVALID_NOME)).thenReturn(Optional.empty());
        assertThrows(ObjetoNaoEncontradoException.class,() ->setorService.apagarSetorPorNome(INVALID_NOME));
    }

}
