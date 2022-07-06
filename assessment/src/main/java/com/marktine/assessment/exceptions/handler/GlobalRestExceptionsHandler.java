package com.marktine.assessment.exceptions.handler;


import com.marktine.assessment.exceptions.ServiceException;
import com.marktine.assessment.models.ServiceResult;
import com.marktine.assessment.models.ServiceStatus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class GlobalRestExceptionsHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle.
     *
     * @param request the request
     * @return the http response
     */
    @ExceptionHandler(value
            = { Exception.class})
    protected ResponseEntity<?> handleConflict(
            Exception e, WebRequest request) {

        if(e instanceof ServiceException) {
           return ResponseEntity.status(((ServiceException) e).getStatusCode())
                    .body(new ServiceResult<>(null,new ServiceStatus(((ServiceException) e).getStatusCode(),e.getMessage())));
        }
       return ResponseEntity.status(500)
               .body(new ServiceResult<>(null,new ServiceStatus(1,"There is some internal issue")));
    }
}