package com.javanauta.aprendendo_spring.infraestructure.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }
    public ResourceNotFoundException(String mansegem, Throwable throwable){
        super(mansegem, throwable);
    }

}
