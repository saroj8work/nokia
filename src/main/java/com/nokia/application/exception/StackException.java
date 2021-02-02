package com.nokia.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StackException extends ResponseEntityExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> BadRequestHandler(Exception ex) {
                
                return new ResponseEntity<String>("Invalid Operation...", HttpStatus.BAD_REQUEST);
        }

}

