package com.kaibacorp.figmabackend.domain.utils;

import com.kaibacorp.figmabackend.domain.entity.Setor;
import com.kaibacorp.figmabackend.domain.exception.ObjetoJaRegistradoException;
import com.kaibacorp.figmabackend.domain.exception.ObjetoNaoEncontradoException;
import com.kaibacorp.figmabackend.domain.repository.SetorRepository;

public final class ValidadorSetor {

    private ValidadorSetor(){}

    public static void validarAtualizacao(Setor setor, String nomeAtual, SetorRepository setorRepository){
        if(setorRepository.findByNome(nomeAtual.toLowerCase()).isEmpty())
            throw new ObjetoNaoEncontradoException("O setor em questão não existe!");
        if(!setorRepository.findByNome(setor.getNome().toLowerCase()).isEmpty() &&
                !nomeAtual.toLowerCase().equals(setor.getNome().toLowerCase())) {
            throw new ObjetoJaRegistradoException("Setor já Registrado!");
        }
    }

    public static void validarSalvar(Setor setor,SetorRepository setorRepository){
        if(!setorRepository.findByNome(setor.getNome().toLowerCase()).isEmpty()) {
            throw new ObjetoJaRegistradoException("Setor já Registrado!");
        }
    }
}
