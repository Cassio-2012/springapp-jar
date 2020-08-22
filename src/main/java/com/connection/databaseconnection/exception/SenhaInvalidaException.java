package com.connection.databaseconnection.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("senha invalida");
    }
}
