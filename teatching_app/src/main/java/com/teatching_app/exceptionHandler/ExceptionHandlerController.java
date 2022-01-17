package com.teatching_app.exceptionHandler;

import com.teatching_app.exceptionHandler.exception.ResourceAlreadyExistsException;
import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.exceptionHandler.exception.WrongFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Object> handlerIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<Object> handlerIllegalState(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    ResponseEntity<Object> handlerResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }


    @ExceptionHandler(ResourceNotExistsException.class)
    ResponseEntity<Object> handlerResourceNotExistsException(ResourceNotExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(WrongFormatException.class)
    ResponseEntity<Object> handlerWrongFormatException(WrongFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
