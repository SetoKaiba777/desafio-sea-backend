package com.kaibacorp.figmabackend.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjetoJaRegistradoException extends RuntimeException{

    public ObjetoJaRegistradoException(String msg){
        super(msg);
    }
}
