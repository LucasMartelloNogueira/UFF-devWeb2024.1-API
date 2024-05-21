package id.uff.lucasmartello20241.devwebapi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;

public class BaseController {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.toMap());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleExeption(Exception exception) {

        System.out.println(exception.getCause());
        
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", exception.getMessage());
        responseBody.put("cause", exception.getCause().toString());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

}
