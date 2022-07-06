package com.marktine.assessment.exceptions;

import lombok.Getter;
import lombok.Setter;

public abstract class ServiceException extends RuntimeException{

    @Getter
    @Setter
    private Integer statusCode;

    @Getter@Setter
    private String message;

    @Getter @Setter
    private Object data;

    @Getter@Setter
    private Boolean logError = true;
}

