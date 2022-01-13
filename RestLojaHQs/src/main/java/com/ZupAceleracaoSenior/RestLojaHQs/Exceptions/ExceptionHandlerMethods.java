package com.ZupAceleracaoSenior.RestLojaHQs.Exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import feign.FeignException;

@ControllerAdvice
public class ExceptionHandlerMethods {
    
    @ExceptionHandler (NoSuchElementException.class)
    public final ResponseEntity<Object> handleNotFoundException(NoSuchElementException ex, WebRequest request) {
        List<String> detalhes = new ArrayList<String>();
        RespostaErro erro = new RespostaErro("Recurso não encontrado no sistema.", detalhes, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (FeignException.class)
    public final ResponseEntity<Object> handleMarvelExceptions (FeignException ex, WebRequest request) {
        List<String> detalhes = new ArrayList<String>();
        RespostaErro erro = new RespostaErro(ex.getLocalizedMessage(), detalhes, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DadosUtilizadosEx.class)
    public final ResponseEntity<Object> handleDadosExistentesException(DadosUtilizadosEx ex, WebRequest request) {
        List<String> detalhes = new ArrayList<String>();
        RespostaErro erro = new RespostaErro("CPF ou Email já cadastrado no sistema.", detalhes, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleEx(ConstraintViolationException ex, WebRequest request) {
        
        List<String> detalhes = new ArrayList<String>();
        for (ConstraintViolation erro : ex.getConstraintViolations()) {
            detalhes.add(erro.getMessage());
        }

        RespostaErro erros = new RespostaErro("Um ou mais argumentos incorretos.", detalhes, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }

}
