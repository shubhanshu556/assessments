package com.marktine.assessment.exceptions;



public class InvalidQueryException extends ServiceException {

    private static final String MESSAGE = "Invalid inputs";

    public InvalidQueryException() {
        this.setStatusCode(400);
        this.setMessage(MESSAGE);
    }

    public InvalidQueryException(String message) {
        this.setStatusCode(400);
        this.setMessage(message);
    }

    public InvalidQueryException(Integer code) {
        this.setStatusCode(code);
        this.setMessage(MESSAGE);
    }

    public InvalidQueryException(Integer code, String message) {
        this.setStatusCode(code);
        this.setMessage(message);
    }

}
