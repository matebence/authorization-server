package com.blesk.authorizationserver.Exceptions;

import com.blesk.authorizationserver.Values.Messages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class AuthorizationServerExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorMessage> handleTypeMismatchException(Exception ex, WebRequest request) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), Messages.TYPE_MISMATCH_EXCEPTION, request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleMethodArgumentException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> errorObj = new LinkedHashMap<>();
        errorObj.put("timestamp", new Date());

        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        errorObj.put("validations", errors);
        errorObj.put("details", request.getDescription(false));
        errorObj.put("error", true);

        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleRequestBodyNotFoundException(Exception ex, WebRequest request) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), Messages.REQUEST_BODY_NOT_FOUND_EXCEPTION, request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationServerException.class)
    public final ResponseEntity<ErrorMessage> handleResourcesException(Exception ex, WebRequest request) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<ErrorMessage> handleNotFoundError(Exception ex, WebRequest request) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), Messages.PAGE_NOT_FOUND_EXCEPTION, request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<ErrorMessage> handlePageNotFoundException(Exception ex, WebRequest request) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), Messages.PAGE_NOT_FOUND_EXCEPTION, request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ErrorMessage> handelNullPointerExceptions(Exception ex, WebRequest request) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), Messages.NULL_POINTER_EXCEPTION, request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
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