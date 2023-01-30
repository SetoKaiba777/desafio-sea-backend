package com.kaibacorp.figmabackend.domain.service;

import com.kaibacorp.figmabackend.domain.entity.Setor;
import com.kaibacorp.figmabackend.domain.exception.ObjetoNaoEncontradoException;
import com.kaibacorp.figmabackend.domain.repository.CargoRepository;
import com.kaibacorp.figmabackend.domain.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.kaibacorp.figmabackend.domain.utils.ValidadorSetor.validarAtualizacao;
import static com.kaibacorp.figmabackend.domain.utils.ValidadorSetor.validarSalvar;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private CargoRepository cargoRepository;

    public List<String> buscarTodos(){
        return setorRepository
                .findAll().stream()
                .map(setor -> setor.getNome())
                .collect(Collectors.toList());
    }

    public Setor salvarSetor(Setor inputSetor){
        validarSalvar(inputSetor,setorRepository);
        Setor setorParaSalvar = new Setor();
        setorParaSalvar.setNome(inputSetor.getNome().toLowerCase());
        return setorRepository.save(construirSetor(setorParaSalvar,inputSetor));
    }

    public Setor atualizarSetor(Setor inputSetor,String nomeAtual){
        validarAtualizacao(inputSetor,nomeAtual,setorRepository);
        var setorParaSalvar = buscarPorNome(nomeAtual);
        if(!inputSetor.getCargos().isEmpty()) cargoRepository.removeAllBySetor(setorParaSalvar);
        return setorRepository.save(construirSetor(setorParaSalvar,inputSetor));
    }

    public void apagarSetorPorNome(String nome){
        buscarPorNome(nome);
        setorRepository.deleteByNome(nome);
    }
    public Setor buscarPorNome(String name){
        return setorRepository.findByNome(name)
                .orElseThrow(()-> new ObjetoNaoEncontradoException("Setor não encontrado"));
    }

    private Setor construirSetor(Setor setorParaSalvar,Setor inputSetor){
        setorParaSalvar.setNome(inputSetor.getNome().toLowerCase());
        inputSetor.getCargos().stream().forEach(cargo -> cargo.setSetor(setorParaSalvar));
        if(!inputSetor.getCargos().isEmpty()) setorParaSalvar.setCargos(inputSetor.getCargos()); ;
        return setorParaSalvar;
    }


}
