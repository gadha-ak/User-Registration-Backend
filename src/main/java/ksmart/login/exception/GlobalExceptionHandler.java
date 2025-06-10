package ksmart.login.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handleIllegalArgumentException(IllegalArgumentException ex){
        Map<String,String> response= new HashMap<>();
        response.put("message",ex.getMessage());
        return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleRunTimeException(RuntimeException ex){
        Map<String,String> response= new HashMap<>();
        response.put("message",ex.getMessage());
        return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
    }
}
