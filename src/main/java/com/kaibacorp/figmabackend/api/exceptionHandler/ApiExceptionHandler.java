package com.kaibacorp.figmabackend.api.exceptionHandler;

import com.kaibacorp.figmabackend.domain.exception.ObjetoJaRegistradoException;
import com.kaibacorp.figmabackend.domain.exception.ObjetoNaoEncontradoException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handlerSQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problema problema = setProblema(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<Object> handlerNotFoundException(ObjetoNaoEncontradoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problema problema = setProblema(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ObjetoJaRegistradoException.class)
    public ResponseEntity<Object> handlerServiceException(ObjetoJaRegistradoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problema problema = setProblema(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<Object> handlerPropertyValueException(
            PropertyValueException ex,
            WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problema problema = setProblema(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        var campos = new ArrayList<Problema.Campo>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            campos.add(new Problema.Campo(nome, mensagem));
        }
        var problema = setProblema("Um ou mais campos não foram preenchidos corretamente.", status);
        problema.setCampos(campos);
        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    private Problema setProblema(String title, HttpStatus status){

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitle(title);
        problema.setOffsetDateTime(OffsetDateTime.now());

        return problema;
    }

}
