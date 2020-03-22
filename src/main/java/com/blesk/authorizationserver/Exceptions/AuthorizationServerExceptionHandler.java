package com.blesk.authorizationserver.Exceptions;

import com.blesk.authorizationserver.Utility.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.Date;

@ControllerAdvice
public class AuthorizationServerExceptionHandler {

    private ResourceLoader resourceLoader;

    @Autowired
    public AuthorizationServerExceptionHandler(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @ExceptionHandler(AuthorizationServerException.class)
    public final ResponseEntity<ErrorMessage> handleResourcesException(Exception ex, WebRequest request) {

        ErrorMessage errorObj = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ErrorMessage> handelNullPointerExceptions(Exception ex, WebRequest request) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), Messages.NULL_POINTER_EXCEPTION, request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public final ResponseEntity<ErrorMessage> handleDatabaseExceptions(Exception ex, WebRequest request) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), Messages.SQL_EXCEPTION, request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleExceptions(Exception ex, WebRequest request) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), Messages.EXCEPTION, request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}