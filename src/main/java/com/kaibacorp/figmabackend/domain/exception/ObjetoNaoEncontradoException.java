package com.kaibacorp.figmabackend.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjetoNaoEncontradoException extends RuntimeException{

    public ObjetoNaoEncontradoException(String msg){
        super(msg);
    }
}
